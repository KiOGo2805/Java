package org.example.repository;

import org.example.model.Battery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BatteryJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public BatteryJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Battery> findBatteriesByMinCapacity(int minCapacityWh) {
        String sql = "SELECT * FROM batteries WHERE capacity_wh >= ?";

        return jdbcTemplate.query(sql, batteryRowMapper, minCapacityWh);
    }

    private final RowMapper<Battery> batteryRowMapper = (rs, rowNum) -> {
        Battery battery = new Battery();
        battery.setId(rs.getLong("id"));
        battery.setManufacturer(rs.getString("manufacturer"));
        battery.setCapacityWh(rs.getInt("capacity_wh"));
        battery.setChemistry(rs.getString("chemistry"));
        return battery;
    };
}