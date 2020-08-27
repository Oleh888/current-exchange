package com.current.exchange.exchange.service;

import com.current.exchange.exchange.model.CurrentRate;
import java.util.List;

public interface CurrentRateInfoService {
    List<CurrentRate> getInfo(String apiUrl);
}
