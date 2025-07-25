package com.example.user_service.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private BigDecimal price;
    private int inventory;
}
