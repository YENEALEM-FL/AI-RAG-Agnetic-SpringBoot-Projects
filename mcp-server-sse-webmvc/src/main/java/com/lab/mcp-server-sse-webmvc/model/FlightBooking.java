package com.example.mcp-server-sse-webmvc.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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