package com.desafiolanchonete.lanchesja.presenter.promotionlist;

public class PromotionListPresenter implements PromotionListContract.Presenter {

    private PromotionListContract.View mView;

    public PromotionListPresenter(PromotionListContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }
}
