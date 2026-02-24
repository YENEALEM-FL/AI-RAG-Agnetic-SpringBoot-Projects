package com.lab.springaimcpsecurity.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingResponseMessage {

  String message;
  List<FlightBooking> flightBookings;
}