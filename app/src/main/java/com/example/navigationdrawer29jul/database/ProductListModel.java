package com.example.navigationdrawer29jul.database;

import android.content.Context;

public class ProductListModel {
    private String name;
    private String color;
    private String ram;
    private String storage;
    private String price;
    private String imgUrl;

    public ProductListModel(String name, String color, String ram, String storage, String price, String imgUrl) {
        this.name = name;
        this.color = color;
        this.ram = ram;
        this.storage = storage;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductListModel(Context context) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
