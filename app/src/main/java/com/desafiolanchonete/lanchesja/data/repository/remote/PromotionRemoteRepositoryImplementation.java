package com.desafiolanchonete.lanchesja.data.repository.remote;

import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.model.Promotion;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class PromotionRemoteRepositoryImplementation implements PromotionRemoteRepositoryContract {

    private Webservice mWebservice;

    public PromotionRemoteRepositoryImplementation(Webservice webservice) {
        mWebservice = webservice;
    }

    @Override
    public OperationResult<List<Promotion>> getPromotionList() {
        OperationResult<List<Promotion>> result = new OperationResult<>();

        try {
            Response<ResponseBody> response = mWebservice.requestPromotionList().execute();
            if (response != null && response.body() != null) {
                String body = response.body().string();

                Type listType = new TypeToken<ArrayList<Promotion>>(){}.getType();
                List<Promotion> promotionList = new Gson().fromJson(body, listType);
                result.setResult(promotionList);
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
