package com.example.tiendasara.demo.servers;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tiendasara.demo.models.Mark;

public interface MarkRepository extends JpaRepository<Mark, Integer>{
    
}
