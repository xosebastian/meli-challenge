package com.meli.challenge.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.meli.challenge.domain.model.Product;
import com.meli.challenge.domain.model.Product.Delivery;
import com.meli.challenge.domain.model.Product.Feature;
import com.meli.challenge.domain.model.Product.Metadata;
import com.meli.challenge.domain.model.Product.PaymentOptions;
import com.meli.challenge.domain.model.Product.Ratings;
import com.meli.challenge.domain.model.Product.Seller;
import com.meli.challenge.domain.model.Product.Variant;
import com.meli.challenge.domain.repository.ProductRepository;
import com.meli.challenge.infrastructure.exception.ProductNotFoundException;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private RedisTemplate<String, Product> redisTemplate;
    @Mock
    private ValueOperations<String, Product> valueOps;

    private ProductService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOps);
        service = new ProductService(repository, redisTemplate);
    }

    @Test
    void shouldLoadFromRepositoryAndCache() {
        Product product = sampleProduct();
        when(valueOps.get("MLA123")).thenReturn(null);
        when(repository.findById("MLA123")).thenReturn(Optional.of(product));

        Product result = service.getProductById("MLA123");

        verify(repository).findById("MLA123");
        verify(valueOps).set("MLA123", product);
        assertEquals(product, result);
    }

    @Test
    void shouldReturnFromCacheWhenProductIsCached() {
        Product product = sampleProduct();
        when(valueOps.get("MLA123")).thenReturn(product);

        Product result = service.getProductById("MLA123");

        verify(valueOps).get("MLA123");
        verify(repository, never()).findById(any());
        verify(valueOps, never()).set(any(), any());
        assertEquals(product, result);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(valueOps.get("INVALID_ID")).thenReturn(null);
        when(repository.findById("INVALID_ID")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, 
                () -> service.getProductById("INVALID_ID"));

        verify(repository).findById("INVALID_ID");
        verify(valueOps, never()).set(any(), any());
    }

    private Product sampleProduct() {
        return new Product(
                "MLA123",
                "name",
                "desc",
                BigDecimal.TEN,
                "ARS",
                1,
                List.of(),
                List.of(new Variant("v", List.of("o"))),
                new PaymentOptions(List.of("card"), "1 cuota"),
                new Delivery("envio", "mañana", BigDecimal.ZERO),
                new Seller("s", 1.0, 1, "gold"),
                new Ratings(1.0, 1),
                List.of(new Feature("f", "v")),
                List.of(new Product.Review("u", "c", 5)),
                new Metadata("t", "d", "c", "b", "a")
        );
    }
}
