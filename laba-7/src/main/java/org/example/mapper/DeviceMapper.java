package org.example.mapper;

import org.example.dto.DeviceDTO;
import org.example.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    @Mapping(source = "passport.serialNumber", target = "passportSerialNumber")
    DeviceDTO toDto(Device device);

    Device toEntity(DeviceDTO dto);
}