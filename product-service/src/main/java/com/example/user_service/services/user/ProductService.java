package com.example.user_service.services.user;

import com.example.user_service.dtos.ProductDto;
import com.example.user_service.exceptions.AlreadyExistException;
import com.example.user_service.exceptions.ResourceNotFoundException;
import com.example.user_service.models.Product;
import com.example.user_service.repositories.ProductRepository;
import com.example.user_service.requests.CreateProductRequest;
import com.example.user_service.requests.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product createUser(CreateProductRequest request){
        return Optional.of(request)
                .filter(user -> !productRepository.existsByName(request.getName()))
                .map(this::assignUserData)
                .orElseThrow(() -> new AlreadyExistException(request.getName() + " already exists"));
    }

    private Product assignUserData(CreateProductRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setInventory(request.getInventory());
        return productRepository.save(product);
    }

    @Override
    public Product getUserById(Long userid) {
        return productRepository.findById(userid)
                .orElseThrow(()-> new ResourceNotFoundException(userid + " not found"));
    }

    @Override
    public Product updateUser(UpdateProductRequest request, Long userId) {
        return productRepository.findById(userId)
                .map(existingProduct -> updateUserData(existingProduct,request))
                .orElseThrow(() -> new ResourceNotFoundException("Product not Found"));
    }

    private Product updateUserData(Product product, UpdateProductRequest request) {
        product.setPrice(request.getPrice());
        product.setInventory(request.getInventory());
        return productRepository.save(product);
    }

    @Override
    public void deleteUser(Long userId) {
        productRepository.findById(userId)
                .ifPresentOrElse(productRepository::delete,()->{
                    throw new ResourceNotFoundException("Product not Found");
                });
    }

    @Override
    public ProductDto convertUserToDto(Product product){
        return modelMapper.map(product, ProductDto.class);
    }
}
