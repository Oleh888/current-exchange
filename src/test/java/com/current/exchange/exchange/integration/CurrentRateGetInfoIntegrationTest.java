package com.current.exchange.exchange.integration;

import com.current.exchange.exchange.CurrentRateUtil;
import com.current.exchange.exchange.controller.CurrentExchangeController;
import com.current.exchange.exchange.model.CurrentRate;
import com.current.exchange.exchange.service.CurrentRateInfoService;
import com.current.exchange.exchange.util.JsonReader;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrentRateGetInfoIntegrationTest {
    private static final String API_URI = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(wireMockConfig().port(8089));

    @Autowired
    private CurrentExchangeController currentExchangeController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CurrentRateUtil currentRateUtil;

    @SpyBean
    private JsonReader jsonReader;

    @SpyBean
    private CurrentRateInfoService currentRateInfoService;

    @Test
    public void getInfoShouldReturnSuccessResponse() {
        String result = currentRateUtil.getCurrentRateInfoFromTestFile();
        List<CurrentRate> expectedCurrentRateList = jsonReader.getCurrentRateList(result);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URI, String.class);
        List<CurrentRate> actualCurrentRateList = currentExchangeController.getInfo();

        Assert.assertNotNull(expectedCurrentRateList);
        Assertions.assertThat(responseEntity.getStatusCode().compareTo(HttpStatus.OK));
        Assert.assertEquals(expectedCurrentRateList.size(), actualCurrentRateList.size());

        verify(currentRateInfoService, times(1)).getInfo(API_URI);
        verify(jsonReader, times(1)).getCurrentRateList(result);
    }
}
