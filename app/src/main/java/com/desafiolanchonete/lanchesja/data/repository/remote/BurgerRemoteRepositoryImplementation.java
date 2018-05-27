package com.desafiolanchonete.lanchesja.data.repository.remote;

import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class BurgerRemoteRepositoryImplementation implements BurgerRemoteRepositoryContract {

    private Webservice mWebservice;

    public BurgerRemoteRepositoryImplementation(Webservice webservice) {
        mWebservice = webservice;
    }

    @Override
    public OperationResult<List<Burger>> getBurgerList() {
        OperationResult<List<Burger>> result = new OperationResult<>();

        try {
            Response<ResponseBody> response = mWebservice.requestBurgerList().execute();
            if (true) {
                Type listType = new TypeToken<ArrayList<Burger>>(){}.getType();
                List<Burger> burgerList = new Gson().fromJson(response.body().string(), listType);
                result.setResult(burgerList);
            }
            result.setType(OperationResult.Type.SUCCESS);
        } catch (IOException e) {
            result.setType(OperationResult.Type.ERROR);
            e.printStackTrace();
        }

        return result;
    }

}
