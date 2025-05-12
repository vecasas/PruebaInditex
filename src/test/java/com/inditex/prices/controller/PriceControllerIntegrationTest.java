package com.inditex.prices.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCorrectPriceForGivenParameters() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2020-06-14-10.00.00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.priceList").value(1));
    }

    @Test
    void shouldReturnNotFoundForInvalidParameters() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("date", "2021-01-01-00.00.00")
                        .param("productId", "99999")
                        .param("brandId", "99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value(containsString("Precio no encontrado")));
    }
}