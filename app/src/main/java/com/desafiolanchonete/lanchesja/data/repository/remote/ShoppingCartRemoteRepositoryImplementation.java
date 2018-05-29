package com.desafiolanchonete.lanchesja.data.repository.remote;

import android.support.annotation.Nullable;

import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.model.Order;
import com.desafiolanchonete.lanchesja.data.model.request.AddExtrasBurgerCartRequest;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ShoppingCartRemoteRepositoryImplementation implements ShoppingCartRemoteRepositoryContract {

    private Webservice mWebservice;

    public ShoppingCartRemoteRepositoryImplementation(Webservice webservice) {
        mWebservice = webservice;
    }

    @Override
    public OperationResult<Order> setBurgerOrder(int burgerId, @Nullable AddExtrasBurgerCartRequest addExtrasBurgerCartRequest) {
        OperationResult<Order> result = new OperationResult<>();
        Response<ResponseBody> response = null;
        try {
            if (addExtrasBurgerCartRequest != null &&
                    addExtrasBurgerCartRequest.getIngredientsExtra() != null &&
                    addExtrasBurgerCartRequest.getIngredientsExtra().size() > 0) {
                response = mWebservice.putBurgerOrderWithExtra(burgerId, addExtrasBurgerCartRequest).execute();
            } else {
                response = mWebservice.putBurgerOrder(burgerId).execute();
            }

            if (response != null && response.body() != null) {
                String body = response.body().string();

                Order order = new Gson().fromJson(body, Order.class);
                result.setResult(order);
            }

            result.setType(OperationResult.Type.SUCCESS);
        } catch (IOException e) {
            result.setType(OperationResult.Type.ERROR);
            e.printStackTrace();
        } catch (Exception e) {
            result.setType(OperationResult.Type.ERROR);
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public OperationResult<List<Order>> getBurgerOrderList() {
        OperationResult<List<Order>> result = new OperationResult<>();

        try {
            Response<ResponseBody> response = mWebservice.requestBurgerOrderList().execute();
            if (response != null && response.body() != null) {
                String body = response.body().string();

                Type listType = new TypeToken<ArrayList<Order>>(){}.getType();
                List<Order> orderList = new Gson().fromJson(body, listType);
                result.setResult(orderList);
            }
            result.setType(OperationResult.Type.SUCCESS);
        } catch (IOException e) {
            result.setType(OperationResult.Type.ERROR);
            e.printStackTrace();
        } catch (Exception e) {
            result.setType(OperationResult.Type.ERROR);
            e.printStackTrace();
        }

        return result;
    }
}
