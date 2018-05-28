package com.desafiolanchonete.lanchesja.presenter.ingredientlist;

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

public class IngredientListActivity extends AppCompatActivity {

    public static final int PICK_INGREDIENTS_CHOICE = 1;
    public static final String INTENT_DATA_INGREDIENTS = "INTENT_DATA_INGREDIENTS";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        IngredientListFragment ingredientListFragment = (IngredientListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_container_default);

        if (ingredientListFragment == null) {
            ingredientListFragment = IngredientListFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_container_default, ingredientListFragment);
            fragmentTransaction.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_ingredients_choice));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof IngredientListContract.View) {
            IngredientListContract.View view = (IngredientListContract.View) fragment;
            view.setPresenter(new IngredientListPresenter(view));
        }
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


    public static Intent getNewIntent(Context context) {
        return new Intent(context, IngredientListActivity.class);
    }
}
