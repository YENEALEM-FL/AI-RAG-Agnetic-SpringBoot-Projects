package com.lab.springaimcpsecurity.controller;

import com.lab.springaimcpsecurity.model.FlightBookingResponseMessage;
import com.lab.springaimcpsecurity.services.FlightBookingClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightBookingController {


    @Autowired
    FlightBookingClientService flightBookingClientService;

    @PostMapping("/chat")
    public FlightBookingResponseMessage chat(@RequestBody String message){

        System.out.println("Message received: " + message);
        FlightBookingResponseMessage response = flightBookingClientService.processInput(message);
        System.out.println("response sent: "+ response);
        return response;

    }
}
