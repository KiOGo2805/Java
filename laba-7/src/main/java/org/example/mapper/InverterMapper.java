package org.example.mapper;

import org.example.dto.InverterDTO;
import org.example.model.Inverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InverterMapper {

    InverterDTO toDto(Inverter inverter);

    Inverter toEntity(InverterDTO dto);
}