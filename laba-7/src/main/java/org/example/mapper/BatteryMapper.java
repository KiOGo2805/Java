package org.example.mapper;

import org.example.dto.BatteryDTO;
import org.example.model.Battery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatteryMapper {

    BatteryDTO toDto(Battery battery);

    Battery toEntity(BatteryDTO dto);
}