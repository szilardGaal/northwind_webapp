package com.codecool.web.model;

public class Task5 {

    private String Company;
    private String Product;
    private double Price;

    public Task5(String Company, String Product, double Price) {
        this.Company = Company;
        this.Product = Product;
        this.Price = Price;
    }

    public String getCompany() {
        return Company;
    }

    public String getProduct() {
        return Product;
    }

    public double getPrice() {
        return Price;
    }
}
