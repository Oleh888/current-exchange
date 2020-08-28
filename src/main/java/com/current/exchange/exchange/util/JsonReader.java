package com.current.exchange.exchange.util;

import com.current.exchange.exchange.model.CurrentRate;
import com.current.exchange.exchange.model.dto.CurrentRateDto;
import com.current.exchange.exchange.model.mapper.CurrentRateMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JsonReader {
    private final CurrentRateMapper currentRateMapper;
    private final ObjectMapper objectMapper;

    public JsonReader(CurrentRateMapper currentRateMapper, ObjectMapper objectMapper) {
        this.currentRateMapper = currentRateMapper;
        this.objectMapper = objectMapper;
    }

    public List<CurrentRate> getCurrentRateList(String json) {
        List<CurrentRateDto> currentRateDtoList = null;
        try {
            currentRateDtoList = Arrays.asList(objectMapper.readValue(json, CurrentRateDto[].class));
        } catch (IOException e) {
            System.out.println(e);
        }
        return currentRateDtoList.stream()
                .map(currentRateMapper::getCurrencyFromDto)
                .collect(Collectors.toList());
    }
}
