package com.lab.springaimcpclientauth.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
public class FlightResponseMessage {
    String message;
    List<FlightBooking> bookings;

}
