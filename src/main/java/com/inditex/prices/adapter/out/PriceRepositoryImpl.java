package com.inditex.prices.adapter.out;

import com.inditex.prices.domain.Price;
import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.domain.port.PriceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceRepositoryImpl implements PriceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Price> findPrice(LocalDateTime date, Long productId, Long brandId) {
        String query = "SELECT p FROM Price p " +
                "WHERE p.productId = :productId " +
                "AND p.brandId = :brandId " +
                "AND :date BETWEEN p.startDate AND p.endDate ";

        return entityManager.createQuery(query, Price.class)
                .setParameter("productId", productId)
                .setParameter("brandId", brandId)
                .setParameter("date", date)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .or(() -> {
                    throw new PriceNotFoundException("No se ha encontrado precio.");
                });
    }
}