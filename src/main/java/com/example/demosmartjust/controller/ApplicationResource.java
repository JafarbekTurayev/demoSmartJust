package com.example.demosmartjust.controller;


import com.example.demosmartjust.dto.ApplicationDTO;
import com.smartsoft.smartofficebackend.domain.integration.smartJust.ApplicationFilterParam;
import com.smartsoft.smartofficebackend.repository.integration.smartJust.ApplicationRepository;
import com.smartsoft.smartofficebackend.service.dto.integration.smartJust.ApplicationDTO;
import com.smartsoft.smartofficebackend.service.integration.smartJust.ApplicationService;
import com.smartsoft.smartofficebackend.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ApplicationResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationResource.class);

    private static final String ENTITY_NAME = "jhsBackendServiceApp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApplicationService applicationService;

    private final ApplicationRepository applicationRepository;

    public ApplicationResource(ApplicationService applicationService, ApplicationRepository applicationRepository) {
        this.applicationService = applicationService;
        this.applicationRepository = applicationRepository;
    }


    @PostMapping("/application")
    public ResponseEntity<ApplicationDTO> createApp(@RequestBody ApplicationDTO applicationDTO) throws URISyntaxException {
        log.debug("REST request to save App : {}", applicationDTO);
        if (applicationDTO.getId() != null) {
            throw new BadRequestAlertException("A new app cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicationDTO result = applicationService.create(applicationDTO);
        return ResponseEntity
                .created(new URI("/api/application/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/application/{id}")
    public ResponseEntity<ApplicationDTO> updateApp(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody ApplicationDTO applicationDTO)
            throws URISyntaxException {
        log.debug("REST request to update App : {}, {}", id, applicationDTO);
        if (applicationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, applicationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!applicationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ApplicationDTO result = applicationService.update(applicationDTO);
        return ResponseEntity
                .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicationDTO.getId().toString()))
                .body(result);
    }


    @PatchMapping(value = "/application/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ApplicationDTO> partialUpdateApp(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody ApplicationDTO applicationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update App partially : {}, {}", id, applicationDTO);
        if (applicationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, applicationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!applicationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApplicationDTO> result = applicationService.partialUpdate(applicationDTO);

//        return ResponseEntity.ok().body(result.orElse(new ApplicationDTO()));
        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicationDTO.getId().toString())
        );
    }

    @PostMapping("/application/width-resolution")
    public ResponseEntity<DocWidthResolution> createDocWidthResolution(@Valid @RequestBody ApplicationDTO applicationDTO)
            throws URISyntaxException {
        log.debug("REST request to save Resolution : {}", applicationDTO);
        //TODO DocWidthResolution classni hosil qilib olish kerak do oborotga tushirish un

        if (resolutionWidthDoc.getDocDTO().getId() != null) {
            throw new BadRequestAlertException("A new resolutions-width-doc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocWidthResolution result = docService.createDocWidthResolution(resolutionWidthDoc);
        return ResponseEntity
                .created(new URI("/api/resolutions/" + result.getResolutionDTO().getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getResolutionDTO().getId().toString()))
                .body(result);
    }

    @GetMapping("/application/all-list")
    public ResponseEntity<List<ApplicationDTO>> getAllApps(@ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Apps");
        Page<ApplicationDTO> page = applicationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/application/paging")
    public ResponseEntity<List<ApplicationDTO>> findAllPagingList(@RequestBody ApplicationFilterParam filter) {
        log.debug("REST request to get a page of DocDTO");
        Page<ApplicationDTO> page = applicationService.findAllPaging(filter);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<ApplicationDTO> getApp(@PathVariable Long id) {
        log.debug("REST request to get App : {}", id);
        Optional<ApplicationDTO> applicationDTO = applicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationDTO);
    }

    @PostMapping("/application/confirm")
    public ResponseEntity<ApplicationDTO> appConfirm(@PathVariable(value = "id", required = false) final Long id,
                                                     @NotNull @RequestBody ApplicationDTO applicationDTO){
        log.debug("REST request to post App confirm : {}", id);
        applicationService.
    }

}
