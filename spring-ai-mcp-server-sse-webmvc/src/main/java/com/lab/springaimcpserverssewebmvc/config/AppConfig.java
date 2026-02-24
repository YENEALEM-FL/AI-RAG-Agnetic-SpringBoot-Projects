package com.lab.springaimcpserverssewebmvc.config;


import com.lab.springaimcpserverssewebmvc.service.FlightBookingService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public List<ToolCallback> toolCallbacks(FlightBookingService flightBookingService) {
        return List.of(ToolCallbacks.from(flightBookingService));
    }
}
