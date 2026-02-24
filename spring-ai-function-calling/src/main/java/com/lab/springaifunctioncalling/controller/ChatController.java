package com.lab.springaifunctioncalling.controller;

import com.lab.springaifunctioncalling.model.StockQuoteRequest;
import com.lab.springaifunctioncalling.service.StockQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final ChatModel chatModel;
    private final StockQuoteService stockQuoteService;

    @Autowired
    public ChatController(ChatModel chatModel, StockQuoteService stockQuoteService) {
        this.chatModel = chatModel;
        this.stockQuoteService = stockQuoteService;
    }

    @GetMapping("/chat/{symbol}")
    public String chat(@PathVariable("symbol") String symbol) {
        logger.info("chat endpoint called for symbol: {}", symbol);
        String user_message = "What is the current price of " + symbol + "?";

    ToolCallback toolCallback =
        FunctionToolCallback.builder("get_stock_quote", stockQuoteService)
            .description(
                "Get current stock quote including price, change, and trading information for a given stock symbol")
            .inputType(StockQuoteRequest.class)
            .build();

        return ChatClient.create(chatModel)
                .prompt(user_message)
                .toolCallbacks(toolCallback)
                .call()
                .content();
    }

    @GetMapping("/api")
    public String chat() {
        return "It is working";
    }
}