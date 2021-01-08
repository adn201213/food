package com.adnan.lesson7fragment;

import java.io.Serializable;

public class Appetizer implements Serializable {
    private String appetizerName;
    private  int appetizerImage;
    private double price;
    private String description;
    public Appetizer() {
    }

    public Appetizer(String appetizerName, int appetizerImage, double price, String description) {
        this.appetizerName = appetizerName;
        this.appetizerImage = appetizerImage;
        this.price = price;
        this.description = description;
    }

    public Appetizer(String appetizerName, int foodImage) {
        this.appetizerName = appetizerName;
        this.appetizerImage = foodImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppetizerName() {
        return appetizerName;
    }

    public void setAppetizerName(String appetizerName) {
        this.appetizerName = appetizerName;
    }

    public int getAppetizerImage() {
        return appetizerImage;
    }

    public void setAppetizerImage(int appetizerImage) {
        this.appetizerImage = appetizerImage;
    }
}
