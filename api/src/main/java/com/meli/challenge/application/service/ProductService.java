package com.meli.challenge.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import com.meli.challenge.domain.model.Product;
import com.meli.challenge.domain.repository.ProductRepository;
import com.meli.challenge.infrastructure.exception.ProductNotFoundException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RedisTemplate<String, Product> redisTemplate;

    public ProductService(ProductRepository productRepository,
                          RedisTemplate<String, Product> redisTemplate) {
        this.productRepository = productRepository;
        this.redisTemplate = redisTemplate;
    }

    public Product getProductById(String id) {
        Product cached = redisTemplate.opsForValue().get(id);
        if (cached != null) {
            return cached;
        }

        Optional<Product> optional = productRepository.findById(id);
        Product product = optional.orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
        redisTemplate.opsForValue().set(id, product);
        return product;
    }
}
