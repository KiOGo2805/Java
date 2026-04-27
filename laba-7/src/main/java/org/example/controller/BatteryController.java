package org.example.controller;

import org.example.model.Battery;
import org.example.service.BatteryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/batteries")
public class BatteryController {

    private final BatteryService service;

    public BatteryController(BatteryService service) {
        this.service = service;
    }

    // 1. Отримати всі батареї (GET http://localhost:8080/api/batteries)
    @GetMapping
    public List<Battery> getAll() {
        return service.getAllBatteries();
    }

    // 2. Отримати батарею за ID (GET http://localhost:8080/api/batteries/1)
    @GetMapping("/{id}")
    public ResponseEntity<Battery> getById(@PathVariable Long id) {
        return service.getBatteryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Створити нову батарею (POST http://localhost:8080/api/batteries)
    @PostMapping
    public Battery create(@RequestBody Battery battery) {
        return service.createBattery(battery);
    }

    // 4. Оновити батарею (PUT http://localhost:8080/api/batteries/1)
    @PutMapping("/{id}")
    public ResponseEntity<Battery> update(@PathVariable Long id, @RequestBody Battery battery) {
        return service.updateBattery(id, battery)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Видалити батарею (DELETE http://localhost:8080/api/batteries/1)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteBattery(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}