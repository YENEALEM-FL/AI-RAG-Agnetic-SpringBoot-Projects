package com.lab.springaitoolcallingproject.tools;

import com.lab.springaitoolcallingproject.model.Device;
import com.lab.springaitoolcallingproject.model.DeviceType;
import com.lab.springaitoolcallingproject.service.DeviceService;
import com.lab.springaitoolcallingproject.service.EnergyService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SmartHomeTools {

  @Autowired private DeviceService deviceService;

  @Autowired private EnergyService energyService;

  @Tool(description = "Control lights by turning them on/off or adjusting brightness (0 - 100")
  public String controlLights(
      @ToolParam(description = "Name of the light device") String deviceName,
      @ToolParam(
              description = "Action: 'on', 'off', or brightness level (0 - 100)",
              required = false)
          String action) {

    try {
      Optional<Device> deviceOpt = deviceService.getDeviceByName(deviceName);

      if (deviceOpt.isEmpty()) {
        return "Light '" + deviceName + "' not found";
      }

      Device device = deviceOpt.get();

      if (device.getDeviceType() != DeviceType.LIGHT) {
        return "Device '" + deviceName + "' is not a light";
      }

      if (action == null || action.equalsIgnoreCase("toggle")) {
        device = deviceService.toggleDevice(deviceName);
        return String.format("light '%s' turned %s ", deviceName, device.getIsOn() ? "ON" : "OFF");
      } else if (action.equalsIgnoreCase("on")) {
        device.setIsOn(true);
        deviceService.saveDevice(device);
        return String.format("Light '%s' turned ON", deviceName);
      } else if (action.equalsIgnoreCase("off")) {
        device.setIsOn(false);
        deviceService.saveDevice(device);
        return String.format("Light '%s' turned OFF", deviceName);
      } else {

        try {
          double brightness = Double.parseDouble(action);

          if (brightness < 0 || brightness > 100) {
            return "brightness must be between 0 and 100";
          }
          device.setIsOn(brightness > 0);
          device.setValue(brightness);
          deviceService.saveDevice(device);
          return String.format("Light '%s' set to %s%% brightness", deviceName, brightness);

        } catch (NumberFormatException e) {
          return "Invalid action. Use 'on', 'off', or a brightness level (0 - 100)";
        }
      }
    } catch (Exception e) {
      return "Error controlling light: " + e.getMessage();
    }
  }

  @Tool(description = "Control thermostat temperature in Celsius")
  public String controlTemperature(
      @ToolParam(description = "Name of the thermostat") String deviceName,
      @ToolParam(description = "Target temperature in Celsius", required = false)
          Double temperature) {

    try {
      Optional<Device> deviceOpt = deviceService.getDeviceByName(deviceName);

      if (deviceOpt.isEmpty()) {
        return "Thermostat '" + deviceName + " ' not found";
      }

      Device device = deviceOpt.get();
      if (device.getDeviceType() != DeviceType.THERMOSTAT) {
        return "Device '" + deviceName + "' is not a thermostat";
      }

      if (temperature == null) {
        return String.format(
            "Current temerature for '%s': %s °C",
            deviceName, device.getValue() != null ? device.getValue() : "Unknown");
      }
      if (temperature < 10 || temperature > 35) {
        return "Temperature must be between 10°C and 35°C";
      }

      device.setIsOn(true);
      device.setValue(temperature);
      deviceService.saveDevice(device);

      return String.format("Thermostat '%s' set to %s°C", deviceName, temperature);
    } catch (Exception e) {
      return "Error controlling temperature: " + e.getMessage();
    }
  }

  @Tool(description = "Control security devices including cameras and locks")
  public String controlSecurity(
      @ToolParam(description = "Name of the security device") String deviceName,
      @ToolParam(description = "Action: 'arm', 'disarm', 'lock', 'unlock', 'status'", required = false) String action){

    try {
      Optional<Device> deviceOpt = deviceService.getDeviceByName(deviceName);

      if (deviceOpt.isEmpty()) {
        return "Security device ' " + deviceName + "' not found";
      }

      Device device = deviceOpt.get();
      if (device.getDeviceType() != DeviceType.SECURITY) {
        return "Device '" + deviceName + "' is not a security device";
      }

      if (action == null || action.equalsIgnoreCase("status")) {
        return String.format(
            "Security device '%s' - status: %s, Active: %s",
            deviceName,
            device.getStatus() != null ? device.getStatus() : "Unknown",
            device.getIsOn() ? "YES" : "ON");
      }

          switch (action.toLowerCase()) {
            case "arm":
              device.setIsOn(true);
              device.setStatus("Armed");

            case "disarm":
              device.setIsOn(false);
              device.setStatus("Disarmed");

            case "lock":
              device.setIsOn(true);
              device.setStatus("Locked");

            case "unlock":
              device.setIsOn(false);
              device.setStatus("unlocked");

            default:
              device.setIsOn(false);
              device.setStatus("none");
          }
      deviceService.saveDevice(device);
    } catch (Exception e) {
      return "Error controlling security device: " + e.getMessage();
    }

    return String.format("Security device '%s' %s successfully", deviceName, action);
  }


  @Tool(description = "Monitor current energy consumption and get energy statistics")
  public String monitorEnergy(@ToolParam(description = "Type: 'current', 'daily', 'monthly' ", required = false) String type) {
    try {
      if (type == null || type.equalsIgnoreCase("current")) {
        Double currentConsumption = energyService.getCurrentEnergyConsumption();
        return String.format("Current total energy consumption: %.2f watts", currentConsumption);
      }

        return switch (type.toLowerCase()) {
            case "daily" -> {
                Double dailyCost = energyService.getTotalCostByPeriod("daily");
                yield String.format("Daily energy cost: $%.2f", dailyCost);
            }
            case "monthly" -> {
                Double monthlyCost = energyService.getTotalCostByPeriod("monthly");
                yield String.format("Monthly energy cost: $%.2f", monthlyCost);
            }
            default -> "Invalid type. Use: 'current', 'daily', or 'monthly'";
        };
    } catch (Exception e) {
      return "Error monitoring energy: " + e.getMessage();
    }

  }
}