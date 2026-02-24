package com.lab.springaitoolcallingproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "DeVICES")
@Getter
@Setter
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType deviceType;

    @Column(nullable = false)
    private String room;

    @Column(nullable = false)
    private Boolean isOn = false;

    @Column(name ="\"value\"")
    private Double value;
    private String status;
    private LocalDateTime lastUpdated;
    private Double energyConsumption = 0.0;

    public Device(){this.lastUpdated = LocalDateTime.now();}

    public Device(String name, DeviceType deviceType, String room){

        this();
        this.name = name;
        this.deviceType = deviceType;
        this.room = room;
    }

}
