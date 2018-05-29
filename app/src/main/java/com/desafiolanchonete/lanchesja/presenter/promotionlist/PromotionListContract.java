package com.desafiolanchonete.lanchesja.presenter.promotionlist;

import com.desafiolanchonete.lanchesja.BasePresenter;
import com.desafiolanchonete.lanchesja.BaseView;
import com.desafiolanchonete.lanchesja.data.model.Promotion;

import java.util.List;

public interface PromotionListContract {

    interface View extends BaseView<Presenter> {
        void showEmptyState();
        void loadingControl(boolean visibility);
        void showMessage(String message);
        void showPromotionList(List<Promotion> promotions);
    }

    interface Presenter extends BasePresenter {

    }

}
