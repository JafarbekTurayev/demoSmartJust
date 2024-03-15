package com.example.demosmartjust.controller;

import com.example.demosmartjust.dto.ApplicationDTO;
import com.example.demosmartjust.entity.ApplicationFilterParam;
import com.example.demosmartjust.repository.ApplicationRepository;
import com.example.demosmartjust.service.ApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @GetMapping("/application/all-list")
    public ResponseEntity<List<ApplicationDTO>> getAllApps(@ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Apps");
        Page<ApplicationDTO> page = applicationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
        return ResponseEntity.ok().body(page.getContent());
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
        ApplicationDTO applicationDTO = applicationService.findOne(id);
        if (applicationDTO != null) {
            return ResponseEntity.ok().body(applicationDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
