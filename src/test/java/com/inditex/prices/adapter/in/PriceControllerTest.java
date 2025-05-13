// src/test/java/com/inditex/prices/adapter/in/PriceControllerTest.java
package com.inditex.prices.adapter.in;

import com.inditex.prices.application.PriceService;
import com.inditex.prices.domain.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    void testGetPrice() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        Price price = new Price(1L, 1L, 1, date, date, 100.0, "EUR");
        when(priceService.getPrice(any(), anyLong(), anyLong())).thenReturn(price);

        mockMvc.perform(get("/api/prices")
                        .param("date", "2023-01-01-00.00.00")
                        .param("productId", "1")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(100.0));
    }
}