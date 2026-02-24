package com.lab.spring_default_system_message.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {

    // If we want to put long text for System message, you can create a .txt file in the resources
    @Value("classpath:system_message.txt")
    Resource resource;
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClient){
        return chatClient
                //.defaultSystem("You are a helpful assistance.")
                .defaultSystem(resource)
                .build();
    }

}
