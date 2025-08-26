package com.mycompany.core;

public class Product {
    private String id;
    private String name;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String productID) {
        this.id = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
