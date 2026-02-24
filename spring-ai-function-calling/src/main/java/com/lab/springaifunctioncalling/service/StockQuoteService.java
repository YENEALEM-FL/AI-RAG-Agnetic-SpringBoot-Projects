package com.lab.springaifunctioncalling.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.springaifunctioncalling.model.StockQuoteRequest;
import com.lab.springaifunctioncalling.model.StockQuoteResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.function.Function;
import java.util.logging.Logger;


@Service
public class StockQuoteService implements Function<StockQuoteRequest, StockQuoteResponse> {

    private static final Logger logger = Logger.getLogger(StockQuoteService.class.getName());
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public StockQuoteService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = (webClientBuilder != null)? webClientBuilder.build() : WebClient.builder().build();
        this.objectMapper = (objectMapper != null)? objectMapper:new ObjectMapper();
    }

    @Override
    public StockQuoteResponse apply(StockQuoteRequest stockQuoteRequest) {
        logger.info("apply method called with request: " + stockQuoteRequest);

        try{
      String response =
          webClient
              .get()
              .uri(
                  "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol={symbol}&apikey=demo",
                  stockQuoteRequest.symbol())
              .retrieve()
              .bodyToMono(String.class)
              .block();

            logger.info("Global Quote Response: " + response);

            if(response != null){

                var jsonNode = objectMapper.readTree(response);
                var globalQuote = jsonNode.get("Global Quote");

                if(globalQuote != null && !globalQuote.isEmpty()){

                      return new StockQuoteResponse(
                          globalQuote.get("01. symbol").asText(),
                          globalQuote.get("05. price").asText(),
                          globalQuote.get("09. change").asText(),
                          globalQuote.get("10. change percent").asText(),
                          globalQuote.get("07. latest trading day").asText(),
                          true
                      );
                }
            }

        } catch (Exception e) {
            logger.severe(e.getMessage());
            return  new StockQuoteResponse(stockQuoteRequest.symbol(),"N/A","N/A","N/A","N/A",false);
        }
        return  new StockQuoteResponse(stockQuoteRequest.symbol(),"N/A","N/A","N/A","N/A",false);
    }
}
