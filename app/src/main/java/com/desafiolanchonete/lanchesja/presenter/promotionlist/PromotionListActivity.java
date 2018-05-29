package com.desafiolanchonete.lanchesja.presenter.promotionlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.desafiolanchonete.lanchesja.BaseActivity;
import com.desafiolanchonete.lanchesja.R;

public class PromotionListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        PromotionListFragment promotionListFragment = (PromotionListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_container_default);

        if (promotionListFragment == null) {
            promotionListFragment = PromotionListFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_container_default, promotionListFragment);
            fragmentTransaction.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_promotion));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof PromotionListContract.View) {
            PromotionListContract.View view = (PromotionListContract.View) fragment;
            view.setPresenter(new PromotionListPresenter(view));
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
        return new Intent(context, PromotionListActivity.class);
    }
}
