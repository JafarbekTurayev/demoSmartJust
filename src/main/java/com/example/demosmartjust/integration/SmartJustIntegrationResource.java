package com.example.demosmartjust.integration;

import com.example.demosmartjust.dto.ApplicationConfirmRequestDTO;
import com.example.demosmartjust.integration.application.ResultResponse;
import com.example.demosmartjust.integration.application.SmartJustPageableRequest;
import com.example.demosmartjust.integration.auth.AuthToken;
import com.example.demosmartjust.integration.confirm.SmartJustConfirmRequest;
import com.example.demosmartjust.integration.confirm.SmartJustConfirmResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

//    @PostMapping("/app-list")
//    public ResponseEntity appList( @RequestBody SmartJustPageableRequest request) {
//        ResultResponse result = smartJustIntegrationService.getApplicationList(request);
//        return ResponseEntity.ok(result);
//    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity confirm(@PathVariable Long id, @RequestBody ApplicationConfirmRequestDTO confirmRequest) {
        SmartJustConfirmResponse result = smartJustIntegrationService.applicationConfirm(id, confirmRequest);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/close/{id}")
    public ResponseEntity close(@PathVariable Long id, @RequestBody ApplicationConfirmRequestDTO confirmRequest) {
        SmartJustConfirmResponse result = smartJustIntegrationService.applicationClose(id, confirmRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/upload-result-file", consumes = "multipart/form-data")
    public ResponseEntity uploadFile(@RequestPart MultipartFile file) {
        SmartJustUploadResponse result = smartJustIntegrationService.uploadResultFile(file);
        return ResponseEntity.ok(result);
    }

}
