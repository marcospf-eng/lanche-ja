package com.desafiolanchonete.lanchesja.presenter.burgerlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.desafiolanchonete.lanchesja.R;

public class BurgerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        BurgerListFragment burgerListFragment = (BurgerListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_container_default);

        if (burgerListFragment == null) {
            burgerListFragment = BurgerListFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_container_default, burgerListFragment);
            fragmentTransaction.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.title_burger_list));
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof BurgerListContract.View) {
            BurgerListContract.View view = (BurgerListContract.View) fragment;
            view.setPresenter(new BurgerListPresenter(view));
        }
    }
}
