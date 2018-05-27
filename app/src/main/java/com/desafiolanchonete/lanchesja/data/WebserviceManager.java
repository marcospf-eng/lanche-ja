package com.desafiolanchonete.lanchesja.data;

import com.desafiolanchonete.lanchesja.infrastructure.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class WebserviceManager {

    public static Webservice getApiInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Webservice.class);
    }

}
