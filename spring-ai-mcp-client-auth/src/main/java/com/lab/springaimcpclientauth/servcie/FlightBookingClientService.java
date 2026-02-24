package com.lab.springaimcpclientauth.servcie;


import com.lab.springaimcpclientauth.model.FlightResponseMessage;
import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightBookingClientService {
    ChatClient chatClient;

    public FlightBookingClientService(List<McpSyncClient> mcpSyncClients, ChatClient.Builder chatClientBuilder) {

        System.out.println("Spring Ai MCP Client Example Application is running!");
        System.out.println("list of mcpSyncClients: " + mcpSyncClients);
        McpSyncClient mspclient = mcpSyncClients.getFirst();
        System.out.println("mspclient: " + mspclient.listTools());
        SyncMcpToolCallbackProvider toolCallbackProvider = new SyncMcpToolCallbackProvider(mcpSyncClients);
        ToolCallback[] toolCallbacks = toolCallbackProvider.getToolCallbacks();
        for (ToolCallback toolCallback : toolCallbacks) {
            System.out.println("Tool Callback getToolDefinition: " + toolCallback.getToolDefinition());
        }

        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(toolCallbackProvider)
                .build();

    }

    public FlightResponseMessage processInput(String userInput) {
        FlightResponseMessage flightResponseMessage = chatClient
                .prompt(userInput)

                .call()
                .entity(new ParameterizedTypeReference<FlightResponseMessage>() {
                });
        return flightResponseMessage;
    }
}
