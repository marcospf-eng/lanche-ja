package com.desafiolanchonete.lanchesja.data.Business;

import android.os.Handler;
import android.os.Looper;

import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.data.repository.remote.BurgerRemoteRepositoryContract;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;

import java.util.List;

public class BurgerBusiness {

    private BurgerRemoteRepositoryContract mBurgerRemoteRepositoryContract;
    private OperationListener<List<Burger>> mBurgerOperationListener;

    public BurgerBusiness(BurgerRemoteRepositoryContract burgerRemoteRepositoryContract,
                          OperationListener<List<Burger>> burgerOperationListener) {
        mBurgerRemoteRepositoryContract = burgerRemoteRepositoryContract;
        mBurgerOperationListener = burgerOperationListener;
    }

    public void getBurgerList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callbackExecution(mBurgerRemoteRepositoryContract.getBurgerList());
            }
        }).start();
    }

    private void callbackExecution(final OperationResult<List<Burger>> operationResult) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (operationResult.getType() == OperationResult.Type.SUCCESS) {
                    mBurgerOperationListener.onSuccess(operationResult.getResult());
                } else {
                    mBurgerOperationListener.onError();
                }
            }
        });
    }

}
