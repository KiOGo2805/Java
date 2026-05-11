package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.ConsumptionLogDTO;
import org.example.model.ConsumptionLog;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-11T20:37:49+0300",
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

        consumptionLogDTO.setId( log.getId() );
        consumptionLogDTO.setDeviceId( log.getDeviceId() );
        consumptionLogDTO.setTotalWhConsumed( log.getTotalWhConsumed() );

        return consumptionLogDTO;
    }

    @Override
    public ConsumptionLog toEntity(ConsumptionLogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ConsumptionLog consumptionLog = new ConsumptionLog();

        consumptionLog.setId( dto.getId() );
        consumptionLog.setDeviceId( dto.getDeviceId() );
        consumptionLog.setTotalWhConsumed( dto.getTotalWhConsumed() );

        return consumptionLog;
    }
}
