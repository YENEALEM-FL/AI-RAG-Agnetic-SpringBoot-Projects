package com.lab.springaitoolprogrammatically.tools;

import org.springframework.stereotype.Component;

@Component
public class DevOpsTools {

    private String currentVersion = "1.4.2";
    private final int podCount = 5;

  public String getDeploymentStatus(String serviceName, String environment) {

        System.out.println("Tools Executed: getDeploymentStatus()");

        String healthStatus = "unhealthy";

        return String.format(
            "service '%s' in environment '%s' is currently running version %s with %d pods %s.",
            serviceName, environment, currentVersion, podCount, healthStatus);
    }

    public String restartService(String serviceName, String environment){

            System.out.println("Tool executed: restartService()");

            String[] versionParts = currentVersion.split("\\.");

            int patch = Integer.parseInt(versionParts[2]) + 1;

            currentVersion = versionParts[0] + versionParts[1] +"." + patch;

            return String.format(
                    "Rolling restart initiated for service '%s' in environment '%s' with %d pods. " +
                            "Expected downtime is less than 1 minutes. " +
                            "Service updated to version %s. ",serviceName, environment, podCount, currentVersion);
    }
}
