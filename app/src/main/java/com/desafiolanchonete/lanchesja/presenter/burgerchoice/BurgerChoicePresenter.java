package com.desafiolanchonete.lanchesja.presenter.burgerchoice;

import android.util.Pair;

import com.desafiolanchonete.lanchesja.data.Business.ShoppingCartBusiness;
import com.desafiolanchonete.lanchesja.data.Webservice;
import com.desafiolanchonete.lanchesja.data.WebserviceManager;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;
import com.desafiolanchonete.lanchesja.data.model.Order;
import com.desafiolanchonete.lanchesja.data.repository.remote.ShoppingCartRemoteRepositoryImplementation;
import com.desafiolanchonete.lanchesja.infrastructure.OperationListener;
import com.google.gson.JsonArray;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BurgerChoicePresenter implements BurgerChoiceContract.Presenter {

    private BurgerChoiceContract.View mView;
    private ShoppingCartBusiness mShoppingCartBusiness;
    private Burger mBurger;
    private List<Ingredient> mCustomIngredientList;

    private OperationListener<Order> mBurgerAddCartOperationListener = new OperationListener<Order>() {
        @Override
        public void onSuccess(Order order) {
            mView.callShoppingCartActivity();
        }

        @Override
        public void onError() {

        }
    };

    public BurgerChoicePresenter(BurgerChoiceContract.View view) {
        mView = view;
        Webservice webservice = WebserviceManager.getApiInstance();
        mShoppingCartBusiness = new ShoppingCartBusiness(
                new ShoppingCartRemoteRepositoryImplementation(webservice),
                mBurgerAddCartOperationListener,
                null);
    }

    @Override
    public void start() {
        mView.initView(calculateBurgerPrice(), mCustomIngredientList != null && !mCustomIngredientList.isEmpty());
    }

    @Override
    public void configurePresenter(Burger burger) {
        mBurger = burger;
    }

    @Override
    public void customBurgerClick() {
        mView.callIngredientListActivity();
    }

    @Override
    public void addCartClick() {
        mShoppingCartBusiness.setBurgerOrder(mBurger.getId(), getPreparedExtraIngredients());
    }

    @Override
    public void updateCustomChanges(List<Ingredient> ingredientList) {
        mCustomIngredientList = ingredientList;
        mView.updateView(calculateBurgerPrice(), !mCustomIngredientList.isEmpty());
    }

    public String calculateBurgerPrice() {
        Double price = 0.0;
        // <IngredientId, <Quantity, Value>>
        HashMap<Integer, Pair<Integer, Double>> map = new HashMap<>();
        List<Ingredient> allIngredients = new ArrayList<>(mBurger.getIngredientList());
        if (mCustomIngredientList != null && !mCustomIngredientList.isEmpty()) {
            allIngredients.addAll(mCustomIngredientList);
        }

        for (Ingredient ingredient : allIngredients) {
            int quantity = (ingredient.getExtraQuantity() == 0) ? 1 : ingredient.getExtraQuantity();

            if (map.containsKey(ingredient.getId())) {
                Integer count = map.get(ingredient.getId()).first;
                count += quantity;
                map.put(ingredient.getId(), Pair.create(count, ingredient.getPrice()));
            } else {
                map.put(ingredient.getId(), Pair.create(quantity, ingredient.getPrice()));
            }
        }

        // hasDiscountTenPercent
        // alface = 1
        // bacon = 2
        // hamburger = 3
        // queijo = 5

        boolean hasDiscount = map.containsKey(1) && !map.containsKey(2);

        if (map.containsKey(3)) {
            int meatQuantity = map.get(3).first;
            Double meatPrice = map.get(3).second;

            if (meatQuantity > 3) {
                meatQuantity = (int)(Math.floor(meatQuantity / 3) * 2) + meatQuantity % 3;
                map.put(3, Pair.create(meatQuantity, meatPrice));
            }
        }

        if (map.containsKey(5)) {
            int cheeseQuantity = map.get(5).first;
            Double cheesePrice = map.get(5).second;

            if (cheeseQuantity > 3) {
                cheeseQuantity = (int)(Math.floor(cheeseQuantity / 3) * 2) + cheeseQuantity % 3;
                map.put(3, Pair.create(cheeseQuantity, cheesePrice));
            }
        }

        for (Map.Entry<Integer, Pair<Integer, Double>> entry : map.entrySet()) {
            price += entry.getValue().first * entry.getValue().second;
        }

        if (hasDiscount) {
            price = price * 0.9;
        }

        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(locale).format(price);
    }

    private JsonArray getPreparedExtraIngredients() {
        JsonArray extraIngredients = null;

        if (mCustomIngredientList != null && !mCustomIngredientList.isEmpty()) {
            extraIngredients = new JsonArray();
            for (Ingredient ingredient : mCustomIngredientList) {
                extraIngredients.add(ingredient.getId());
            }
        }

        return extraIngredients;
    }
}
