package com.adnan.lesson7fragment;

import java.util.HashMap;
import java.util.Map;

public class FoodMap {
    private Map<String, Food> foodListMap;

    public FoodMap() {

        //this.foodListMap=new HashMap<String, Food>();
    }

    public FoodMap(Map<String, Food> foodListMap) {
        this.foodListMap = foodListMap;
    }

    public Map<String, Food> getFoodListMap() {
        return foodListMap;
    }

    public void setFoodListMap(Map<String, Food> foodListMap) {
        this.foodListMap = foodListMap;
    }

}
