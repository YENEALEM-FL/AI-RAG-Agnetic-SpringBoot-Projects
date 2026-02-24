package com.lab.springaitoolcallingproject.config;


import com.lab.springaitoolcallingproject.model.Device;
import com.lab.springaitoolcallingproject.model.DeviceType;
import com.lab.springaitoolcallingproject.service.DeviceService;
import com.lab.springaitoolcallingproject.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private EnergyService energyService;

    @Override
    public void run(String... args) throws Exception {
        initializeDevices();
        initializeEnergyData();
        System.out.println("✅ Smart Home data initialized!");
    }

    private void initializeDevices() {
        // Living Room
        createDevice("Living Room Light", DeviceType.LIGHT, "Living Room", true, 75.0, 12.0, "On");
        createDevice("Main Thermostat", DeviceType.THERMOSTAT, "Living Room", true, 22.0, 150.0, "Heating");
        createDevice("TV", DeviceType.APPLIANCE, "Living Room", false, null, 95.0, "Standby");

        // Kitchen
        createDevice("Kitchen Light", DeviceType.LIGHT, "Kitchen", true, 80.0, 15.0, "On");
        createDevice("Refrigerator", DeviceType.APPLIANCE, "Kitchen", true, null, 120.0, "Running");
        createDevice("Coffee Maker", DeviceType.APPLIANCE, "Kitchen", false, null, 45.0, "Standby");

        // Bedroom
        createDevice("Bedroom Light", DeviceType.LIGHT, "Bedroom", false, 60.0, 10.0, "Off");
        createDevice("Bedroom Thermostat", DeviceType.THERMOSTAT, "Bedroom", true, 20.0, 100.0, "Heating");

        // Security
        createDevice("Front Door Camera", DeviceType.SECURITY, "Entrance", true, null, 8.0, "Recording");
        createDevice("Front Door Lock", DeviceType.SECURITY, "Entrance", true, null, 2.0, "Locked");
        createDevice("Back Door Lock", DeviceType.SECURITY, "Backyard", false, null, 2.0, "Unlocked");

        // Office
        createDevice("Office Light", DeviceType.LIGHT, "Office", false, 70.0, 18.0, "Off");
        createDevice("Computer", DeviceType.APPLIANCE, "Office", false, null, 200.0, "Standby");
    }

    private void createDevice(String name, DeviceType type, String room, boolean isOn, Double value, Double energyConsumption, String status) {
        Device device = new Device(name, type, room);
        device.setIsOn(isOn);
        device.setValue(value);
        device.setEnergyConsumption(energyConsumption);
        device.setStatus(status);
        deviceService.saveDevice(device);
    }

    private void initializeEnergyData() {
        energyService.recordEnergyUsage("daily", 45.2, 8.15);
        energyService.recordEnergyUsage("monthly", 1456.0, 262.08);
    }
}