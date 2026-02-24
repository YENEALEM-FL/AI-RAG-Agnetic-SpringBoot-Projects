package com.lab.toolingwithmethodparameters.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;


@Component
public class DevopsTools {

    @Tool(description = "Get the current deployment status of a microservice in a specific environment.")
    public String getDeployment(@ToolParam(description = "Name of hte microservice or service") String serviceName,
                                @ToolParam(description = "Environment(e.g., production, staging)") String environment){
        System.out.println("get deployment status called with input: '"+ serviceName+"' ,'"+ environment+"'");
        return "deployment status for the service '" + serviceName+ "' in environment '"+ environment+ "':"+
                "Service is currently running version 1.4.2 with 5 pods healthy.";
    }

    @Tool(description="Trigger a rolling restart of a microservice in a given environment.")
    public String restartService(@ToolParam(description = "Name of the microservice or service") String serviceName,
                                 @ToolParam(description = "Environment (e.g., production, staging)") String environment){
        System.out.println("Restart service called with input: '" + serviceName + "' , '" + environment +"'");
        return "Rolling restart of service '" + serviceName + "' in environment '"+ environment + "' initiated for service with 5 pods." +
                "Expected downtime is less than 10 minutes.";
    }
}