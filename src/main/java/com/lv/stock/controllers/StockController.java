package com.lv.stock.controllers;

import com.lv.stock.services.StockService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.version}/stock")
@CrossOrigin
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/company/{name}")
    public Object getCompanyOverview(@PathVariable String name) {
        final RestTemplate tmp = new RestTemplate();
        String url = stockService.getCompanyOverviewUrl(name);
        return tmp.getForEntity(url, Object.class);
    }

    @GetMapping("/daily/{name}")
    public Object getDailyStock(@PathVariable String name) {
        final RestTemplate tmp = new RestTemplate();
        String url = stockService.getDailyUrl(name);
        return tmp.getForEntity(url, Object.class);
    }

    @GetMapping("/company/daily/{name}")
    public List<Object> getCompanyDailyStock(@PathVariable String name) {
        Object companyOverview = this.getCompanyOverview(name);
        Object dailyStock = this.getDailyStock(name);

        List<Object> result = new ArrayList<>();
        result.add(companyOverview);
        result.add(dailyStock);

        return result;
    }
}
