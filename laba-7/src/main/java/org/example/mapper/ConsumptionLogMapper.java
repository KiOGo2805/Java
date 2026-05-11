package org.example.mapper;

import org.example.dto.ConsumptionLogDTO;
import org.example.model.ConsumptionLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumptionLogMapper {
    ConsumptionLogDTO toDto(ConsumptionLog log);
    ConsumptionLog toEntity(ConsumptionLogDTO dto);
}