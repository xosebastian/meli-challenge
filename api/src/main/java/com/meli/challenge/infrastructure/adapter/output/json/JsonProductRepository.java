package com.meli.challenge.infrastructure.adapter.output.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.challenge.domain.model.Product;
import com.meli.challenge.domain.repository.ProductRepository;

@Repository
public class JsonProductRepository implements ProductRepository {

    private final Map<String, Product> productMap;

    public JsonProductRepository(ObjectMapper objectMapper) throws IOException {
        try (InputStream is = new ClassPathResource("products.json").getInputStream()) {
            List<Product> products = objectMapper.readValue(is, new TypeReference<List<Product>>(){});
            this.productMap = products.stream().collect(Collectors.toMap(Product::id, p -> p));
        }
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productMap.get(id));
    }
}
