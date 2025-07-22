package com.meli.challenge.domain.repository;

import java.util.Optional;

import com.meli.challenge.domain.model.Product;

public interface ProductRepository {
    Optional<Product> findById(String id);
}
