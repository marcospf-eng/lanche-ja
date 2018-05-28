package com.desafiolanchonete.lanchesja.presenter.promotionlist;

import com.desafiolanchonete.lanchesja.BasePresenter;
import com.desafiolanchonete.lanchesja.BaseView;

public interface PromotionListContract {

    interface View extends BaseView<Presenter> {
        void showEmptyState();
    }

    interface Presenter extends BasePresenter {

    }

}
