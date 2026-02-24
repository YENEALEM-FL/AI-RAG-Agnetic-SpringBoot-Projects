package com.lab.springaitoolcallingproject.repository;

import com.lab.springaitoolcallingproject.model.EnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EnergyUsageRepository extends JpaRepository<EnergyUsage, Long> {

    List<EnergyUsage> findByPeriod(String period);

    @Query("SELECT e FROM EnergyUsage e WHERE e.recordedAt BETWEEN ?1 AND ?2 ORDER By e.recordedAt DESC")
    List<EnergyUsage> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(e.consumption) FROM EnergyUsage e WHERE e.period = ?1")
    Double getTotalConsumptionByPeriod(String period);

    @Query("SELECT SUM(e.cost) FROM EnergyUsage e WHERE e.period = ?1")
    Double getTotalCostByPeriod(String period);
}
