package com.desafiolanchonete.lanchesja.presenter.burgerchoice;

import com.desafiolanchonete.lanchesja.BasePresenter;
import com.desafiolanchonete.lanchesja.BaseView;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;

import java.util.List;

public interface BurgerChoiceContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String message);
        void callIngredientListActivity();
        void callShoppingCartActivity();
        void initView(String burgerFormattedPrice, boolean isCustom, String mCustomIngredientList);
        void updateView(String burgerFormattedPrice, boolean isCustom, String mCustomIngredientList);
        void loadingControl(boolean visibility);
    }

    interface Presenter extends BasePresenter {
        void configurePresenter(Burger burger);
        void customBurgerClick();
        void addCartClick();
        void updateCustomChanges(List<Ingredient> ingredientList);
    }

}
