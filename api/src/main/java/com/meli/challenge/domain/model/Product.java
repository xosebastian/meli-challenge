package com.meli.challenge.domain.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Immutable domain model representing a product with all the information
 * required to render a MercadoLibre style detail page.
 */
public record Product(
        String id,
        String title,
        String description,
        BigDecimal price,
        String currency,
        int stock,
        List<String> images,
        List<Variant> variants,
        PaymentOptions paymentOptions,
        Delivery delivery,
        Seller seller,
        Ratings ratings,
        List<Feature> features,
        List<Review> reviews,
        Metadata metadata) {

    public record Variant(String label, List<String> options) { }

    public record PaymentOptions(List<String> methods, String installments) { }

    public record Delivery(String type, String estimatedDate, BigDecimal cost) { }

    public record Seller(String name, double rating, int reviews, String reputation) { }

    public record Ratings(double average, int count) { }

    public record Feature(String name, String value) { }

    public record Review(String user, String comment, int rating) { }

    public record Metadata(String seoTitle, String seoDescription, String category,
                           String brand, String availability) { }
}
