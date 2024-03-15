package com.example.demosmartjust.integration;

import com.example.demosmartjust.dto.ApplicationConfirmRequestDTO;
import com.example.demosmartjust.dto.ApplicationFileDTO;
import com.example.demosmartjust.entity.ApplicationFileType;
import com.example.demosmartjust.feign.FileServiceFeign;
import com.example.demosmartjust.feign.FileStorageDTO;
import com.example.demosmartjust.integration.application.ResultResponse;
import com.example.demosmartjust.integration.application.SmartJustPageableRequest;
import com.example.demosmartjust.integration.auth.AuthToken;
import com.example.demosmartjust.integration.confirm.SmartJustConfirmRequest;
import com.example.demosmartjust.integration.confirm.SmartJustConfirmResponse;
import com.example.demosmartjust.service.ApplicationFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
//@Transactional
public class SmartJustIntegrationService {

    private final RestTemplate restTemplate;
    private final FileServiceFeign fileServiceFeign;
    private final ApplicationFileService applicationFileService;
    @Value("${integration.smart-just.base-url}")
    private String smartJustBaseUrl;
    @Value("${integration.smart-just.authKey}")
    private String authKey;

    public SmartJustIntegrationService(RestTemplate restTemplate, FileServiceFeign fileServiceFeign, ApplicationFileService applicationFileService) {
        this.restTemplate = restTemplate;
        this.fileServiceFeign = fileServiceFeign;
        this.applicationFileService = applicationFileService;
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

    public HttpEntity httpBearerFile(MultipartFile file, String accessToken){
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

    public ResultResponse getApplicationList(SmartJustPageableRequest request) {
        return restTemplate.postForObject(smartJustBaseUrl + "back/application/list", httpBearerEntity(request, getToken().getData()), ResultResponse.class);
    }

    public SmartJustConfirmResponse applicationConfirm(Long id, ApplicationConfirmRequestDTO confirmRequest) {

        if (confirmRequest.getFileStorageHashId() != null) {
            //TODO srazi o'zimizni fayl serverdan tortib ulargayam fayl serverga yozish kk
            FileStorageDTO fileStorageDTO = fileServiceFeign.getOneByHashWithByte(confirmRequest.getFileStorageHashId());
            if (fileStorageDTO != null) {
                try {
                    SmartJustUploadResponse uploadResponse = uploadResultFile(convertToFile(fileStorageDTO));

                    ApplicationFileDTO applicationFileDTO = new ApplicationFileDTO();
                    applicationFileDTO.setFileType(ApplicationFileType.CONFIRM.name());
                    applicationFileDTO.setSmartJustFileId(String.valueOf(uploadResponse.getData().getId()));
                    applicationFileService.partialUpdate(applicationFileDTO);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        SmartJustConfirmRequest smartJustConfirmRequest = new SmartJustConfirmRequest();
        smartJustConfirmRequest.setMessage(confirmRequest.getMessage());
        smartJustConfirmRequest.setStatus(confirmRequest.getStatus());
        smartJustConfirmRequest.setFileId(confirmRequest.getFileId());

        return restTemplate.exchange(smartJustBaseUrl + "back/application/confirm/" + id, HttpMethod.PUT, httpBearerEntity(smartJustConfirmRequest, getToken().getData()), SmartJustConfirmResponse.class).getBody();
    }

    public SmartJustConfirmResponse applicationClose(Long id, ApplicationConfirmRequestDTO confirmRequest) {

        if (confirmRequest.getFileStorageHashId() != null) {
            //TODO srazi o'zimizni fayl serverdan tortib ulargayam fayl serverga yozish kk
            FileStorageDTO fileStorageDTO = fileServiceFeign.getOneByHashWithByte(confirmRequest.getFileStorageHashId());
            if (fileStorageDTO != null) {
                try {
                    SmartJustUploadResponse uploadResponse = uploadResultFile(convertToFile(fileStorageDTO));

                    ApplicationFileDTO applicationFileDTO = new ApplicationFileDTO();
                    applicationFileDTO.setFileType(ApplicationFileType.CONFIRM.name());
                    applicationFileDTO.setSmartJustFileId(String.valueOf(uploadResponse.getData().getId()));
                    applicationFileService.partialUpdate(applicationFileDTO);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        SmartJustConfirmRequest smartJustConfirmRequest = new SmartJustConfirmRequest();
        smartJustConfirmRequest.setMessage(confirmRequest.getMessage());
        smartJustConfirmRequest.setStatus(confirmRequest.getStatus());
        smartJustConfirmRequest.setFileId(confirmRequest.getFileId());

        return restTemplate.exchange(smartJustBaseUrl + "back/application/close/" + id, HttpMethod.PUT, httpBearerEntity(smartJustConfirmRequest, getToken().getData()), SmartJustConfirmResponse.class).getBody();
    }

    public SmartJustUploadResponse uploadResultFile(MultipartFile file) {
        return restTemplate.postForEntity(smartJustBaseUrl + "front/open-source/upload", httpBearerFile(file, getToken().getData()), SmartJustUploadResponse.class).getBody();
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
