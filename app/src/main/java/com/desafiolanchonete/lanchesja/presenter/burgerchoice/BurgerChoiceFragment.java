package com.desafiolanchonete.lanchesja.presenter.burgerchoice;

import android.app.ProgressDialog;
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

import com.desafiolanchonete.lanchesja.BaseActivity;
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
    private ProgressDialog mProgressDialog;

    @Bind(R.id.img_burger_choice) ImageView mBurgerImage;
    @Bind(R.id.txt_burger_choice_name) TextView mBurgerName;
    @Bind(R.id.txt_burger_choice_price) TextView mBurgerPrice;
    @Bind(R.id.txt_burger_choice_ingredients) TextView mBurgerIngredients;
    @Bind(R.id.txt_burger_choice_ingredients_extras) TextView mBurgerIngredientsExtras;
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
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).showToast(getContext(), message);
        }
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
    public void initView(String burgerFormattedPrice, boolean isCustom, String mCustomIngredientList) {
        ImageHelper.loadImageByUrl(mBurger.getImageUrl(), mBurgerImage, R.drawable.ic_launcher_background);
        mBurgerPrice.setText(burgerFormattedPrice);
        mBurgerIngredients.setText(mBurger.getFormattedIngredientList());

        if (isCustom) {
            mBurgerName.setText(getString(R.string.custom_burger_your_way, mBurger.getName()));
        } else {
            mBurgerName.setText(mBurger.getName());
        }

        mBurgerIngredientsExtras.setText(getString(R.string.custom_burger_ingredients_extras, mCustomIngredientList));
        mCustomBurger.setOnClickListener(mCustomBurgerClickListener);
        mAddCart.setOnClickListener(mAddCartClickListener);
    }

    @Override
    public void updateView(String burgerFormattedPrice, boolean isCustom, String mCustomIngredientList) {
        mBurgerPrice.setText(burgerFormattedPrice);

        if (isCustom) {
            mBurgerName.setText(getString(R.string.custom_burger_your_way, mBurger.getName()));
        } else {
            mBurgerName.setText(mBurger.getName());
        }

        mBurgerIngredientsExtras.setText(getString(R.string.custom_burger_ingredients_extras, mCustomIngredientList));
    }

    @Override
    public void loadingControl(boolean visibility) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading_message));
        }

        if (getActivity() != null) {
            if (!visibility && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            } else {
                mProgressDialog.show();
            }
        }
    }

}
