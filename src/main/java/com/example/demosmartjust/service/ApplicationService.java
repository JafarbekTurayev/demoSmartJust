package com.example.demosmartjust.service;

import com.example.demosmartjust.dto.ApplicationDTO;
import com.example.demosmartjust.entity.Application;
import com.example.demosmartjust.entity.ApplicationFilterParam;
import com.example.demosmartjust.entity.ParamUtil;
import com.example.demosmartjust.mapper.ApplicationMapper;
import com.example.demosmartjust.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ApplicationService {
    private final Logger log = LoggerFactory.getLogger(ApplicationService.class);

    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationMapper applicationMapper, ApplicationRepository applicationRepository) {
        this.applicationMapper = applicationMapper;
        this.applicationRepository = applicationRepository;
    }

    public ApplicationDTO create(ApplicationDTO applicationDTO) {
        log.debug("Request to save App : {}", applicationDTO);
        Application save = applicationRepository.save(applicationMapper.toEntity(applicationDTO));
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
    public ApplicationDTO findOne(Long id) {
        log.debug("Request to get App : {}", id);
        return applicationRepository.findById(id).map(applicationMapper::toDto).orElse(new ApplicationDTO());
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
//                    ApplicationDTO res = fillCooperationDTO(application, applicationDTO);
                    return applicationDTO;
                });
    }
}
