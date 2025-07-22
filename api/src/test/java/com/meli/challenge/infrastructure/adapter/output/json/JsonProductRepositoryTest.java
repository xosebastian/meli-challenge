package com.meli.challenge.infrastructure.adapter.output.json;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.challenge.domain.model.Product;

class JsonProductRepositoryTest {

    private JsonProductRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        repository = new JsonProductRepository(objectMapper);
    }

    @Test
    void shouldFindExistingProduct() {
        Optional<Product> result = repository.findById("MLA123456");
        
        assertTrue(result.isPresent());
        Product product = result.get();
        assertEquals("MLA123456", product.id());
        assertEquals("Celular Samsung Galaxy A54", product.title());
        assertEquals("ARS", product.currency());
        assertEquals(15, product.stock());
        assertNotNull(product.seller());
        assertEquals("TechStore", product.seller().name());
        assertEquals(4.8, product.seller().rating());
        assertNotNull(product.reviews());
        assertEquals(2, product.reviews().size());
        assertEquals("Juan Pérez", product.reviews().get(0).user());
    }

    @Test
    void shouldReturnEmptyForNonExistentProduct() {
        Optional<Product> result = repository.findById("INVALID_ID");
        
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyForNullId() {
        Optional<Product> result = repository.findById(null);
        
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnEmptyForEmptyId() {
        Optional<Product> result = repository.findById("");
        
        assertFalse(result.isPresent());
    }
} 