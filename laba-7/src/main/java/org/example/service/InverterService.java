package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Inverter;
import org.example.repository.InverterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InverterService {

    private final InverterRepository repository;

    public InverterService(InverterRepository repository) {
        this.repository = repository;
    }

    public List<Inverter> getAllInverters() {
        return repository.findAll();
    }

    public Optional<Inverter> getInverterById(Long id) {
        return repository.findById(id);
    }

    public Inverter createInverter(Inverter inverter) {
        return repository.save(inverter);
    }

    public Optional<Inverter> updateInverter(Long id, Inverter updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setManufacturer(updatedData.getManufacturer());
            existing.setMaxLoadWattage(updatedData.getMaxLoadWattage());
            return repository.save(existing);
        });
    }

    public boolean deleteInverter(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public void testNPlusOne() {
        System.out.println("--- ПОЧАТОК ТЕСТУ N+1 ---");
        List<Inverter> inverters = repository.findAllWithBatteries();

        for (Inverter inverter : inverters) {
            System.out.println("Інвертор: " + inverter.getManufacturer() + ", батарей: " + inverter.getBatteries().size());
        }
        System.out.println("--- КІНЕЦЬ ТЕСТУ N+1 ---");
    }
}