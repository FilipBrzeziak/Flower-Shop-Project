package com.example.flowershopproject.model;

import com.example.flowershopproject.entity.Product;

public class ProductInfo {
    private String code;
    private String name;
    private String colour;
    private String category;
    private double price;

    public ProductInfo() {
    }

    public ProductInfo(Product product) {
        this.code = product.getCode();
        this.colour = product.getCategory();
        this.category = product.getCategory();
        this.name = product.getName();
        this.price = product.getPrice();


    }

    public ProductInfo(String code, String colour, String category, String name, double price) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.colour = colour;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
