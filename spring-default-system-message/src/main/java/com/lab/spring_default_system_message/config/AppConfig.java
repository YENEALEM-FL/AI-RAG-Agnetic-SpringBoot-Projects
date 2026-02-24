package com.lab.spring_default_system_message.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClient){
        return chatClient
                .defaultSystem("You are a helpful assistance.")
                .build();
    }

}
