package com.example.demosmartjust.mapper;

import com.example.demosmartjust.dto.ApplicationFileDTO;
import com.example.demosmartjust.entity.ApplicationFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Mapper for the entity {@link com.example.demosmartjust.entity.ApplicationFile} and its DTO {@link com.example.demosmartjust.dto.ApplicationFileDTO}.
 */
@Mapper(componentModel = "spring",uses = {ApplicationMapper.class})
public interface ApplicationFileMapper extends EntityMapper<ApplicationFileDTO, ApplicationFile> {

    @Mapping(target = "applicationId", source = "application.id")
    ApplicationFileDTO toDto(ApplicationFile s);

    @Mapping(source = "applicationId", target = "application")
    ApplicationFile toEntity(ApplicationFileDTO applicationFileDTO);

    default ApplicationFile fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApplicationFile applicationFile = new ApplicationFile();
        applicationFile.setId(id);
        return applicationFile;
    }

}
