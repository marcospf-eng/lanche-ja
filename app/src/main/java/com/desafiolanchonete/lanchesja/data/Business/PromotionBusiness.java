package com.desafiolanchonete.lanchesja.data.Business;

import com.desafiolanchonete.lanchesja.data.model.Promotion;
import com.desafiolanchonete.lanchesja.data.repository.remote.PromotionRemoteRepositoryContract;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;

import java.util.List;

public class PromotionBusiness extends BaseBusiness {

    private PromotionRemoteRepositoryContract mPromotionRemoteRepositoryContract;
    private OperationListener<List<Promotion>> mPromotionListOperationListener;

    public PromotionBusiness(PromotionRemoteRepositoryContract promotionRemoteRepositoryContract,
                             OperationListener<List<Promotion>> promotionListOperationListener) {
        mPromotionRemoteRepositoryContract = promotionRemoteRepositoryContract;
        mPromotionListOperationListener = promotionListOperationListener;
    }

    public void gerPromotionList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callbackExecution(mPromotionRemoteRepositoryContract.getPromotionList(), mPromotionListOperationListener);
            }
        }).start();
    }

}
