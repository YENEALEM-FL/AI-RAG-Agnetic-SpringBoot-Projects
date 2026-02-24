package com.lab.springaitoolcalling.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
@Component
public class DevopsTools {

    @Tool(
            description = "Get the current deployment status of a microservice in a specific environment."
    )
    public String getDeployment(){
        System.out.println("get deployment status called with input: ");
        return "Service is currently running version 1.4.2 with 5 pods healthy.";
    }

    @Tool(
            description="Trigger a rolling restart of a microservice in a given environment."
    )
    public String restartService(){
        System.out.println("Restart service called with input: ");
        return "Rolling restart initiated for service with 5 pods." +
                "Expected downtime is less than 10 minutes.";
    }
}