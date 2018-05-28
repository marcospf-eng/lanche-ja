package com.desafiolanchonete.lanchesja.presenter.burgerchoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;
import com.desafiolanchonete.lanchesja.infrastructure.ImageHelper;
import com.desafiolanchonete.lanchesja.presenter.ingredientlist.IngredientListActivity;
import com.desafiolanchonete.lanchesja.presenter.shoppingcart.ShoppingCartActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class BurgerChoiceFragment extends Fragment implements BurgerChoiceContract.View {

    private BurgerChoiceContract.Presenter mPresenter;
    private Burger mBurger;

    @Bind(R.id.img_burger_choice) ImageView mBurgerImage;
    @Bind(R.id.txt_burger_choice_name) TextView mBurgerName;
    @Bind(R.id.txt_burger_choice_price) TextView mBurgerPrice;
    @Bind(R.id.txt_burger_choice_ingredients) TextView mBurgerIngredients;
    @Bind(R.id.txt_burger_choice_description) TextView mBurgerDescription;
    @Bind(R.id.fab_burger_choice_custom) FloatingActionButton mCustomBurger;
    @Bind(R.id.fab_burger_choice_add_cart) FloatingActionButton mAddCart;

    private View.OnClickListener mCustomBurgerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.customBurgerClick();
        }
    };

    private View.OnClickListener mAddCartClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.addCartClick();
        }
    };

    public static BurgerChoiceFragment newInstance() {
        return new BurgerChoiceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_burger_choice, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            String burgerJson = savedInstanceState.getString(BurgerChoiceActivity.INTENT_DATA_BURGER);
            mBurger = new Gson().fromJson(burgerJson, Burger.class);
        } else {
            String burgerJson = getArguments().getString(BurgerChoiceActivity.INTENT_DATA_BURGER);
            mBurger = new Gson().fromJson(burgerJson, Burger.class);
        }

        mPresenter.configurePresenter(mBurger);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IngredientListActivity.PICK_INGREDIENTS_CHOICE) {
            if (resultCode == RESULT_OK) {
                String returnedResult = data.getStringExtra(IngredientListActivity.INTENT_DATA_INGREDIENTS);
                Type listType = new TypeToken<ArrayList<Ingredient>>(){}.getType();
                List<Ingredient> ingredientList = new Gson().fromJson(returnedResult, listType);
                mPresenter.updateCustomChanges(ingredientList);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void callIngredientListActivity() {
        startActivityForResult(IngredientListActivity.getNewIntent(getContext()),
                IngredientListActivity.PICK_INGREDIENTS_CHOICE);
    }

    @Override
    public void callShoppingCartActivity() {
        startActivity(ShoppingCartActivity.getNewIntent(getContext()));
        getActivity().finish();
    }

    @Override
    public void setPresenter(BurgerChoiceContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initView(String burgerFormattedPrice, boolean isCustom) {
        ImageHelper.loadImageByUrl(mBurger.getImageUrl(), mBurgerImage, R.drawable.ic_launcher_background);
        mBurgerPrice.setText(burgerFormattedPrice);
        mBurgerIngredients.setText(mBurger.getFormattedIngredientsList());

        if (isCustom) {
            mBurgerName.setText(getString(R.string.custom_burger_your_way, mBurger.getName()));
        } else {
            mBurgerName.setText(mBurger.getName());
        }

        mCustomBurger.setOnClickListener(mCustomBurgerClickListener);
        mAddCart.setOnClickListener(mAddCartClickListener);
    }

    @Override
    public void updateView(String burgerFormattedPrice, boolean isCustom) {
        mBurgerPrice.setText(burgerFormattedPrice);

        if (isCustom) {
            mBurgerName.setText(getString(R.string.custom_burger_your_way, mBurger.getName()));
        } else {
            mBurgerName.setText(mBurger.getName());
        }
    }


}
