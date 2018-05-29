package com.desafiolanchonete.lanchesja.presenter.ingredientlist;

import com.desafiolanchonete.lanchesja.data.Business.BurgerBusiness;
import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.WebserviceManager;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;
import com.desafiolanchonete.lanchesja.data.repository.remote.BurgerRemoteRepositoryImplementation;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;

import java.util.ArrayList;
import java.util.List;

public class IngredientListPresenter implements IngredientListContract.Presenter {

    private IngredientListContract.View mView;
    private BurgerBusiness mBurgerBusiness;
    private List<Ingredient> mIngredientList;

    private OperationListener<List<Ingredient>> mIngredientListOperationListener = new OperationListener<List<Ingredient>>() {
        @Override
        public void onSuccess(List<Ingredient> ingredients) {
            if (ingredients != null && !ingredients.isEmpty()) {
                mIngredientList = ingredients;
                mView.showIngredientList(mIngredientList);
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

    public IngredientListPresenter(IngredientListContract.View view) {
        mView = view;
        Webservice webservice = WebserviceManager.getApiInstance();
        mBurgerBusiness = new BurgerBusiness(
                new BurgerRemoteRepositoryImplementation(webservice),
                null,
                mIngredientListOperationListener);
    }

    @Override
    public void start() {
        mView.loadingControl(true);
        mBurgerBusiness.getAllIngredientList();
    }

    @Override
    public void onIngredientQuantityUpdated(int position, int quantity) {
        mIngredientList.get(position).setExtraQuantity(quantity);
    }

    @Override
    public void onFinishSelectionClick() {
        List<Ingredient> ingredientsSelection = new ArrayList<>();
        for (Ingredient ingredient : mIngredientList) {
            if (ingredient.getExtraQuantity() > 0) {
                ingredientsSelection.add(ingredient);
            }
        }

        mView.finishActivity(ingredientsSelection);
    }
}
