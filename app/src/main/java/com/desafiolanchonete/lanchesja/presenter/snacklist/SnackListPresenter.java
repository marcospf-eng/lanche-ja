package com.desafiolanchonete.lanchesja.presenter.snacklist;

import com.desafiolanchonete.lanchesja.data.Business.BurgerBusiness;
import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.WebserviceManager;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.data.repository.remote.BurgerRemoteRepositoryImplementation;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;

import java.util.List;

public class SnackListPresenter implements SnackListContract.Presenter {

    private SnackListContract.View mView;
    private BurgerBusiness mBurgerBusiness;

    private OperationListener<List<Burger>> burgerListOperationListener = new OperationListener<List<Burger>>() {
        @Override
        public void onSuccess(List<Burger> burgers) {
            if (burgers != null && !burgers.isEmpty()) {
                mView.showBurgerList(burgers);
            } else {
                mView.showEmptyState();
            }
        }

        @Override
        public void onError() {
            mView.showErrorMessage("Ops! Ocorreu algum erro, tente novamente mais tarde.");
        }
    };

    public SnackListPresenter(SnackListContract.View view) {
        mView = view;
        Webservice webservice = WebserviceManager.getApiInstance();
        mBurgerBusiness = new BurgerBusiness(new BurgerRemoteRepositoryImplementation(webservice), burgerListOperationListener);
    }

    @Override
    public void start() {
        mView.showErrorMessage("Test");
        mBurgerBusiness.getBurgerList();
    }
}
