package com.example.user_service.repositories;

import com.example.user_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(long id);
    boolean existsByName(String name);

}
