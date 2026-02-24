package com.lab.springaimcpclientauth.controller;

import com.lab.springaimcpclientauth.model.FlightResponseMessage;
import com.lab.springaimcpclientauth.servcie.FlightBookingClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FlightBookingController {

    @Autowired
    FlightBookingClientService flightBookingClientService;

    @PostMapping("/chat")
    public FlightResponseMessage chat(@RequestBody String message) {
        System.out.println("request received : " + message);
        FlightResponseMessage response = flightBookingClientService.processInput(message);
        System.out.println("response sent: " + response);
        //convert below to json
        return response;
    }
}