package org.example.controller;

import org.example.model.Battery;
import org.example.model.Inverter;
import org.example.repository.BatteryRepository;
import org.example.repository.InverterRepository;
import org.example.service.InverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final InverterService inverterService;
    private final InverterRepository inverterRepository;
    private final BatteryRepository batteryRepository;

    public DataSeeder(InverterService inverterService, InverterRepository inverterRepository, BatteryRepository batteryRepository) {
        this.inverterService = inverterService;
        this.inverterRepository = inverterRepository;
        this.batteryRepository = batteryRepository;
    }

    @Override
    public void run(String... args) {
        if (inverterRepository.count() == 0) {
            Inverter inv = new Inverter();
            inv.setManufacturer("Victron Energy");
            inv.setMaxLoadWattage(3000);
            inverterRepository.save(inv);

            Battery bat1 = new Battery();
            bat1.setManufacturer("Pylontech");
            bat1.setCapacityWh(5000);
            bat1.setChemistry("LiFePO4");
            batteryRepository.save(bat1);

            Battery bat2 = new Battery();
            bat2.setManufacturer("Samsung");
            bat2.setCapacityWh(3000);
            bat2.setChemistry("LiFePO4");
            batteryRepository.save(bat2);

            System.out.println("--- Seeding completed ---");

            Long inverterId = 1L;
            Long battery1Id = 1L;
            Long battery2Id = 2L;

            inverterService.addBatteryToInverter(inverterId, battery1Id);
            inverterService.addBatteryToInverter(inverterId, battery2Id);
        }
        System.out.println("--- N+1 Problem ---");
        inverterService.showNPlusOneProblem();
        System.out.println("--- N+1 Solved ---");
        inverterService.showNPlusOneSolved();
    }
}
