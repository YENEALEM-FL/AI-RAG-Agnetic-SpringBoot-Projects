package com.lab.springaitoolcallingproject.repository;

import com.lab.springaitoolcallingproject.model.Device;
import com.lab.springaitoolcallingproject.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByRoom(String room);
    List<Device> findByDeviceType(DeviceType type);
    Optional<Device> findByNameIgnoreCase(String name);
    @Query("SELECT SUM(d.energyConsumption) fROM Device d where d.isOn = true")
    Double getTotalEnergyConsumption();

}
