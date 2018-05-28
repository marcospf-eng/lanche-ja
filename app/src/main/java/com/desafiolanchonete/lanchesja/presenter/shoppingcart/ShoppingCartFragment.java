package com.desafiolanchonete.lanchesja.presenter.shoppingcart;

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
import com.desafiolanchonete.lanchesja.data.model.Order;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends Fragment implements ShoppingCartContract.View {

    private ShoppingCartContract.Presenter mPresenter;

    @Bind(R.id.rv_shopping_cart)
    RecyclerView mShoppingCartList;

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void setPresenter(ShoppingCartContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showOrderList(List<Order> orderList) {
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(orderList);
        mShoppingCartList.setLayoutManager(new LinearLayoutManager(getContext()));
        mShoppingCartList.setAdapter(shoppingCartAdapter);
    }

    @Override
    public void showEmptyState() {

    }
}
