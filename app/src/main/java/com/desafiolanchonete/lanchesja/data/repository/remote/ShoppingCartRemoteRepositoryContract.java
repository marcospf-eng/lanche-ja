package com.desafiolanchonete.lanchesja.data.repository.remote;

import android.support.annotation.Nullable;

import com.desafiolanchonete.lanchesja.data.model.Order;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;
import com.google.gson.JsonArray;

import java.util.List;

public interface ShoppingCartRemoteRepositoryContract {

    OperationResult<Order> setBurgerOrder(int burgerId, @Nullable JsonArray extraIngredients);

    OperationResult<List<Order>> getBurgerOrderList();

}
