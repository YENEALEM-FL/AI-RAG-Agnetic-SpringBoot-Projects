package com.lab.springaitoolprogrammatically.config;

import com.lab.springaitoolprogrammatically.tools.DevOpsTools;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.definition.ToolDefinition;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.util.json.schema.JsonSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

@Configuration
public class ToolCallBackConfiguration {

    @Autowired
    DevOpsTools devOpsTools;

    public ToolCallback createDeploymentStatusToolCallBack(){

        Method method = ReflectionUtils.findMethod(DevOpsTools.class, "getDeploymentStatus", String.class, String.class);

        assert method!= null;

        return MethodToolCallback.builder()
                .toolDefinition(ToolDefinition.builder()
                        .name("getDeploymentStatus")
                        .description("Retrieves the current deployment status of a microservice including version, pod count, and health status. Use this when asked and provide them in list")
                        .inputSchema(JsonSchemaGenerator.generateForMethodInput(method))
                        .build())
                .toolMethod(method)
                .toolObject(devOpsTools)
                .build();

    }

    public ToolCallback createRestartServcieToolCallBack(){

        Method method = ReflectionUtils.findMethod(DevOpsTools.class, "restartService", String.class, String.class);

        assert method!= null;

        return MethodToolCallback.builder()
                .toolDefinition(ToolDefinition.builder()
                        .name("restartService")
                        .description("Triggers s rolling restart of a microservice. Use this when asked to restart a service or perform maintenance.")
                        .inputSchema(JsonSchemaGenerator.generateForMethodInput(method))
                        .build())
                .toolMethod(method)
                .toolObject(devOpsTools)
                .build();
    }

    /*
    This callback replaces applying annotation @Tool for services.
     */
    @Bean
    public List<ToolCallback> getDeploymentStatusCallBack(){
        return List.of(
                createDeploymentStatusToolCallBack(),
                createRestartServcieToolCallBack()
        );
    }
}
