package org.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.example.model.Battery;
import org.example.model.Inverter;
import org.example.repository.BatteryRepository;
import org.example.repository.InverterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.Optional;

@Service
public class InverterService {

    private final InverterRepository inverterRepository;
    private final BatteryRepository batteryRepository;

    public InverterService(InverterRepository inverterRepository, BatteryRepository batteryRepository) {
        this.inverterRepository = inverterRepository;
        this.batteryRepository = batteryRepository;
    }

    public List<Inverter> getAllInverters() {
        return inverterRepository.findAll();
    }

    public Optional<Inverter> getInverterById(Long id) {
        return inverterRepository.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Inverter createInverter(Inverter inverter) {
        return inverterRepository.save(inverter);
    }

    public Optional<Inverter> updateInverter(Long id, Inverter updatedData) {
        return inverterRepository.findById(id).map(existing -> {
            existing.setManufacturer(updatedData.getManufacturer());
            existing.setMaxLoadWattage(updatedData.getMaxLoadWattage());
            return inverterRepository.save(existing);
        });
    }

    public boolean deleteInverter(Long id) {
        if (inverterRepository.existsById(id)) {
            inverterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public void addBatteryToInverter(Long inverterId, Long batteryId) {
        Inverter inverter = inverterRepository.findById(inverterId)
                .orElseThrow(() -> new RuntimeException("Інвертор не знайдено"));

        Battery battery = batteryRepository.findById(batteryId)
                .orElseThrow(() -> new RuntimeException("Батарею не знайдено"));

        inverter.getBatteries().add(battery);
        battery.getInverters().add(inverter);
        inverterRepository.save(inverter);
    }

    public Page<Inverter> getInvertersPaginated(int page, int size) {
        return inverterRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional
    public void showNPlusOneProblem() {
        List<Inverter> inverters = inverterRepository.findAll();

        for (Inverter inverter : inverters) {
            System.out.println(inverter.getManufacturer() + ": " + inverter.getBatteries().size());
        }
    }

    @Transactional
    public void showNPlusOneSolved() {
        List<Inverter> inverters = inverterRepository.findAllWithBatteries();

        for (Inverter inverter : inverters) {
            System.out.println(inverter.getManufacturer() + ": " + inverter.getBatteries().size());
        }
    }
}