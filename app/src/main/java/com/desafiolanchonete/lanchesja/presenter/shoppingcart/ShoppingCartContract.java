package com.desafiolanchonete.lanchesja.presenter.shoppingcart;

import com.desafiolanchonete.lanchesja.BasePresenter;
import com.desafiolanchonete.lanchesja.BaseView;
import com.desafiolanchonete.lanchesja.data.model.Order;

import java.util.List;

public interface ShoppingCartContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void showOrderList(List<Order> orderList);
        void showEmptyState();
    }

    interface Presenter extends BasePresenter {

    }

}
