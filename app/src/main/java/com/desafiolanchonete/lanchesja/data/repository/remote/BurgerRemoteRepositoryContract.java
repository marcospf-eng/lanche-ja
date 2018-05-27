package com.desafiolanchonete.lanchesja.data.repository.remote;

import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;

import java.util.List;

public interface BurgerRemoteRepositoryContract {

    OperationResult<List<Burger>> getBurgerList();

}
