package com.lv.stock.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Value("${alphavantage.api.key}")
    private String apiKey;

    @Value("${alphavantage.host}")
    private String host;

    @Value("${alphavantage.function.time-service-daily}")
    private String function;

    @Value("${alphavantage.function.company-overview}")
    private String overview;

    public String getDailyUrl(String companyName) {
        String str = String.format("%squery?function=%s&symbol=%s&apikey=%s", host, function, companyName, apiKey);
        return str;
    }

    public String getCompanyOverviewUrl(String companyName) {
        String str = String.format("%squery?function=%s&symbol=%s&apikey=%s", host, overview, companyName, apiKey);
        return str;
    }
}
