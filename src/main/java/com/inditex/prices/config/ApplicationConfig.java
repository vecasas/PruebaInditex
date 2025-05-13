package com.inditex.prices.config;

import com.inditex.prices.application.PriceService;
import com.inditex.prices.domain.port.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PriceService priceService(PriceRepository priceRepository) {
        return new PriceService(priceRepository);
    }
}