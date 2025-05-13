package com.inditex.prices.adapter.out;

import com.inditex.prices.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(PriceRepositoryImpl.class)
class PriceRepositoryImplTest {

    @Autowired
    private PriceRepositoryImpl priceRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        // Inserta datos de prueba
        Price price = new Price();
        price.setProductId(35455L);
        price.setBrandId(1L);
        price.setPriceList(1);
        price.setStartDate(LocalDateTime.parse("2020-06-14-00.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")));
        price.setEndDate(LocalDateTime.parse("2020-12-31-23.59.59", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")));
        price.setPrice(35.50);
        price.setCurr("EUR");
        entityManager.persist(price);
    }

    @Test
    void testFindPrice() {
        LocalDateTime date = LocalDateTime.parse("2020-06-14-00.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));
        Optional<Price> price = priceRepository.findPrice(date, 35455L, 1L);

        // Validación completa
        assertTrue(price.isPresent());
        Price result = price.get();
        assertEquals(35455L, result.getProductId());
        assertEquals(1L, result.getBrandId());
        assertEquals(1, result.getPriceList());
        assertEquals(LocalDateTime.parse("2020-06-14-00.00.00", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")), result.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31-23.59.59", DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss")), result.getEndDate());
        assertEquals(35.50, result.getPrice());
        assertEquals("EUR", result.getCurr());
    }
}