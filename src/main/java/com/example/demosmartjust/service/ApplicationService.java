package com.example.demosmartjust.service;


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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationService {
    private final Logger log = LoggerFactory.getLogger(ApplicationService.class);

    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;
    private final ApplicationFileService applicationFileService;

    public ApplicationService(ApplicationMapper applicationMapper, ApplicationRepository applicationRepository,ApplicationFileService applicationFileService) {
        this.applicationMapper = applicationMapper;
        this.applicationRepository = applicationRepository;
        this.applicationFileService = applicationFileService;
    }

    public ApplicationDTO create(ApplicationDTO applicationDTO) {
        log.debug("Request to save App : {}", applicationDTO);
        Application save = applicationRepository.save(applicationMapper.toEntity(applicationDTO));

        if (applicationDTO.getFileStorageHashId()!=null){
            applicationFileService.create(applicationDTO.getApplicationFileList().get(0));
            //TODO aapfileniyam save qilamiz shu yerda
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
}
