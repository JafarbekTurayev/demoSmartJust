package com.example.demosmartjust.mapper;

import com.example.demosmartjust.dto.ApplicationDTO;
import com.example.demosmartjust.entity.Application;
import com.example.demosmartjust.integration.confirm.SmartJustName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring"
)
public interface ApplicationMapper extends EntityMapper<ApplicationDTO, Application> {

    @Mappings({
            @Mapping(target = "address", expression = "java(convertStringToAddress(application.getAddress()))"),
            @Mapping(source = "fio", target = "firstName", qualifiedByName = "mapFirstName"),
            @Mapping(source = "fio", target = "lastName", qualifiedByName = "mapLastName"),
            @Mapping(source = "fio", target = "middleName", qualifiedByName = "mapMiddleName")
    })
    ApplicationDTO toDto(Application application);


    @Mappings({
            @Mapping(target = "address", expression = "java(convertAddressToString(applicationDTO.getAddress()))")
    })
    Application toEntity(ApplicationDTO applicationDTO);

    default Application fromId(Long id) {
        if (id == null) {
            return null;
        }
        Application doc = new Application();
        doc.setId(id);
        return doc;
    }

    default SmartJustName convertStringToAddress(String address) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(address, SmartJustName.class);
        } catch (JsonProcessingException e) {
            // Обработка ошибки
            e.printStackTrace();
            return null;
        }
    }

    default String convertAddressToString(SmartJustName address) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(address);
        } catch (JsonProcessingException e) {
            // Обработка ошибки
            e.printStackTrace();
            return null;
        }
    }
    @Named("mapFirstName")
    default String mapFirstName(String fio) {
        return fio.split("\\s+")[0];
    }

    @Named("mapLastName")
    default String mapLastName(String fio) {
        String[] parts = fio.split("\\s+");
        return parts.length > 1 ? parts[parts.length - 1] : "";
    }

    @Named("mapMiddleName")
    default String mapMiddleName(String fio) {
        String[] parts = fio.split("\\s+");
        return parts.length > 2 ? parts[1] : "";
    }
}
