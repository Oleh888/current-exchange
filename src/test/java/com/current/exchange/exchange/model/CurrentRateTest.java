package com.current.exchange.exchange.model;

import com.current.exchange.exchange.model.dto.CurrentRateDto;
import com.current.exchange.exchange.model.mapper.CurrentRateMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class CurrentRateTest {
    private static final String TEST_DATE = "12.12.2020";
    private static final String TEST_NAME = "Євро";
    private static final BigDecimal TEST_RATE = BigDecimal.valueOf(33.55);
    private static final LocalDate TEST_LOCAL_DATE = LocalDate.of(2020, 12, 12);

    @SpyBean
    private CurrentRateMapper mapper;

    @Test
    public void mapFromCurrentRateDtoToCurrentRateIsOk() {
        CurrentRateDto currentRateDto = new CurrentRateDto();
        currentRateDto.setExchangedate(TEST_DATE);
        currentRateDto.setTxt(TEST_NAME);
        currentRateDto.setRate(TEST_RATE.toString());

        CurrentRate expectedCurrentRate = new CurrentRate(TEST_NAME, TEST_RATE, TEST_LOCAL_DATE);
        CurrentRate currentRate = mapper.getCurrencyFromDto(currentRateDto);

        Assert.assertNotNull(currentRate);
        Assert.assertEquals(expectedCurrentRate, currentRate);
    }
}
