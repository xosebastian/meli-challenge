package com.meli.challenge.infrastructure.adapter.input.rest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.meli.challenge.application.service.ProductService;
import com.meli.challenge.domain.model.Product;
import com.meli.challenge.domain.model.Product.Delivery;
import com.meli.challenge.domain.model.Product.Feature;
import com.meli.challenge.domain.model.Product.Metadata;
import com.meli.challenge.domain.model.Product.PaymentOptions;
import com.meli.challenge.domain.model.Product.Ratings;
import com.meli.challenge.domain.model.Product.Seller;
import com.meli.challenge.domain.model.Product.Variant;
import com.meli.challenge.infrastructure.exception.ProductNotFoundException;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void shouldReturnProductWhenFound() throws Exception {
        Product product = createSampleProduct();
        when(productService.getProductById("MLA123456")).thenReturn(product);

        mockMvc.perform(get("/products/MLA123456"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id").value("MLA123456"))
               .andExpect(jsonPath("$.title").value("Celular Samsung Galaxy A54"))
               .andExpect(jsonPath("$.price").value(189999.99))
               .andExpect(jsonPath("$.currency").value("ARS"))
               .andExpect(jsonPath("$.stock").value(15))
               .andExpect(jsonPath("$.seller.name").value("TechStore"))
               .andExpect(jsonPath("$.ratings.average").value(4.5))
               .andExpect(jsonPath("$.metadata.category").value("Celulares"))
               .andExpect(jsonPath("$.reviews[0].user").value("Juan Pérez"));
    }

    @Test
    void shouldReturn404WhenProductNotFound() throws Exception {
        when(productService.getProductById("INVALID_ID"))
                .thenThrow(new ProductNotFoundException("Product not found: INVALID_ID"));

        mockMvc.perform(get("/products/INVALID_ID"))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.message").value("Product not found: INVALID_ID"))
               .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void shouldHandleEmptyId() throws Exception {
        mockMvc.perform(get("/products/"))
               .andExpect(status().isNotFound());
    }

    private Product createSampleProduct() {
        return new Product(
                "MLA123456",
                "Celular Samsung Galaxy A54",
                "Pantalla Super AMOLED de 6.4'', 128 GB de almacenamiento",
                new BigDecimal("189999.99"),
                "ARS",
                15,
                List.of("https://example.com/img1.jpg", "https://example.com/img2.jpg"),
                List.of(new Variant("Color", List.of("Negro", "Blanco"))),
                new PaymentOptions(List.of("Tarjeta de crédito", "Transferencia bancaria", "Efectivo"), "6x $31.666 sin interés"),
                new Delivery("express", "2024-06-15", BigDecimal.ZERO),
                new Seller("TechStore", 4.8, 1024, "gold"),
                new Ratings(4.5, 235),
                List.of(new Feature("Pantalla", "6.4'' Super AMOLED"), new Feature("Almacenamiento", "128 GB")),
                List.of(
                        new Product.Review("Juan Pérez", "Excelente calidad de imagen", 5),
                        new Product.Review("Ana Gómez", "Muy bueno, pero el soporte es bajo", 4)
                ),
                new Metadata(
                        "Samsung Galaxy A54 al mejor precio | TechStore",
                        "Comprá el Samsung Galaxy A54 con pantalla Super AMOLED, 128 GB y 15 unidades disponibles. ¡Aprovechá esta oferta!",
                        "Celulares",
                        "Samsung",
                        "in_stock"
                )
        );
    }
}