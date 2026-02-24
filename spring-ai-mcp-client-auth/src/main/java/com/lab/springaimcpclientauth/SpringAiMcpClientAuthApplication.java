package com.lab.springaimcpclientauth;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.mcp.customizer.McpSyncClientCustomizer;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAiMcpClientAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiMcpClientAuthApplication.class, args);
	}


    @Bean
    CommandLineRunner commandLineRunner(List<McpSyncClient> mcpSyncClients, ChatClient.Builder chatClientBuilder) {
        return args -> {
            System.out.println("Spring Ai MCP Client Example Application is running!");
            System.out.println("list of mcpSyncClients: " + mcpSyncClients);
            McpSyncClient mspclient = mcpSyncClients.getFirst();
            //System.out.println("mspclient: " + mspclient.listTools());
            SyncMcpToolCallbackProvider toolCallbackProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
            ToolCallback[] toolCallbacks = toolCallbackProvider.getToolCallbacks();
            // iterate over the toolCallbacks and print them

            for (ToolCallback toolCallback : toolCallbacks) {
                System.out.println("Tool Callback getToolDefinition: " + toolCallback.getToolDefinition());
            }


        };
    }

    @Bean
    McpSyncClientCustomizer customizeMcpClient() {
        return (name, mcpClientSpec) -> {
            mcpClientSpec.toolsChangeConsumer(tv -> {
                System.out.println("Tools changed: " + tv);
                // iterate tv
                tv.forEach(tool -> {
                    System.out.println("Tool: " + tool);
                });
            });
        };
    }
}
