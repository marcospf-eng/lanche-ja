package com.desafiolanchonete.lanchesja.data.Business;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.desafiolanchonete.lanchesja.data.model.Order;
import com.desafiolanchonete.lanchesja.data.repository.remote.ShoppingCartRemoteRepositoryContract;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;
import com.google.gson.JsonArray;

import java.util.List;

public class ShoppingCartBusiness extends BaseBusiness {

    private ShoppingCartRemoteRepositoryContract mShoppingCartRemoteRepositoryContract;
    private OperationListener<Order> mBurgerAddCartOperationListener;
    private OperationListener<List<Order>> mCartListOperationListener;

    public ShoppingCartBusiness(ShoppingCartRemoteRepositoryContract shoppingCartRemoteRepositoryContract,
                                @Nullable OperationListener<Order> burgerAddCartOperationListener,
                                @Nullable OperationListener<List<Order>> cartListOperationListener) {
        mShoppingCartRemoteRepositoryContract = shoppingCartRemoteRepositoryContract;
        mBurgerAddCartOperationListener = burgerAddCartOperationListener;
        mCartListOperationListener = cartListOperationListener;
    }

    public void setBurgerOrder(final int burgerId, @Nullable final JsonArray extraIngredients) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callbackExecution(mShoppingCartRemoteRepositoryContract.setBurgerOrder(burgerId, extraIngredients), mBurgerAddCartOperationListener);
            }
        }).start();
    }

    public void getBurgerOrderList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callbackExecution(mShoppingCartRemoteRepositoryContract.getBurgerOrderList(), mCartListOperationListener);
            }
        }).start();
    }

}
