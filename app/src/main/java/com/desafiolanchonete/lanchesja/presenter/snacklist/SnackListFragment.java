package com.desafiolanchonete.lanchesja.presenter.snacklist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Burger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SnackListFragment extends Fragment implements SnackListContract.View {

    private SnackListContract.Presenter mPresenter;

    @Bind(R.id.rv_burger_list)
    RecyclerView mBurgerList;

    public static SnackListFragment newInstance() {
        return new SnackListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snack_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mPresenter.start();
    }

    @Override
    public void setPresenter(SnackListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBurgerList(List<Burger> burgerList) {
        BurgerListAdapter burgerListAdapter = new BurgerListAdapter(burgerList);
        mBurgerList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBurgerList.setAdapter(burgerListAdapter);
    }

    @Override
    public void showEmptyState() {

    }
}
