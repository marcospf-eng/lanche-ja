package com.desafiolanchonete.lanchesja.presenter.burgerlist;

import com.desafiolanchonete.lanchesja.BasePresenter;
import com.desafiolanchonete.lanchesja.BaseView;
import com.desafiolanchonete.lanchesja.data.model.Burger;

import java.util.List;

public interface BurgerListContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void showBurgerList(List<Burger> burgerList);
        void showEmptyState();
        void callBurgerChoiceActivity(Burger selectedBurger);
    }

    interface Presenter extends BasePresenter {
        void onBurgerSelected(Burger burger);
    }

    interface AdapterItemAction {
        void onClick(Burger selectedBurger);
    }

}
