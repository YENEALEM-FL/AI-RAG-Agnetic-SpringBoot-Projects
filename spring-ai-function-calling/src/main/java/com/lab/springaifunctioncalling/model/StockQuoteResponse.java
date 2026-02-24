package com.lab.springaifunctioncalling.model;

public record StockQuoteResponse(
        String symbol,
        String price,
        String change,
        String changePercent,
        String latestTradingDay,
        boolean available
) {}
