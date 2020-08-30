package com.current.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CurrentRate {
    private String name;
    private BigDecimal rate;
    private LocalDate date;
}
