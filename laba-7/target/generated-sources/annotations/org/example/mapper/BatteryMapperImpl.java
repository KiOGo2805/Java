package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.BatteryDTO;
import org.example.model.Battery;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-20T17:47:25+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class BatteryMapperImpl implements BatteryMapper {

    @Override
    public BatteryDTO toDto(Battery battery) {
        if ( battery == null ) {
            return null;
        }

        BatteryDTO batteryDTO = new BatteryDTO();

        return batteryDTO;
    }

    @Override
    public Battery toEntity(BatteryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Battery battery = new Battery();

        return battery;
    }
}
