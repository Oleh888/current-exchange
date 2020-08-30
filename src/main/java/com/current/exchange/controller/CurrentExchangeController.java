package com.current.exchange.controller;

import com.current.exchange.model.CurrentRate;
import com.current.exchange.service.CurrentRateInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/exchange")
public class CurrentExchangeController {
    private static final String API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final CurrentRateInfoService currentRateInfoService;

    public CurrentExchangeController(CurrentRateInfoService currentRateInfoService) {
        this.currentRateInfoService = currentRateInfoService;
    }

    @GetMapping("/get-info")
    public List<CurrentRate> getInfo() {
        return currentRateInfoService.getInfo(API_URL);
    }

    @GetMapping("/get-info/{name}")
    public CurrentRate getInfoByParameter(@PathVariable String name) {
        return currentRateInfoService.getCurrentRate(API_URL, name);
    }
}
