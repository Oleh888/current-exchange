package com.current.exchange.exchange.service.impl;

import com.current.exchange.exchange.model.CurrentRate;
import com.current.exchange.exchange.service.CurrentRateInfoService;
import com.current.exchange.exchange.util.JsonReader;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class CurrentRateInfoServiceImpl implements CurrentRateInfoService {
    private final JsonReader jsonReader;
    private final RestTemplate restTemplate;

    public CurrentRateInfoServiceImpl(JsonReader jsonReader, RestTemplate restTemplate) {
        this.jsonReader = jsonReader;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CurrentRate> getInfo(String apiUrl) {
        String result = restTemplate.getForObject(apiUrl, String.class);
        return jsonReader.getCurrencies(result);
    }
}
