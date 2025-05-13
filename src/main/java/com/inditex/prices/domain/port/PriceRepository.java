package com.inditex.prices.domain.port;

import com.inditex.prices.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findPrice(LocalDateTime date, Long productId, Long brandId);
}