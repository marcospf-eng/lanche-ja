package com.desafiolanchonete.lanchesja.presenter.ingredientlist;

import com.desafiolanchonete.lanchesja.BasePresenter;
import com.desafiolanchonete.lanchesja.BaseView;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;

import java.util.List;

public interface IngredientListContract {

    interface View extends BaseView<Presenter> {
        void showIngredientList(List<Ingredient> ingredientList);
        void showEmptyState();
        void finishActivity(List<Ingredient> ingredientsSelection);
        void loadingControl(boolean visibility);
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void onIngredientQuantityUpdated(int position, int quantity);
        void onFinishSelectionClick();
    }

    interface AdapterItemAction {
        void onQuantityUpdated(int position, int quantity);
    }

}
