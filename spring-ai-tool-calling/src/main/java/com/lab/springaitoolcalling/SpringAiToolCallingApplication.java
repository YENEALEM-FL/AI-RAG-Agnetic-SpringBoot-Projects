package com.lab.springaitoolcalling;

import com.lab.springaitoolcalling.tools.DevopsTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringAiToolCallingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiToolCallingApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ChatClient.Builder builder){
//        return args -> {
//            System.out.println("Command Line runner at executes");
//            var chat = builder.build();
//            var scanner = new Scanner(System.in);
//            System.out.println("\n Let's chat!");
//
//            while(true){
//                System.out.println("\nUSER: ");
//                System.out.println("ASSISTANT: "+
//                        chat.prompt(scanner.nextLine())
//                                .tools(new DevopsTools())
//                                .call()
//                                .content());
//            }
//        };
//    }
//    @Bean
//    public List<ToolCallback> toolcallbacks(DevopsTools devopsTools){
//        return List.of(ToolCallbacks.from(devopsTools));
//    }
}
