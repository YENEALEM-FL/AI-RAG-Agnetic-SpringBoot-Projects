package com.lab.springaimcpclient;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAiMcpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiMcpClientApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(List<McpSyncClient> mcpSyncClients, ChatClient.Builder chatClientBuilder) {
    return args -> {
      System.out.println("mcp Sync Clients");
      McpSyncClient mcpSyncClient = mcpSyncClients.getFirst();
      System.out.println("mcp client" + mcpSyncClient.listTools());
      SyncMcpToolCallbackProvider toolCallBackProvider =
          new SyncMcpToolCallbackProvider(mcpSyncClients);
      ToolCallback[] toolCallbacks = toolCallBackProvider.getToolCallbacks();

      for (ToolCallback toolCallback : toolCallbacks) {
        System.out.println("Total callback getToolDefinition: " + toolCallback.getToolDefinition());
      }

      var chatClient = chatClientBuilder.defaultToolCallbacks(toolCallBackProvider).build();

      var userInput = "Please give me books name authored by Tom Wood";

      System.out.println("\n >>> QUESTION: " + userInput);
      System.out.println("\n >>> ANSWER: " + chatClient.prompt(userInput).call().content());
    };
    }
}
