package com.desafiolanchonete.lanchesja.data.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    private int id;
    private String name;
    private Double price;
    @SerializedName("image")
    private String imageUrl;

    public Ingredient() {
        /* Do nothing */
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
