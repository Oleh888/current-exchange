package com.current.exchange.controller;

import com.current.exchange.exception.BadArgumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CurrentExchangeControllerTest {
    private static final String BAD_URI = "https://bad-uri";
    private static final String BAD_ARGUMENT = "bad_arguments";
    private static final String VALID_ARGUMENT = "Євро";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getInfoShouldReturnNotFound() throws Exception {
        mockMvc.perform(get(BAD_URI)).andExpect(status().isNotFound());
    }

    @Test
    public void getInfoWithArgShouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/exchange/get-info/{VALID_ARGUMENT}", VALID_ARGUMENT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getInfoWithArgShouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/exchange/get-info/{BAD_ARGUMENT}", BAD_ARGUMENT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentException))
                .andExpect(result -> assertEquals(BAD_ARGUMENT + " is a bad arguments",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
