package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.ConsumptionLogDTO;
import org.example.model.ConsumptionLog;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-20T17:47:25+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class ConsumptionLogMapperImpl implements ConsumptionLogMapper {

    @Override
    public ConsumptionLogDTO toDto(ConsumptionLog log) {
        if ( log == null ) {
            return null;
        }

        ConsumptionLogDTO consumptionLogDTO = new ConsumptionLogDTO();

        return consumptionLogDTO;
    }

    @Override
    public ConsumptionLog toEntity(ConsumptionLogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConsumptionLog consumptionLog = new ConsumptionLog();

        return consumptionLog;
    }
}
