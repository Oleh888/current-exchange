package com.current.exchange.service.impl;

import com.current.exchange.model.CurrentRate;
import com.current.exchange.service.CurrentRateInfoService;
import com.current.exchange.util.JsonReader;
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
        return jsonReader.getCurrentRateList(getResultInfoFromRestTemplate(apiUrl));
    }

    @Override
    public CurrentRate getCurrentRate(String apiUrl, String name) {
        return jsonReader.getCurrentRate(getResultInfoFromRestTemplate(apiUrl), name);
    }

    private String getResultInfoFromRestTemplate(String apiUri) {
        return restTemplate.getForObject(apiUri, String.class);
    }
}
