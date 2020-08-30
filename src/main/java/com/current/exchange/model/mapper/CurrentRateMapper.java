package com.current.exchange.model.mapper;

import com.current.exchange.model.CurrentRate;
import com.current.exchange.model.dto.CurrentRateDto;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CurrentRateMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public CurrentRate getCurrencyFromDto(CurrentRateDto currentRateDto) {
        return new CurrentRate(
                currentRateDto.getTxt(),
                BigDecimal.valueOf(Double.parseDouble(currentRateDto.getRate())),
                LocalDate.parse(currentRateDto.getExchangedate(), FORMATTER));
    }
}
