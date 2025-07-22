package com.meli.challenge.infrastructure.adapter.input.rest;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.challenge.application.service.ProductService;
import com.meli.challenge.infrastructure.adapter.input.rest.dto.ProductResponseDto;
import com.meli.challenge.infrastructure.adapter.input.rest.mapper.ProductMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Validated
@Tag(name = "Products", description = "API for MercadoLibre product management")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @Operation(
            summary = "Get product by ID",
            description = """
                    Returns detailed information of a specific product using its MercadoLibre ID.
                    
                    **Behavior:**
                    - Search first in cache (Redis) to optimize performance
                    - If not in cache, search in local repository (JSON)
                    - Save result in cache for future queries
                    
                    **ID Format:**
                    - Must follow MercadoLibre standard format (e.g., MLA123456)
                    - Cannot be empty or null
                    """,
            tags = {"Products"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class),
                            examples = @ExampleObject(
                                    name = "Product example",
                                    summary = "Successful response example",
                                    value = """
                                            {
                                              "id": "MLA123456",
                                              "title": "Celular Samsung Galaxy A54",
                                              "description": "Pantalla Super AMOLED de 6.4'', 128 GB de almacenamiento",
                                              "price": 189999.99,
                                              "currency": "ARS",
                                              "stock": 15,
                                              "images": [
                                                "https://example.com/img1.jpg",
                                                "https://example.com/img2.jpg"
                                              ],
                                              "variants": [
                                                {
                                                  "label": "Color",
                                                  "options": ["Negro", "Blanco"]
                                                }
                                              ],
                                              "paymentOptions": {
                                                "methods": ["Tarjeta de crédito", "Transferencia bancaria", "Efectivo"],
                                                "installments": "6x $31.666 sin interés"
                                              },
                                              "delivery": {
                                                "type": "express",
                                                "estimatedDate": "2024-06-15",
                                                "cost": 0
                                              },
                                              "seller": {
                                                "name": "TechStore",
                                                "rating": 4.8,
                                                "reviews": 1024,
                                                "reputation": "gold"
                                              },
                                              "ratings": {
                                                "average": 4.5,
                                                "count": 235
                                              },
                                              "features": [
                                                {
                                                  "name": "Pantalla",
                                                  "value": "6.4'' Super AMOLED"
                                                },
                                                {
                                                  "name": "Almacenamiento",
                                                  "value": "128 GB"
                                                }
                                              ],
                                              "reviews": [
                                                {
                                                  "user": "Juan Pérez",
                                                  "comment": "Excelente calidad de imagen",
                                                  "rating": 5
                                                },
                                                {
                                                  "user": "Ana Gómez",
                                                  "comment": "Muy bueno, pero el soporte es bajo",
                                                  "rating": 4
                                                }
                                              ],
                                              "metadata": {
                                                "seoTitle": "Samsung Galaxy A54 al mejor precio | TechStore",
                                                "seoDescription": "Comprá el Samsung Galaxy A54 con pantalla Super AMOLED, 128 GB y 15 unidades disponibles. ¡Aprovechá esta oferta!",
                                                "category": "Celulares",
                                                "brand": "Samsung",
                                                "availability": "in_stock"
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid product ID",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Validation error",
                                    summary = "Empty or invalid ID",
                                    value = """
                                            {
                                              "error": "Product ID cannot be blank",
                                              "status": 400
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Product not found",
                                    summary = "Product ID does not exist",
                                    value = """
                                            {
                                              "message": "Product not found: MLA999999",
                                              "timestamp": "2024-05-20T10:15:30Z",
                                              "status": 404
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Internal error",
                                    summary = "Unexpected system error",
                                    value = """
                                            {
                                              "error": "Internal server error",
                                              "status": 500
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(
            @Parameter(
                    name = "id",
                    description = "Unique product ID in MercadoLibre (e.g., MLA123456)",
                    required = true,
                    example = "MLA123456",
                    schema = @Schema(
                            type = "string",
                            pattern = "^MLA[0-9]+$",
                            minLength = 4,
                            maxLength = 20
                    )
            )
            @PathVariable @NotBlank(message = "Product ID cannot be blank") String id) {
        var product = productService.getProductById(id);
        return new ResponseEntity<>(ProductMapper.toDto(product), HttpStatus.OK);
    }
}
