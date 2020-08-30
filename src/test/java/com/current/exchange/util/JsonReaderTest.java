package com.current.exchange.util;

import com.current.exchange.model.CurrentRate;
import com.current.exchange.model.mapper.CurrentRateMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class JsonReaderTest {
    private static final String TEST_NAME = "Австралійський долар";
    private static final BigDecimal TEST_RATE = BigDecimal.valueOf(19.9082);
    private static final LocalDate TEST_LOCAL_DATE = LocalDate.of(2020, 12, 12);

    @SpyBean
    private JsonReader jsonReader;

    @SpyBean
    private CurrentRateMapper currentRateMapper;

    @SpyBean
    private ObjectMapper objectMapper;

    @Test
    public void getCurrentRateListIsOk() {
        String inputJson = "[\n" +
                "\n" +
                "{ \n" +
                "\n" +
                "\"r030\":36,\"txt\":\"Австралійський долар\",\"rate\":19.9082,\"cc\":\"AUD\",\"exchangedate\":\"12.12.2020\"\n" +
                "}\n" +
                "\n" +
                "]\n";

        CurrentRate expectedCurrentRate = new CurrentRate(TEST_NAME, TEST_RATE, TEST_LOCAL_DATE);
        CurrentRate currentRate = jsonReader.getCurrentRateList(inputJson).get(0);

        Assert.assertNotNull(currentRate);
        Assert.assertEquals(expectedCurrentRate, currentRate);
    }
}
