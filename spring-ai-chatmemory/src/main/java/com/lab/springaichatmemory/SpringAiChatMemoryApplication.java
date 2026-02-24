package com.lab.springaichatmemory;
import com.lab.springaichatmemory.service.ChatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class SpringAiChatMemoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiChatMemoryApplication.class, args);
    }

    @Component
    public static class CommandLineAppStartUpRunner implements CommandLineRunner {

        private final ChatService chatService;

        public CommandLineAppStartUpRunner(ChatService chatService) {
            this.chatService = chatService;
        }

        @Override
        public void run(String... args) throws Exception {
            chatService.startChat();
        }
    }

}
