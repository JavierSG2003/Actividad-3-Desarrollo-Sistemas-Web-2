package com.example.tiendasara.demo.servers;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tiendasara.demo.models.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
