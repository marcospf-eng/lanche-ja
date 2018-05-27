package com.desafiolanchonete.lanchesja.data.model;

import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Burger {

    private int id;
    private String name;
    @SerializedName("ingredients")
    private List<Ingredient> ingredientList;
    @SerializedName("image")
    private String imageUrl;

    public Burger() {
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

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getBurgerPrice() {
        Double price = 0.0;
        for (Ingredient ingredient : ingredientList) {
            price += ingredient.getPrice();
        }
        return price;
    }

    public String getFormattedPrice() {
        Locale ptBr = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(ptBr).format(getBurgerPrice());
    }

    public String getFormattedIngredientsList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Ingredient ingredient : ingredientList) {
            stringBuilder.append(ingredient.getName());
            if (!ingredientList.get(ingredientList.size() - 1).equals(ingredient)) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(".");
            }
        }
        return stringBuilder.toString();
    }
}
