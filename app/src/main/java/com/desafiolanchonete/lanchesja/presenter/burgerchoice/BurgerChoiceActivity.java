package com.desafiolanchonete.lanchesja.presenter.burgerchoice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.google.gson.Gson;

public class BurgerChoiceActivity extends AppCompatActivity {

    public static final String INTENT_DATA_BURGER = "INTENT_DATA_BURGER";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        BurgerChoiceFragment burgerChoiceFragment = (BurgerChoiceFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_container_default);

        if (burgerChoiceFragment == null) {
            burgerChoiceFragment = BurgerChoiceFragment.newInstance();
            burgerChoiceFragment.setArguments(getIntent().getExtras());

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_container_default, burgerChoiceFragment);
            fragmentTransaction.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_burger_choice));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof BurgerChoiceContract.View) {
            BurgerChoiceContract.View view = (BurgerChoiceContract.View) fragment;
            view.setPresenter(new BurgerChoicePresenter(view));
        }
    }

    public static Intent getNewIntent(Context context, Burger burger) {
        Intent intent = new Intent(context, BurgerChoiceActivity.class);
        intent.putExtra(INTENT_DATA_BURGER, new Gson().toJson(burger));

        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;

        if (item.getItemId() == android.R.id.home) {
            finish();
            result = true;
        } else {
            result = super.onOptionsItemSelected(item);
        }

        return result;
    }
}
