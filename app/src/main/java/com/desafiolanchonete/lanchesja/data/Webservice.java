package com.desafiolanchonete.lanchesja.data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Webservice {

    @GET("lanche")
    Call<ResponseBody> requestBurgerList();

    @GET("ingrediente")
    Call<ResponseBody> requestAllIngredientsList();

    @GET("ingrediente/de/{id}")
    Call<ResponseBody> requestBurgerIngredientsList(@Path("id") int burgerId);

}

