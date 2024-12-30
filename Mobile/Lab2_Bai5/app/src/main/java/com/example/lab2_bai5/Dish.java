package com.example.lab2_bai5;

public class Dish {
    private String name;
    private int thumbnail; // Resource ID của hình
    private boolean hasPromotion;

    public Dish(String name, int thumbnail, boolean hasPromotion) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.hasPromotion = hasPromotion;
    }

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public boolean hasPromotion() {
        return hasPromotion;
    }
}
