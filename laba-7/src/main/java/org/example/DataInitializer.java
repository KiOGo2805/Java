package org.example;

import org.example.model.*;
import org.example.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BatteryService batteryService;
    private final InverterService inverterService;
    private final DeviceService deviceService;
    private final PowerOutageService outageService;
    private final ConsumptionLogService logService;

    public DataInitializer(BatteryService batteryService, InverterService inverterService,
                           DeviceService deviceService, PowerOutageService outageService,
                           ConsumptionLogService logService) {
        this.batteryService = batteryService;
        this.inverterService = inverterService;
        this.deviceService = deviceService;
        this.outageService = outageService;
        this.logService = logService;
    }

    @Override
    public void run(String... args) {
        // 1. Заповнюємо Акумулятори
        batteryService.createBattery(new Battery(null, "EcoFlow", 1024, "LiFePO4"));
        batteryService.createBattery(new Battery(null, "Bluetti", 2048, "LiFePO4"));

        // 2. Заповнюємо Інвертори
        inverterService.createInverter(new Inverter(null, "Must", 3000));
        inverterService.createInverter(new Inverter(null, "Victron", 1200));

        // 3. Заповнюємо Пристрої
        deviceService.createDevice(new Device(null, "Роутер Mikrotik", 15));
        deviceService.createDevice(new Device(null, "Ігровий ПК", 450));
        deviceService.createDevice(new Device(null, "Холодильник", 150));

        // 4. Заповнюємо Відключення
        outageService.createOutage(new PowerOutage(null, "10:00", "14:00"));
        outageService.createOutage(new PowerOutage(null, "18:00", "22:00"));

        // 5. Заповнюємо Логи
        logService.createLog(new ConsumptionLog(null, 1L, 60)); // Роутер за 4 години

        System.out.println(">>> Дані для захисту успішно завантажені в пам'ять!");
    }
}