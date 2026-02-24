package com.lab.springaitoolcallingproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Eneergy_USAGE")
@Getter
@Setter
public class EnergyUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private Double consumption;

    @Column(nullable = false)
    private Double cost;

    @Column(nullable = false)
    private LocalDateTime recordedAt;


    public EnergyUsage(){
        this.recordedAt = LocalDateTime.now();
    }

    public EnergyUsage(String period, Double consumption, Double cost){
        this();
        this.period = period;
        this.consumption = consumption;
        this.cost = cost;
    }

}
