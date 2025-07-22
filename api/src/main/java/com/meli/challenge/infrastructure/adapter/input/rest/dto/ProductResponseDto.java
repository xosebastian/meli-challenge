package com.meli.challenge.infrastructure.adapter.input.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO with complete product information")
public class ProductResponseDto {
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private String currency;
    private int stock;
    private List<String> images;
    private List<VariantDto> variants;
    private PaymentOptionsDto paymentOptions;
    private DeliveryDto delivery;
    private SellerDto seller;
    private RatingsDto ratings;
    private List<FeatureDto> features;
    private List<ReviewDto> reviews;
    private MetadataDto metadata;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VariantDto {
        private String label;
        private List<String> options;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentOptionsDto {
        private List<String> methods;
        private String installments;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryDto {
        private String type;
        private String estimatedDate;
        private BigDecimal cost;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SellerDto {
        private String name;
        private double rating;
        private int reviews;
        private String reputation;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RatingsDto {
        private double average;
        private int count;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FeatureDto {
        private String name;
        private String value;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewDto {
        private String user;
        private String comment;
        private int rating;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetadataDto {
        private String seoTitle;
        private String seoDescription;
        private String category;
        private String brand;
        private String availability;
    }
}
