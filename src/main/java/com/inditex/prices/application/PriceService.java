package com.inditex.prices.application;

import com.inditex.prices.domain.Price;
import com.inditex.prices.domain.port.PriceRepository;

import java.time.LocalDateTime;

public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPrice(LocalDateTime date, Long productId, Long brandId) {
        return priceRepository.findPrice(date, productId, brandId)
                .orElseThrow(() -> new RuntimeException("Precio no encontrado"));
    }
}