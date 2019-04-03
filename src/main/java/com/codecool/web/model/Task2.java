package com.codecool.web.model;


public final class Task2 {

    private int numberOFProducts;
    private String company;

    public Task2(int numberOFProducts, String company) {
        this.numberOFProducts = numberOFProducts;
        this.company = company;
    }

    public int getNumberOfProducts() {
        return numberOFProducts;
    }

    public String getCompany() {
        return company;
    }

}
