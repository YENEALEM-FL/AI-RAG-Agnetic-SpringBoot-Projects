package com.lab.springaimcpserverusingclaude.AppConfig;

import com.lab.springaimcpserverusingclaude.services.BookDataService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public List<ToolCallback> toolCallbacks(BookDataService bookDataService) {
        return List.of(ToolCallbacks.from(bookDataService));
    }
}
