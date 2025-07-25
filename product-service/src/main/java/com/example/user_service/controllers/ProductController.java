package com.example.user_service.controllers;

import com.example.user_service.dtos.ProductDto;
import com.example.user_service.exceptions.ResourceNotFoundException;
import com.example.user_service.models.Product;
import com.example.user_service.requests.CreateProductRequest;
import com.example.user_service.requests.UpdateProductRequest;
import com.example.user_service.respones.ApiResponse;
import com.example.user_service.services.user.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @RequestMapping("/{productId}/product")
    public ResponseEntity<ApiResponse> getUser(@PathVariable long productId) {
        try{
            Product product = productService.getUserById(productId);
            ProductDto productDto = productService.convertUserToDto(product);
            return ResponseEntity.ok(new ApiResponse("Product:("+ product.getName() + ") retrieved Successfully", productDto));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody CreateProductRequest request) {
        try {
            Product product = productService.createUser(request);
            ProductDto productDto = productService.convertUserToDto(product);
            return ResponseEntity.ok(new ApiResponse("Product:("+ product.getName() + ") created Successfully", productDto));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable long productId, @RequestBody UpdateProductRequest request) {
        try{
            Product product = productService.updateUser(request, productId);
            ProductDto productDto = productService.convertUserToDto(product);
            return ResponseEntity.ok(new ApiResponse("Product:("+ product.getName() + ") updated Successfully", productDto));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable long productId) {
        try{
            Product product = productService.getUserById(productId);
            productService.deleteUser(productId);
            return ResponseEntity.ok(new ApiResponse("Product:("+ product.getName()+") deleted successfully",null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

}
