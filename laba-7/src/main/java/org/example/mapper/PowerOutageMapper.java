package org.example.mapper;

import org.example.dto.PowerOutageDTO;
import org.example.model.PowerOutage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PowerOutageMapper {
    PowerOutageDTO toDto(PowerOutage outage);
    PowerOutage toEntity(PowerOutageDTO dto);
}