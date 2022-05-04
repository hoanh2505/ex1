package com.example.ex1.model;

public class Exercise {

    private String name, unit;
    private int img;
    private String price;
    private boolean online;

    public Exercise(String name, String unit, int img, String price, boolean online) {
        this.name = name;
        this.unit = unit;
        this.img = img;
        this.price = price;
        this.online = online;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Exercise() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
