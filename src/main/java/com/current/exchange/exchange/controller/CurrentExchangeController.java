package com.current.exchange.exchange.controller;

import com.current.exchange.exchange.model.CurrentRate;
import com.current.exchange.exchange.service.CurrentRateInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/exchange")
public class CurrentExchangeController {
    private static final String API_URI = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final CurrentRateInfoService currentRateInfoService;

    public CurrentExchangeController(CurrentRateInfoService currentRateInfoService) {
        this.currentRateInfoService = currentRateInfoService;
    }

    @GetMapping("/get-info")
    public List<CurrentRate> getInfo() {
        return currentRateInfoService.getInfo(API_URI);
    }
}
