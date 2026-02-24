package com.lab.springaijdbcchatmemory.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChatService {

    @Autowired
    JdbcChatMemoryRepository jdbcChatMemoryRepository;

    @Autowired
    ChatClient chatClient;


    public String chat(String request){
        return chatClient.prompt()
                .user(request)
                .call()
                .content();
    }

    public List<Message> listAllChats() {
        List<String> pastids = jdbcChatMemoryRepository.findConversationIds();
        List<Message> byConversationId = jdbcChatMemoryRepository.findByConversationId(pastids.get(0));
        return byConversationId;
    }
}
