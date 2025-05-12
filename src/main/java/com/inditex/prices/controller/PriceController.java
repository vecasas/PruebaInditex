package com.inditex.prices.controller;

import com.inditex.prices.dto.PriceResponse;
import com.inditex.prices.service.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam String date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

        return priceService.getPrice(dateTime, productId, brandId)
                .map(p -> new PriceResponse(p.getProductId(), p.getBrandId(), p.getPriceList(),
                        p.getStartDate(), p.getEndDate(), p.getPrice(), p.getCurr()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
