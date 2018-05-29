package com.desafiolanchonete.lanchesja.data.model.request;

import android.widget.Adapter;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class AddExtrasBurgerCartRequest {

    @SerializedName("extras")
    private JsonArray ingredientsExtra;

    public AddExtrasBurgerCartRequest() {
        /* Do nothing */
    }

    public JsonArray getIngredientsExtra() {
        return ingredientsExtra;
    }

    public void setIngredientsExtra(JsonArray ingredientsExtra) {
        this.ingredientsExtra = ingredientsExtra;
    }
}
