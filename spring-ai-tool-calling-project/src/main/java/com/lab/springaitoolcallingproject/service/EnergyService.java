package com.lab.springaitoolcallingproject.service;

import java.time.LocalDateTime;
import java.util.List;

import com.lab.springaitoolcallingproject.model.EnergyUsage;
import com.lab.springaitoolcallingproject.repository.EnergyUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyService {

    @Autowired
    private EnergyUsageRepository energyUsageRepository;

    @Autowired
    private DeviceService deviceService;

    public List<EnergyUsage> getAllEnergyUsage() {
        return energyUsageRepository.findAll();
    }

    public List<EnergyUsage> getEnergyUsageByPeriod(String period) {
        return energyUsageRepository.findByPeriod(period);
    }

    public EnergyUsage recordEnergyUsage(String period, Double consumption, Double cost) {
        EnergyUsage usage = new EnergyUsage(period, consumption, cost);
        return energyUsageRepository.save(usage);
    }

    public Double getCurrentEnergyConsumption() {
        return deviceService.getTotalEnergyConsumption();
    }

    public Double getTotalCostByPeriod(String period) {
        Double total = energyUsageRepository.getTotalCostByPeriod(period);
        return total != null ? total : 0.0;
    }

    public List<EnergyUsage> getEnergyUsageInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return energyUsageRepository.findByDateRange(startDate, endDate);
    }
}