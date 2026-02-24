package com.lab.springaimcpsecurity.model;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FlightBooking {
  private Long id;
  private String airline;
  private String from;
  private String to;
  private LocalDateTime departure;
  private LocalDateTime arrival;
  private String flightNumber;
  private String passenger;
  private String bookingRef;
    }
