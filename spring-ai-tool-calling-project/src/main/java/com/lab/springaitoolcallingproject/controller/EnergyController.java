package com.lab.springaitoolcallingproject.controller;

import java.util.List;
import java.util.Map;

import com.lab.springaitoolcallingproject.model.EnergyUsage;
import com.lab.springaitoolcallingproject.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/energy")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class EnergyController {
    
    @Autowired
    private EnergyService energyService;
    
    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> getCurrentConsumption() {
        Double consumption = energyService.getCurrentEnergyConsumption();
        return ResponseEntity.ok(Map.of(
            "currentConsumption", consumption,
            "unit", "watts",
            "timestamp", System.currentTimeMillis()
        ));
    }
    
    @GetMapping("/usage")
    public ResponseEntity<List<EnergyUsage>> getAllUsage() {
        return ResponseEntity.ok(energyService.getAllEnergyUsage());
    }
    
    @GetMapping("/usage/{period}")
    public ResponseEntity<List<EnergyUsage>> getUsageByPeriod(@PathVariable String period) {
        return ResponseEntity.ok(energyService.getEnergyUsageByPeriod(period));
    }
    
    @GetMapping("/cost/{period}")
    public ResponseEntity<Map<String, Object>> getCostByPeriod(@PathVariable String period) {
        Double cost = energyService.getTotalCostByPeriod(period);
        return ResponseEntity.ok(Map.of(
            "period", period,
            "totalCost", cost,
            "currency", "USD"
        ));
    }
    
    @PostMapping("/usage")
    public ResponseEntity<EnergyUsage> recordUsage(@RequestBody Map<String, Object> request) {
        try {
            String period = (String) request.get("period");
            Double consumption = Double.valueOf(request.get("consumption").toString());
            Double cost = Double.valueOf(request.get("cost").toString());

            EnergyUsage usage = energyService.recordEnergyUsage(period, consumption, cost);
            return ResponseEntity.ok(usage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}