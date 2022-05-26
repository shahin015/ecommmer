package com.developr.me_commars.model;

public class Product {
    private String name,image,status;
    private  double price,discount;
    private int storck,id;

    public Product(String name, String image, String status, double price, double discount, int storck, int id) {
        this.name = name;
        this.image = image;
        this.status = status;
        this.price = price;
        this.discount = discount;
        this.storck = storck;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getStorck() {
        return storck;
    }

    public void setStorck(int storck) {
        this.storck = storck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
