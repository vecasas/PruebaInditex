package com.inditex.prices.controller;

import com.inditex.prices.dto.PriceResponse;
import com.inditex.prices.exception.EntityNotFoundException;
import com.inditex.prices.service.PriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<?> getPrice(
            @RequestParam String date,
            @RequestParam Long productId,
            @RequestParam Long brandId) {

        try {
            LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

            return priceService.getPrice(dateTime, productId, brandId)
                    .map(p -> new PriceResponse(p.getProductId(), p.getBrandId(), p.getPriceList(),
                            p.getStartDate(), p.getEndDate(), p.getPrice(), p.getCurr()))
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new EntityNotFoundException("Precio no encontrado"));

        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato de fecha inválido. Use 'yyyy-MM-dd-HH.mm.ss'.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
        }
    }
}