package org.example.mapper;

import org.example.dto.DeviceDTO;
import org.example.model.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceDTO toDto(Device device);

    Device toEntity(DeviceDTO dto);
}