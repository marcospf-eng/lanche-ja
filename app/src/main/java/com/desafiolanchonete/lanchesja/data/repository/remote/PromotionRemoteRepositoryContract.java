package com.desafiolanchonete.lanchesja.data.repository.remote;

import com.desafiolanchonete.lanchesja.data.model.Promotion;
import com.desafiolanchonete.lanchesja.infrastructure.OperationResult;

import java.util.List;

public interface PromotionRemoteRepositoryContract {

    OperationResult<List<Promotion>> getPromotionList();

}
