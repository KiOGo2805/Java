package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.PowerOutageDTO;
import org.example.model.PowerOutage;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-20T18:20:09+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class PowerOutageMapperImpl implements PowerOutageMapper {

    @Override
    public PowerOutageDTO toDto(PowerOutage outage) {
        if ( outage == null ) {
            return null;
        }

        PowerOutageDTO powerOutageDTO = new PowerOutageDTO();

        powerOutageDTO.setId( outage.getId() );
        powerOutageDTO.setStartTime( outage.getStartTime() );
        powerOutageDTO.setEndTime( outage.getEndTime() );

        return powerOutageDTO;
    }

    @Override
    public PowerOutage toEntity(PowerOutageDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PowerOutage powerOutage = new PowerOutage();

        powerOutage.setId( dto.getId() );
        powerOutage.setStartTime( dto.getStartTime() );
        powerOutage.setEndTime( dto.getEndTime() );

        return powerOutage;
    }
}
