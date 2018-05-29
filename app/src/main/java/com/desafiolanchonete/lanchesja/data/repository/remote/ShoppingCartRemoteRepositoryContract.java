package com.desafiolanchonete.lanchesja.data.repository.remote;

import android.support.annotation.Nullable;

import com.desafiolanchonete.lanchesja.data.model.Order;
import com.desafiolanchonete.lanchesja.data.model.request.AddExtrasBurgerCartRequest;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;
import com.google.gson.JsonArray;

import java.util.List;

public interface ShoppingCartRemoteRepositoryContract {

    OperationResult<Order> setBurgerOrder(int burgerId, @Nullable AddExtrasBurgerCartRequest addExtrasBurgerCartRequest);

    OperationResult<List<Order>> getBurgerOrderList();

}
