package com.adnan.lesson7fragment;

import java.io.Serializable;

public class Sweet implements Serializable {
    private String sweetName;
    private  int sweetImage;
    private double price;
    private String description;

    public Sweet(String sweetName, int sweetImage, double price, String description) {
        this.sweetName = sweetName;
        this.sweetImage = sweetImage;
        this.price = price;
        this.description = description;
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

    public Sweet() {
    }

    public Sweet(String foodName, int sweetImage) {
        this.sweetName = foodName;
        this.sweetImage = sweetImage;
    }

    public String getSweetName() {
        return sweetName;
    }

    public void setSweetName(String sweetName) {
        this.sweetName = sweetName;
    }

    public int getSweetImage() {
        return sweetImage;
    }

    public void setSweetImage(int sweetImage) {
        this.sweetImage = sweetImage;
    }
}
