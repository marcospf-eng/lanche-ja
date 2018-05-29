package com.desafiolanchonete.lanchesja.presenter.burgerlist;

import com.desafiolanchonete.lanchesja.data.Business.BurgerBusiness;
import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.WebserviceManager;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.data.repository.remote.BurgerRemoteRepositoryImplementation;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;

import java.util.List;

public class BurgerListPresenter implements BurgerListContract.Presenter {

    private BurgerListContract.View mView;
    private BurgerBusiness mBurgerBusiness;

    private OperationListener<List<Burger>> burgerListOperationListener = new OperationListener<List<Burger>>() {
        @Override
        public void onSuccess(List<Burger> burgers) {
            if (burgers != null && !burgers.isEmpty()) {
                mView.showBurgerList(burgers);
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

    public BurgerListPresenter(BurgerListContract.View view) {
        mView = view;
        Webservice webservice = WebserviceManager.getApiInstance();
        mBurgerBusiness = new BurgerBusiness(
                new BurgerRemoteRepositoryImplementation(webservice),
                burgerListOperationListener,
                null);
    }

    @Override
    public void start() {
        mView.loadingControl(true);
        mBurgerBusiness.getBurgerList();
    }

    @Override
    public void onBurgerSelected(Burger burger) {
        mView.callBurgerChoiceActivity(burger);
    }
}
