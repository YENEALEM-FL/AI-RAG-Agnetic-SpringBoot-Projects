package com.lab.springaifunctioncalling.config;

import com.lab.springaifunctioncalling.model.StockQuoteRequest;
import com.lab.springaifunctioncalling.model.StockQuoteResponse;
import com.lab.springaifunctioncalling.service.StockQuoteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class FunctionConfig {

    @Bean(name = "stock_quote")
    @Description("Get real-time stock quote information including price, change, and trading day")
    public Function<StockQuoteRequest, StockQuoteResponse> stockQuoteFunction(StockQuoteService stockQuoteService) {
        return stockQuoteService;
    }

}
