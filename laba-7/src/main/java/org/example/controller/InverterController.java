package org.example.controller;

import org.example.model.Inverter;
import org.example.service.InverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Кажемо, що спілкуємось тільки через JSON
@RequestMapping("/api/inverters") // Адреса нашого "столика"
public class InverterController {

    private final InverterService service; // Рація для зв'язку з кухнею

    public InverterController(InverterService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inverter> getAll() {
        return service.getAllInverters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inverter> getById(@PathVariable Long id) {
        return service.getInverterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inverter create(@RequestBody Inverter inverter) {
        return service.createInverter(inverter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inverter> update(@PathVariable Long id, @RequestBody Inverter inverter) {
        return service.updateInverter(id, inverter)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteInverter(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}