package com.desafiolanchonete.lanchesja.presenter.snacklist;

import com.desafiolanchonete.lanchesja.BasePresenter;
import com.desafiolanchonete.lanchesja.BaseView;
import com.desafiolanchonete.lanchesja.data.model.Burger;

import java.util.List;

public interface SnackListContract {

    interface View extends BaseView<Presenter> {
        void showErrorMessage(String errorMessage);
        void showBurgerList(List<Burger> burgerList);
        void showEmptyState();
    }

    interface Presenter extends BasePresenter {

    }

}
