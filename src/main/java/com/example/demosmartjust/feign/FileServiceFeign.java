package com.example.demosmartjust.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@AuthorizedFeignClient(name = "${feign.name.smartoffice-file-service}")
public interface FileServiceFeign {

    @GetMapping(value = "/api/file-storages/hash/{hashId}")
    FileStorageDTO getOneByHash(@PathVariable String hashId);

    @GetMapping(value = "/api/file-storages/preview/{hashId}")
    FileStorageDTO getOneByHashWithByte(@PathVariable String hashId);

    @GetMapping(value = "/api/file-storages/create-take-action-file/{takeActionJobId}")
    FileStorageDTO createTakeActionFile(@PathVariable Long takeActionJobId);

    @GetMapping(value = "/api/file-storages/create-call-center-file/{docId}")
    FileStorageDTO createCallCenterFile(@PathVariable Long docId);
}
