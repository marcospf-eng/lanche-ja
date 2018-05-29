package com.desafiolanchonete.lanchesja.presenter.promotionlist;

import com.desafiolanchonete.lanchesja.data.Business.PromotionBusiness;
import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.WebserviceManager;
import com.desafiolanchonete.lanchesja.data.model.Promotion;
import com.desafiolanchonete.lanchesja.data.repository.remote.PromotionRemoteRepositoryImplementation;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;

import java.util.List;

public class PromotionListPresenter implements PromotionListContract.Presenter {

    private PromotionListContract.View mView;
    private PromotionBusiness mPromotionBusiness;

    private OperationListener<List<Promotion>> mPromotionListOperationListener = new OperationListener<List<Promotion>>() {
        @Override
        public void onSuccess(List<Promotion> promotions) {
            if (promotions != null && !promotions.isEmpty()) {
                mView.showPromotionList(promotions);
            } else {
                mView.showEmptyState();
            }

            mView.loadingControl(false);
        }

        @Override
        public void onError() {
            mView.loadingControl(false);
            mView.showMessage("Ops! Ocorreu algum erro, tente novamente mais tarde.");
        }
    };

    public PromotionListPresenter(PromotionListContract.View view) {
        mView = view;
        Webservice webservice = WebserviceManager.getApiInstance();
        mPromotionBusiness = new PromotionBusiness(
                new PromotionRemoteRepositoryImplementation(webservice),
                mPromotionListOperationListener);
    }

    @Override
    public void start() {
        mView.loadingControl(true);
        mPromotionBusiness.gerPromotionList();
    }
}
