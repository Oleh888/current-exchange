package com.current.exchange.service;

import com.current.exchange.model.CurrentRate;

import java.util.List;

public interface CurrentRateInfoService {
    List<CurrentRate> getInfo(String apiUrl);

    CurrentRate getCurrentRate(String json, String name);
}
