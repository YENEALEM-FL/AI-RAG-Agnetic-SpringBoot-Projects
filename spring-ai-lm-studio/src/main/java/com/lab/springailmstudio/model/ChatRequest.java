package com.lab.springailmstudio.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatRequest {

    @Value("${lmstudio.ai.model}")
    private String model;
    private List<Message> messages;
    private int maxTokens;
    private boolean stream;
}
