package com.example.user_service.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
}
