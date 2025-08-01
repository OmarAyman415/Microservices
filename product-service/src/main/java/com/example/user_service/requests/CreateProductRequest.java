package com.example.user_service.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
}
