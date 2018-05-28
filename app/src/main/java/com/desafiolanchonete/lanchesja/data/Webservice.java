package com.desafiolanchonete.lanchesja.data;

import com.google.gson.JsonArray;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Webservice {

    @GET("lanche")
    Call<ResponseBody> requestBurgerList();

    @GET("ingrediente")
    Call<ResponseBody> requestAllIngredientList();

    @GET("ingrediente/de/{id}")
    Call<ResponseBody> requestBurgerIngredientList(@Path("id") int burgerId);

    @GET("pedido")
    Call<ResponseBody> requestBurgerOrderList();

    @PUT("pedido/{id}")
    Call<ResponseBody> putBurgerOrder(@Path("id") int burgerId);

    @PUT("pedido/{id}")
    Call<ResponseBody> putBurgerOrderWithExtra(@Path("id") int burgerId, @Body JsonArray jsonArray);

}

