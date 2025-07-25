package com.example.user_service.services.user;

import com.example.user_service.dtos.ProductDto;
import com.example.user_service.models.Product;
import com.example.user_service.requests.CreateProductRequest;
import com.example.user_service.requests.UpdateProductRequest;

public interface IProductService {
    Product getUserById(Long userid);

    Product createUser(CreateProductRequest request);

    Product updateUser(UpdateProductRequest request, Long userId);

    void deleteUser(Long userId);

    ProductDto convertUserToDto(Product product);

}
