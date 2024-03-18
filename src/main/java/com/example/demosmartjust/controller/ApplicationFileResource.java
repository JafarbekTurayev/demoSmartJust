package com.example.demosmartjust.controller;


import com.smartsoft.smartofficebackend.domain.integration.smartJust.ApplicationFileFilterParam;
import com.smartsoft.smartofficebackend.repository.integration.smartJust.ApplicationFileRepository;
import com.smartsoft.smartofficebackend.service.dto.integration.smartJust.ApplicationFileDTO;
import com.smartsoft.smartofficebackend.service.integration.smartJust.ApplicationFileService;
import com.smartsoft.smartofficebackend.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

/**
 * REST controller for managing {@link com.example.demosmartjust.entity.ApplicationFile}.
 */
@RestController
@RequestMapping("/api")
public class ApplicationFileResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationFileResource.class);

    private static final String ENTITY_NAME = "smartOfficeBackendApplicationFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApplicationFileService applicationFileService;
    private final ApplicationFileRepository applicationFileRepository;

    public ApplicationFileResource(ApplicationFileService applicationFileService, ApplicationFileRepository applicationFileRepository) {
        this.applicationFileService = applicationFileService;
        this.applicationFileRepository = applicationFileRepository;
    }


    @PostMapping("/application-files")
    public ResponseEntity<ApplicationFileDTO> createApplicationFile(@Valid @RequestBody ApplicationFileDTO applicationFileDTO) throws URISyntaxException {
        log.debug("REST request to save ApplicationFile : {}", applicationFileDTO);
        if (applicationFileDTO.getId() != null) {
            throw new BadRequestAlertException("A new ApplicationFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicationFileDTO result = applicationFileService.create(applicationFileDTO);
        return ResponseEntity
                .created(new URI("/api/application-files/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/application-files/{id}")
    public ResponseEntity<ApplicationFileDTO> updateApplicationFile(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody ApplicationFileDTO applicationFileDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ApplicationFile : {}, {}", id, applicationFileDTO);
        if (applicationFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, applicationFileDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!applicationFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ApplicationFileDTO result = applicationFileService.update(applicationFileDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicationFileDTO.getId().toString()))
                .body(result);
    }

    @PatchMapping(value = "/application-files/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ApplicationFileDTO> partialUpdateApplicationFile(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @RequestBody ApplicationFileDTO applicationFileDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ApplicationFile partially : {}, {}", id, applicationFileDTO);
        if (applicationFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, applicationFileDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!applicationFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApplicationFileDTO> result = applicationFileService.partialUpdate(applicationFileDTO);

        return ResponseUtil.wrapOrNotFound(
                result,
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, applicationFileDTO.getId().toString())
        );
//        return ResponseEntity.ok().body(result.get());
    }

    @GetMapping("/application-files")
    public ResponseEntity<List<ApplicationFileDTO>> getAllApplicationFiles(@ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ApplicationFile");
        Page<ApplicationFileDTO> page = applicationFileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/application-files/{id}")
    public ResponseEntity<ApplicationFileDTO> getApplicationFile(@PathVariable Long id) {
        log.debug("REST request to get ApplicationFile : {}", id);
        Optional<ApplicationFileDTO> applicationFileDTO = applicationFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationFileDTO);
    }

    @DeleteMapping("/application-files/{id}")
    public ResponseEntity<Void> deleteApplicationFile(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationFile : {}", id);
        applicationFileService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    @PostMapping("/application-files/paging")
    public ResponseEntity<List<ApplicationFileDTO>> findAllPagingList(@RequestBody ApplicationFileFilterParam filter) {
        log.debug("REST request to get a page of DocDTO");
        Page<ApplicationFileDTO> page = applicationFileService.findAllPaging(filter);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/application-files/all-list")
    public ResponseEntity<List<ApplicationFileDTO>> getAllList() {
        log.debug("REST request to get a page of DocDTO");
        List<ApplicationFileDTO> result = applicationFileService.findAllList();
        return ResponseEntity.ok(result);
    }
}
