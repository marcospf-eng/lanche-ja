package com.desafiolanchonete.lanchesja.presenter.snacklist;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.desafiolanchonete.lanchesja.R;

public class SnackListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_list);

        SnackListFragment snackListFragment = (SnackListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_container_snack_list);

        if (snackListFragment == null) {
            snackListFragment = SnackListFragment.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_container_snack_list, snackListFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof SnackListFragment) {
            SnackListFragment snackListFragment = (SnackListFragment) fragment;
            snackListFragment.setPresenter(new SnackListPresenter(snackListFragment));
        }
    }
}
