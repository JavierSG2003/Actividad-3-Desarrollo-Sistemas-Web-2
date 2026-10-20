package com.example.tiendasara.demo.servers;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.tiendasara.demo.models.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
