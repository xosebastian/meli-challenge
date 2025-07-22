package com.meli.challenge.infrastructure.adapter.input.rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.meli.challenge.domain.model.Product;
import com.meli.challenge.infrastructure.adapter.input.rest.dto.ProductResponseDto;
import com.meli.challenge.infrastructure.adapter.input.rest.dto.ProductResponseDto.DeliveryDto;
import com.meli.challenge.infrastructure.adapter.input.rest.dto.ProductResponseDto.FeatureDto;
import com.meli.challenge.infrastructure.adapter.input.rest.dto.ProductResponseDto.PaymentOptionsDto;
import com.meli.challenge.infrastructure.adapter.input.rest.dto.ProductResponseDto.RatingsDto;
import com.meli.challenge.infrastructure.adapter.input.rest.dto.ProductResponseDto.SellerDto;
import com.meli.challenge.infrastructure.adapter.input.rest.dto.ProductResponseDto.VariantDto;

public class ProductMapper {

    public static ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
                product.id(),
                product.title(),
                product.description(),
                product.price(),
                product.currency(),
                product.stock(),
                product.images(),
                mapVariants(product.variants()),
                mapPaymentOptions(product.paymentOptions()),
                mapDelivery(product.delivery()),
                mapSeller(product.seller()),
                mapRatings(product.ratings()),
                mapFeatures(product.features()),
                mapReviews(product.reviews()),
                mapMetadata(product.metadata())
        );
    }

    private static List<VariantDto> mapVariants(List<Product.Variant> variants) {
        if (variants == null) return null;
        return variants.stream()
                .map(v -> new VariantDto(v.label(), v.options()))
                .collect(Collectors.toList());
    }

    private static PaymentOptionsDto mapPaymentOptions(Product.PaymentOptions po) {
        if (po == null) return null;
        return new PaymentOptionsDto(po.methods(), po.installments());
    }

    private static DeliveryDto mapDelivery(Product.Delivery delivery) {
        if (delivery == null) return null;
        return new DeliveryDto(delivery.type(), delivery.estimatedDate(), delivery.cost());
    }

    private static SellerDto mapSeller(Product.Seller seller) {
        if (seller == null) return null;
        return new SellerDto(seller.name(), seller.rating(), seller.reviews(), seller.reputation());
    }

    private static RatingsDto mapRatings(Product.Ratings ratings) {
        if (ratings == null) return null;
        return new RatingsDto(ratings.average(), ratings.count());
    }

    private static List<FeatureDto> mapFeatures(List<Product.Feature> features) {
        if (features == null) return null;
        return features.stream()
                .map(f -> new FeatureDto(f.name(), f.value()))
                .collect(Collectors.toList());
    }

    private static List<ProductResponseDto.ReviewDto> mapReviews(List<Product.Review> reviews) {
        if (reviews == null) return null;
        return reviews.stream()
                .map(r -> new ProductResponseDto.ReviewDto(r.user(), r.comment(), r.rating()))
                .collect(Collectors.toList());
    }

    private static ProductResponseDto.MetadataDto mapMetadata(Product.Metadata metadata) {
        if (metadata == null) return null;
        return new ProductResponseDto.MetadataDto(
                metadata.seoTitle(),
                metadata.seoDescription(),
                metadata.category(),
                metadata.brand(),
                metadata.availability()
        );
    }
}
