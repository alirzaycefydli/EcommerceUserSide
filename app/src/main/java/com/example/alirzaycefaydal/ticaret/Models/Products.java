package com.example.alirzaycefaydal.ticaret.Models;

public class Products {
    private String product_description;
    private String product_name;
    private String product_price;
    private String image;
    private String date;

    public Products(){}

    public Products(String product_description, String product_name, String product_price, String image, String date) {
        this.product_description = product_description;
        this.product_name = product_name;
        this.product_price = product_price;
        this.image = image;
        this.date = date;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
