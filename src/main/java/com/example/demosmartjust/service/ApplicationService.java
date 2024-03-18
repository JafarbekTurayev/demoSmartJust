package com.example.demosmartjust.service;


import com.example.demosmartjust.dto.ApplicationConfirmRequestDTO;
import com.example.demosmartjust.dto.ApplicationDTO;
import com.example.demosmartjust.dto.ApplicationFileDTO;
import com.example.demosmartjust.entity.ApplicationFileType;
import com.example.demosmartjust.entity.ApplicationStatus;
import com.example.demosmartjust.feign.FileStorageDTO;
import com.example.demosmartjust.integration.SmartJustIntegrationService;
import com.example.demosmartjust.integration.SmartJustUploadResponse;
import com.example.demosmartjust.integration.confirm.SmartJustConfirmRequest;
import com.example.demosmartjust.integration.confirm.SmartJustConfirmResponse;
import com.smartsoft.smartofficebackend.domain.integration.smartJust.Application;
import com.smartsoft.smartofficebackend.domain.integration.smartJust.ApplicationFilterParam;
import com.smartsoft.smartofficebackend.repository.integration.smartJust.ApplicationRepository;
import com.smartsoft.smartofficebackend.service.dto.integration.smartJust.ApplicationDTO;
import com.smartsoft.smartofficebackend.service.dto.integration.smartJust.ApplicationFileDTO;
import com.smartsoft.smartofficebackend.service.mapper.ApplicationMapper;
import com.smartsoft.smartofficebackend.service.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ApplicationService {
    private final Logger log = LoggerFactory.getLogger(ApplicationService.class);

    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;
    private final ApplicationFileService applicationFileService;
    private final SmartJustIntegrationService smartJustIntegrationService;

    public ApplicationService(ApplicationMapper applicationMapper, ApplicationRepository applicationRepository, ApplicationFileService applicationFileService, SmartJustIntegrationService smartJustIntegrationService) {
        this.applicationMapper = applicationMapper;
        this.applicationRepository = applicationRepository;
        this.applicationFileService = applicationFileService;
        this.smartJustIntegrationService = smartJustIntegrationService;
    }

    public ApplicationDTO create(ApplicationDTO applicationDTO) {
        log.debug("Request to save App : {}", applicationDTO);
        Application save = applicationRepository.save(applicationMapper.toEntity(applicationDTO));

        if (!applicationDTO.getApplicationFileList().isEmpty()) {
            for (ApplicationFileDTO fileDTO : applicationDTO.getApplicationFileList()) {
                fileDTO.setApplicationId(save.getId());
                applicationFileService.create(fileDTO);
            }
        } else {
            //TODO shu yerda fayl generatsiya qilish kerak
        }

        return applicationMapper.toDto(save);
    }

    public ApplicationDTO update(ApplicationDTO applicationDTO) {
        log.debug("Request to save App : {}", applicationDTO);
        Application application = applicationMapper.toEntity(applicationDTO);
        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public Optional<ApplicationDTO> partialUpdate(ApplicationDTO applicationDTO) {
        log.debug("Request to partially update App : {}", applicationDTO);

        return applicationRepository.findById(applicationDTO.getId()).map(existingApp -> {
            applicationMapper.partialUpdate(existingApp, applicationDTO);

            return existingApp;
        }).map(applicationRepository::save).map(applicationMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ApplicationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Apps");
        return applicationRepository.findAll(pageable).map(applicationMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ApplicationDTO> findOne(Long id) {
        log.debug("Request to get Application : {}", id);
        return applicationRepository
            .findById(id)
            .map(application -> {
                ApplicationDTO applicationDTO = applicationMapper.toDto(application);
                List<ApplicationFileDTO> allListByApplicationId = applicationFileService.findAllListByApplicationId(applicationDTO.getId());
                if (allListByApplicationId.size() > 0) {
                    applicationDTO.setApplicationFileList(allListByApplicationId);
                } else {
                    applicationDTO.setApplicationFileList(new ArrayList<>());
                }
                return applicationDTO;
            });
    }

    @Transactional(readOnly = true)
    public Page<ApplicationDTO> findAllPaging(ApplicationFilterParam filter) {
        log.debug("Request to get all Application list");

        return applicationRepository
                .findAllPaging(
                        ParamUtil.parseString(filter.getPageable()),
                        filter.getId(),
                        filter.getStatus(),
                        filter.getOutDateStr(),
                        filter.getOutNumber(),
                        filter.getInDateStr(),
                        filter.getInNumber()
                ).map(application -> {
                    ApplicationDTO applicationDTO = applicationMapper.toDto(application);
                    return applicationDTO;
                });
    }

    @Transactional
    public ApplicationDTO confirm(Long id, ApplicationConfirmRequestDTO confirmRequest) {
        ApplicationDTO resAppDTO = null;
        if (!confirmRequest.getApplicationFileDTOList().isEmpty()) {
            for (ApplicationFileDTO applicationFileDTO : confirmRequest.getApplicationFileDTOList()) {
                FileStorageDTO fileStorageDTO = fileServiceFeign.getOneByHashWithByte(applicationFileDTO.getFileStorageHashId());
                if (fileStorageDTO != null) {
                    try {
                        SmartJustUploadResponse uploadResponse = smartJustIntegrationService.uploadResultFile(convertToFile(fileStorageDTO));

                        applicationFileDTO.setType(ApplicationFileType.CONFIRM.name());
                        applicationFileDTO.setSmartJustFileId(String.valueOf(uploadResponse.getData().getId()));
                        applicationFileService.partialUpdate(applicationFileDTO);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        SmartJustConfirmRequest smartJustConfirmRequest = new SmartJustConfirmRequest();
        smartJustConfirmRequest.setMessage(confirmRequest.getMessage());
        if (confirmRequest.getStatus().equals(ApplicationStatus.ACCEPTED.name())) {
            smartJustConfirmRequest.setStatus("CONFIRM");
        } else {
            smartJustConfirmRequest.setStatus("REJECTED");
        }
        smartJustConfirmRequest.setFileId(confirmRequest.getFileId());

        SmartJustConfirmResponse confirmResponse = smartJustIntegrationService.applicationConfirm(id, smartJustConfirmRequest);

        if (Objects.requireNonNull(confirmResponse).getStatus() == 0 && confirmResponse.getData() != null) {
            Optional<ApplicationDTO> dtoOptional = findOne(id);
            if (dtoOptional.isPresent()) {
                ApplicationDTO applicationDTO = dtoOptional.get();
                if (confirmResponse.getData().getApplicationStatus().equals("CONFIRM")) {
                    applicationDTO.setApplicationStatus(ApplicationStatus.ACCEPTED.name());
                } else {
                    applicationDTO.setApplicationStatus(ApplicationStatus.CANCELLED.name());
                }
                Optional<ApplicationDTO> optionalApplicationDTO = partialUpdate(applicationDTO);
                resAppDTO = optionalApplicationDTO.get();
            }
        }
        return resAppDTO;
    }

    @Transactional
    public ApplicationDTO close(Long id, ApplicationConfirmRequestDTO confirmRequest) {
        ApplicationDTO resAppDTO = null;

        if (!confirmRequest.getApplicationFileDTOList().isEmpty()) {
            for (ApplicationFileDTO applicationFileDTO : confirmRequest.getApplicationFileDTOList()) {
                FileStorageDTO fileStorageDTO = fileServiceFeign.getOneByHashWithByte(applicationFileDTO.getFileStorageHashId());
                if (fileStorageDTO != null) {
                    try {
                        SmartJustUploadResponse uploadResponse = smartJustIntegrationService.uploadResultFile(convertToFile(fileStorageDTO));

                        applicationFileDTO.setType(ApplicationFileType.CLOSE.name());
                        applicationFileDTO.setSmartJustFileId(String.valueOf(uploadResponse.getData().getId()));
                        applicationFileService.partialUpdate(applicationFileDTO);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        SmartJustConfirmRequest smartJustConfirmRequest = new SmartJustConfirmRequest();
        smartJustConfirmRequest.setMessage(confirmRequest.getMessage());
        smartJustConfirmRequest.setStatus(confirmRequest.getStatus());
        smartJustConfirmRequest.setFileId(confirmRequest.getFileId());

        //integratisiyani chaqiramiz
        SmartJustConfirmResponse closeResponse = smartJustIntegrationService.applicationClose(id, smartJustConfirmRequest);

        if (Objects.requireNonNull(closeResponse).getStatus() == 0 && closeResponse.getData() != null) {
            Optional<ApplicationDTO> dtoOptional = findOne(id);
            if (dtoOptional.isPresent()) {
                ApplicationDTO applicationDTO = dtoOptional.get();
                applicationDTO.setApplicationStatus(closeResponse.getData().getApplicationStatus());
                Optional<ApplicationDTO> optionalApplicationDTO = partialUpdate(applicationDTO);
                resAppDTO = optionalApplicationDTO.get();
            }
        }
        return resAppDTO;
    }

    private MultipartFile convertToFile(FileStorageDTO fileStorageDTO) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(fileStorageDTO.getBytes());
        MockMultipartFile multipartFile = new MockMultipartFile(
                fileStorageDTO.getName(),
                fileStorageDTO.getName(),
                fileStorageDTO.getContentType(),
                inputStream
        );

        return multipartFile;
    }

}
