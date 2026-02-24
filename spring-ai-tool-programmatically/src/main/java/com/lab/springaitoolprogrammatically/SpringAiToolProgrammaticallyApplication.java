package com.lab.springaitoolprogrammatically;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringAiToolProgrammaticallyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiToolProgrammaticallyApplication.class, args);
    }

    @Autowired
    public ChatModel chatModel;

    @Autowired
    public List<ToolCallback> toolcallbacks;

    @Bean
    public CommandLineRunner commandLineRunner(){

        return args -> {
            System.out.println("Command lien runner execute at startup");
            System.out.println("Let's chat!");
            Scanner scanner = new Scanner(System.in);
            ChatClient chatClient = ChatClient.create(chatModel);
            MessageWindowChatMemory chatMemory = MessageWindowChatMemory.builder()
                    .maxMessages(10)
                    .build();

            while(true){

                System.out.println("USER: ");
                String userInput = scanner.nextLine();
                if("quit".equalsIgnoreCase(userInput)|| "exit".equalsIgnoreCase(userInput)){
                    System.out.println("Goodbye!");
                    break;
                }
                try{
                    String response = chatClient
                            .prompt(userInput)
                            .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                            .toolCallbacks(toolcallbacks)
                            .call()
                            .content();

                    System.out.println("ASSISTANT: " + response);
                    System.out.println();
                }catch(Exception e){
                    System.out.println("ERROR: "+ e.getMessage());
                    System.out.println();
                }
            }
            scanner.close();
        };
    }

}
