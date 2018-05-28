package com.desafiolanchonete.lanchesja.presenter.shoppingcart;

import com.desafiolanchonete.lanchesja.data.Business.ShoppingCartBusiness;
import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.WebserviceManager;
import com.desafiolanchonete.lanchesja.data.model.Order;
import com.desafiolanchonete.lanchesja.data.repository.remote.ShoppingCartRemoteRepositoryImplementation;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;

import java.util.List;

public class ShoppingCartPresenter implements ShoppingCartContract.Presenter {

    private ShoppingCartContract.View mView;
    private ShoppingCartBusiness mShoppingCartBusiness;

    private OperationListener<List<Order>> mCartListOperationListener = new OperationListener<List<Order>>() {
        @Override
        public void onSuccess(List<Order> orders) {
            if (orders != null && !orders.isEmpty()) {
                mView.showOrderList(orders);
            } else {
                mView.showEmptyState();
            }
        }

        @Override
        public void onError() {

        }
    };

    public ShoppingCartPresenter(ShoppingCartContract.View view) {
        mView = view;
        Webservice webservice = WebserviceManager.getApiInstance();
        mShoppingCartBusiness = new ShoppingCartBusiness(
                new ShoppingCartRemoteRepositoryImplementation(webservice),
                null,
                mCartListOperationListener);
    }

    @Override
    public void start() {
        mShoppingCartBusiness.getBurgerOrderList();
    }
}
