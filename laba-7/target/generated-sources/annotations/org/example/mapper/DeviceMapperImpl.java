package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.DeviceDTO;
import org.example.model.Device;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-11T20:37:49+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class DeviceMapperImpl implements DeviceMapper {

    @Override
    public DeviceDTO toDto(Device device) {
        if ( device == null ) {
            return null;
        }

        DeviceDTO deviceDTO = new DeviceDTO();

        deviceDTO.setId( device.getId() );
        deviceDTO.setName( device.getName() );
        deviceDTO.setPowerDrawWattage( device.getPowerDrawWattage() );

        return deviceDTO;
    }

    @Override
    public Device toEntity(DeviceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Device device = new Device();

        device.setId( dto.getId() );
        device.setName( dto.getName() );
        device.setPowerDrawWattage( dto.getPowerDrawWattage() );

        return device;
    }
}
