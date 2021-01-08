package com.adnan.lesson7fragment;

import java.io.Serializable;

public class FoodMap1{
    private String first1;
    private Food value1;

    public FoodMap1() {
    }

    public FoodMap1(String first1, Food value1) {
        this.first1 = first1;
        this.value1 = value1;
    }

    public String getFirst1() {
        return first1;
    }

    public void setFirst1(String first) {
        this.first1 = first1;
    }

    public Food getValue1() {
        return value1;
    }

    public void setValue1(Food value1) {
        this.value1 = value1;
    }
}
