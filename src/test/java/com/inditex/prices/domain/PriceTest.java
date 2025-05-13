// src/test/java/com/inditex/prices/domain/PriceTest.java
package com.inditex.prices.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PriceTest {

    @Test
    void testPriceCreation() {
        com.inditex.prices.domain.Price price = new com.inditex.prices.domain.Price(1L, 1L, 1, null, null, 100.0, "EUR");
        assertNotNull(price);
        assertEquals(100.0, price.getPrice());
    }
}