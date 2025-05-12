package com.inditex.prices.service;

import com.inditex.prices.entity.Price;
import com.inditex.prices.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> getPrice(LocalDateTime date, Long productId, Long brandId) {
        List<Price> prices = priceRepository
            .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, date, date);
        return prices.stream().findFirst();
    }
}
