package com.meli.challenge.infrastructure.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.meli.challenge.infrastructure.exception.dto.ErrorResponse;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleProductNotFoundException() {
        String errorMessage = "Product not found: MLA123456";
        ProductNotFoundException exception = new ProductNotFoundException(errorMessage);
        
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleProductNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().getMessage());
    }

    @Test
    void shouldHandleProductNotFoundExceptionWithDifferentMessage() {
        String errorMessage = "Product not available";
        ProductNotFoundException exception = new ProductNotFoundException(errorMessage);
        
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleProductNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(errorMessage, response.getBody().getMessage());
    }
} 