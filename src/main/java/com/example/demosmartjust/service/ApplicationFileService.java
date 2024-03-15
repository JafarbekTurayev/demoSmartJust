package com.example.demosmartjust.service;

import com.example.demosmartjust.dto.ApplicationDTO;
import com.example.demosmartjust.dto.ApplicationFileDTO;
import com.example.demosmartjust.entity.ApplicationFile;
import com.example.demosmartjust.entity.ApplicationFileFilterParam;
import com.example.demosmartjust.entity.ParamUtil;
import com.example.demosmartjust.feign.FileServiceFeign;
import com.example.demosmartjust.feign.FileStorageDTO;
import com.example.demosmartjust.mapper.ApplicationFileMapper;
import com.example.demosmartjust.repository.ApplicationFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Service Implementation for managing {@link com.example.demosmartjust.entity.ApplicationFile}.
 */
@Service
@Transactional
public class ApplicationFileService {

    private final Logger log = LoggerFactory.getLogger(ApplicationFileService.class);

    private final ApplicationFileRepository applicationFileRepository;

    private final ApplicationFileMapper applicationFileMapper;

//    private final FileServiceFeign fileServiceFeign;
//, FileServiceFeign fileServiceFeign
    public ApplicationFileService(ApplicationFileRepository applicationFileRepository, ApplicationFileMapper applicationFileMapper) {
        this.applicationFileRepository = applicationFileRepository;
        this.applicationFileMapper = applicationFileMapper;
//        this.fileServiceFeign = fileServiceFeign;
    }


    public ApplicationFileDTO create(ApplicationFileDTO applicationFileDTO) {
        log.debug("Request to save ApplicationFile : {}", applicationFileDTO);
        ApplicationFile applicationFile = applicationFileMapper.toEntity(applicationFileDTO);
        applicationFile = applicationFileRepository.save(applicationFile);
        return applicationFileMapper.toDto(applicationFile);
    }


    public ApplicationFileDTO update(ApplicationFileDTO applicationFileDTO) {
        log.debug("Request to save ApplicationFile : {}", applicationFileDTO);
        ApplicationFile cooperationContractFile = applicationFileMapper.toEntity(applicationFileDTO);
        ApplicationFile save = applicationFileRepository.save(cooperationContractFile);
        return applicationFileMapper.toDto(save);
    }

    public Optional<ApplicationFileDTO> partialUpdate(ApplicationFileDTO cooperationContractFileDTO) {
        log.debug("Request to partially update ApplicationFile : {}", cooperationContractFileDTO);

        return applicationFileRepository
            .findById(cooperationContractFileDTO.getId())
            .map(existingCoopContractFile -> {
                applicationFileMapper.partialUpdate(existingCoopContractFile, cooperationContractFileDTO);

                return existingCoopContractFile;
            })
            .map(applicationFileRepository::save)
            .map(applicationFileMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ApplicationFileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationFile");
        return applicationFileRepository.findAll(pageable).map(applicationFileMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ApplicationFileDTO> findOne(Long id) {
        log.debug("Request to get ApplicationFile : {}", id);
        return applicationFileRepository.findById(id).map(applicationFileMapper::toDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete ApplicationFile : {}", id);
        applicationFileRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<ApplicationFileDTO> findAllPaging(ApplicationFileFilterParam filter) {
        log.debug("Request to get all ApplicationFileDTO list");
        return applicationFileRepository
            .findAllPaging(
                ParamUtil.parseString(filter.getPageable()),
                filter.getId(),
                filter.getFileStorageHashId(),
                filter.getApplicationId()
            )
            .map(applicationFileMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<ApplicationFileDTO> findAllList() {
        log.debug("Request to get all ApplicationFileDTO list");
        return applicationFileRepository.findAllByOrderByIdAsc().stream().map(applicationFileMapper::toDto).collect(Collectors.toList());
    }

    public ApplicationDTO saveApplicationFileList(ApplicationDTO applicationDTO) {
        if (applicationDTO.getId() != null) {
            applicationFileRepository.deleteAllByApplicationId(applicationDTO.getId());
            applicationDTO
                .getApplicationFileDTOList()
                .forEach(cooperationContractFileDTO -> {
                    cooperationContractFileDTO.setId(null);
                    cooperationContractFileDTO.setApplicationId(applicationDTO.getId());
                    ApplicationFileDTO save = create(cooperationContractFileDTO);
                    cooperationContractFileDTO.setId(save.getId());
                });
        }
        return applicationDTO;
    }
    public List<ApplicationFileDTO> findAllListByApplicationId(Long applicationId) {
        log.debug("Request to get all ApplicationDTO list");
        return applicationFileRepository
            .findAllByApplicationId(applicationId)
            .stream()
            .map(applicationFile -> {
                ApplicationFileDTO applicationFileDTO = applicationFileMapper.toDto(applicationFile);
//                FileStorageDTO fileStorageDTO = fileServiceFeign.getOneByHash(applicationFileDTO.getFileStorageHashId());
//                if (fileStorageDTO != null) {
//                    applicationFileDTO.setName(fileStorageDTO.getName());
//                    applicationFileDTO.setFileSize(fileStorageDTO.getFileSize());
//                    applicationFileDTO.setExtension(fileStorageDTO.getExtension());
//                    applicationFileDTO.setHashId(fileStorageDTO.getHashId());
//                }
                return applicationFileDTO;
            })
            .collect(Collectors.toList());
    }

}
