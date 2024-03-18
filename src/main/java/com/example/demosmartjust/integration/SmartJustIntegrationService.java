package com.example.demosmartjust.integration;

import com.example.demosmartjust.integration.auth.AuthToken;
import com.example.demosmartjust.integration.confirm.SmartJustConfirmRequest;
import com.example.demosmartjust.integration.confirm.SmartJustConfirmResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SmartJustIntegrationService {

    private final RestTemplate restTemplate;
    @Value("${integration.smart-just.base-url}")
    private String smartJustBaseUrl;
    @Value("${integration.smart-just.authKey}")
    private String authKey;

    public SmartJustIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpEntity httpEntity(Object param) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(param, headers);
    }

    public HttpEntity httpBearerEntity(Object param, String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        return new HttpEntity<>(param, headers);
    }

    public HttpEntity httpBearerFile(MultipartFile file, String accessToken) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        try {
            body.add("file", new FileSystemResource(convert(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);
        return new HttpEntity<>(body, headers);
    }

    private File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public AuthToken getToken() {
        // TODO currentUserdan pinflni olish kerak
        String pinfl = "32909995730022";
        Map<String, Object> person = new HashMap<>();
        person.put("pin", pinfl);
        return restTemplate.postForObject(smartJustBaseUrl + String.format("id-gov/log-in?key=%s", authKey), httpEntity(person), AuthToken.class);
    }

    public SmartJustConfirmResponse applicationConfirm(Long id, SmartJustConfirmRequest smartJustConfirmRequest) {
        return restTemplate.exchange(smartJustBaseUrl + "back/application/confirm/" + id, HttpMethod.PUT, httpBearerEntity(smartJustConfirmRequest, getToken().getData()), SmartJustConfirmResponse.class).getBody();
    }

    public SmartJustConfirmResponse applicationClose(Long id, SmartJustConfirmRequest smartJustConfirmRequest) {
        return restTemplate.exchange(smartJustBaseUrl + "back/application/close/" + id, HttpMethod.PUT, httpBearerEntity(smartJustConfirmRequest, getToken().getData()), SmartJustConfirmResponse.class).getBody();
    }

    public SmartJustUploadResponse uploadResultFile(MultipartFile file) {
        return restTemplate.postForEntity(smartJustBaseUrl + "front/open-source/upload", httpBearerFile(file, getToken().getData()), SmartJustUploadResponse.class).getBody();
    }

}
