package com.inditex.prices.application;

import com.inditex.prices.domain.Price;
import com.inditex.prices.domain.port.PriceRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceServiceTest {

    @Test
    void testGetPrice() {
        // Mock del repositorio
        PriceRepository priceRepository = mock(PriceRepository.class);
        PriceService priceService = new PriceService(priceRepository);

        // Datos de prueba
        LocalDateTime startDate = LocalDateTime.of(2023, 10, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 10, 31, 23, 59);
        LocalDateTime queryDate = LocalDateTime.of(2023, 10, 15, 10, 0);
        Price expectedPrice = new Price(1L, 35455L, 1, startDate, endDate, 35.50, "EUR");

        // Configuración del mock
        when(priceRepository.findPrice(queryDate, 35455L, 1L)).thenReturn(Optional.of(expectedPrice));

        // Ejecución
        Price result = priceService.getPrice(queryDate, 35455L, 1L);

        // Validación completa
        assertNotNull(result);
        assertEquals(expectedPrice.getId(), result.getId());
        assertEquals(expectedPrice.getProductId(), result.getProductId());
        assertEquals(expectedPrice.getBrandId(), result.getBrandId());
        assertEquals(expectedPrice.getStartDate(), result.getStartDate());
        assertEquals(expectedPrice.getEndDate(), result.getEndDate());
        assertEquals(expectedPrice.getPrice(), result.getPrice());
        assertEquals(expectedPrice.getCurr(), result.getCurr());
    }
}