package com.example.demosmartjust.integration;

import com.example.demosmartjust.integration.auth.AuthToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/integration-smart-just")
public class SmartJustIntegrationResource {

    private final SmartJustIntegrationService smartJustIntegrationService;

    public SmartJustIntegrationResource(SmartJustIntegrationService smartJustIntegrationService) {
        this.smartJustIntegrationService = smartJustIntegrationService;
    }

    @PostMapping("/get-token")
    public ResponseEntity getToken() {
        AuthToken result = smartJustIntegrationService.getToken();
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/upload-result-file", consumes = "multipart/form-data")
    public ResponseEntity uploadFile(@RequestPart MultipartFile file) {
        SmartJustUploadResponse result = smartJustIntegrationService.uploadResultFile(file);
        return ResponseEntity.ok(result);
    }

}
