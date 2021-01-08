package com.adnan.lesson7fragment;

import java.io.Serializable;

public class Food  implements Serializable{
    //
    private String foodName;
    private  String foodImage;
    private double price;
    private String foodDescription;
    private  String foodType;

    public Food(String foodName, String  foodImage, double price, String description,String foodType) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.price = price;
        this.foodDescription = description;
        this.foodType=foodType;
    }
    public Food(String foodName, String description) {
        this.foodName = foodName;
       // this.foodImage = foodImage;
       // this.price = price;
        this.foodDescription = description;
    }

    public Food() {
    }

    public Food(String foodName, String foodImageString,String description) {
        this.foodName = foodName;
        this.foodImage = foodImageString;
        this.foodDescription=description;
    }

   public double getPrice() {
       return price;
   }

    public void setPrice(double price) {
       this.price = price;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
