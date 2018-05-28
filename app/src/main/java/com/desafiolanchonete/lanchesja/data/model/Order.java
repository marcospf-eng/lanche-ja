package com.desafiolanchonete.lanchesja.data.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Order {

    private int id;
    @SerializedName("sandwiche")
    private Burger burger;
    private List<Integer> extras;
    @SerializedName("date")
    private Long dateMilliseconds;

    public Order() {
        /* Do nothing */
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Burger getBurger() {
        return burger;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
    }

    public List<Integer> getExtras() {
        return extras;
    }

    public void setExtras(List<Integer> extras) {
        this.extras = extras;
    }

    public Long getDateMilliseconds() {
        return dateMilliseconds;
    }

    public void setDateMilliseconds(Long dateMilliseconds) {
        this.dateMilliseconds = dateMilliseconds;
    }

    public String getFormattedDate() {
        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", locale);
        return formatter.format(new Date(dateMilliseconds));
    }
}
