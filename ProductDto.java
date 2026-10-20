package com.example.tiendasara.demo.models;

import jakarta.validation.constraints.*;

public class ProductDto {

    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres")
    @Size(max = 2000, message = "La descripción no puede superar los 2000 caracteres")
    private String description;

    @Min(0)
    private double price;

    @Min(0)
    private int amount;

    private Category category;
    private Mark mark;

    // Getters y Setters
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public Mark getMark() {
        return mark;
    }
    public void setMark(Mark mark) {
        this.mark = mark;
    }
}