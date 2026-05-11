package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.InverterDTO;
import org.example.model.Inverter;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-11T20:37:49+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class InverterMapperImpl implements InverterMapper {

    @Override
    public InverterDTO toDto(Inverter inverter) {
        if ( inverter == null ) {
            return null;
        }

        InverterDTO inverterDTO = new InverterDTO();

        inverterDTO.setId( inverter.getId() );
        inverterDTO.setManufacturer( inverter.getManufacturer() );
        inverterDTO.setMaxLoadWattage( inverter.getMaxLoadWattage() );

        return inverterDTO;
    }

    @Override
    public Inverter toEntity(InverterDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Inverter inverter = new Inverter();

        inverter.setId( dto.getId() );
        inverter.setManufacturer( dto.getManufacturer() );
        inverter.setMaxLoadWattage( dto.getMaxLoadWattage() );

        return inverter;
    }
}
