package com.lab.springaimcpsecurity.services;

import com.lab.springaimcpsecurity.model.FlightBookingResponseMessage;
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

        if (mcpSyncClients == null || mcpSyncClients.isEmpty()) {
            throw new IllegalStateException("No MCP Sync Clients available. Check your MCP server configuration in application.properties");
        }

        System.out.println("Spring AI MCP Client Example Application is running!");
        System.out.println("List of MCP Sync Clients: "+mcpSyncClients);

        McpSyncClient mcpSyncClient = mcpSyncClients.getFirst();
        System.out.println("mcp client: "+mcpSyncClient.listTools());

        SyncMcpToolCallbackProvider toolCallbackProvider = new SyncMcpToolCallbackProvider(mcpSyncClient);
        ToolCallback[]  toolCallbacks = toolCallbackProvider.getToolCallbacks();

        for (ToolCallback toolCallback : toolCallbacks) {
            System.out.println("Tool Callback getToolDefinition: "+toolCallback.getToolDefinition());
        }

        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(toolCallbackProvider)
                .build();
  }

  public FlightBookingResponseMessage processInput(String userInput){

      return chatClient
              .prompt(userInput)
              .call()
              .entity(new ParameterizedTypeReference<FlightBookingResponseMessage>() {});

  }
}
