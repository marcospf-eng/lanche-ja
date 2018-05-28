package com.desafiolanchonete.lanchesja.data.Business;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;
import com.desafiolanchonete.lanchesja.data.repository.remote.BurgerRemoteRepositoryContract;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;

import java.util.List;

public class BurgerBusiness extends BaseBusiness {

    private BurgerRemoteRepositoryContract mBurgerRemoteRepositoryContract;
    private OperationListener<List<Burger>> mBurgerOperationListener;
    private OperationListener<List<Ingredient>> mIngredientListOperationListener;

    public BurgerBusiness(BurgerRemoteRepositoryContract burgerRemoteRepositoryContract,
                          @Nullable OperationListener<List<Burger>> burgerOperationListener,
                          @Nullable OperationListener<List<Ingredient>> ingredientListOperationListener) {
        mBurgerRemoteRepositoryContract = burgerRemoteRepositoryContract;
        mBurgerOperationListener = burgerOperationListener;
        mIngredientListOperationListener = ingredientListOperationListener;
    }

    public void getBurgerList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callbackExecution(mBurgerRemoteRepositoryContract.getBurgerList(), mBurgerOperationListener);
            }
        }).start();
    }

    public void getAllIngredientList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callbackExecution(mBurgerRemoteRepositoryContract.getAllIngredientList(), mIngredientListOperationListener);
            }
        }).start();
    }

}
