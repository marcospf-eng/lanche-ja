package com.desafiolanchonete.lanchesja.presenter.shoppingcart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.desafiolanchonete.lanchesja.BaseActivity;
import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Order;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends Fragment implements ShoppingCartContract.View {

    private ShoppingCartContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Bind(R.id.rv_shopping_cart) RecyclerView mShoppingCartList;
    @Bind(R.id.fragment_empty_state) View mEmptyStateView;

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
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).showToast(getContext(), message);
        }
    }

    @Override
    public void showOrderList(List<Order> orderList) {
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(orderList);
        mShoppingCartList.setLayoutManager(new LinearLayoutManager(getContext()));
        mShoppingCartList.setAdapter(shoppingCartAdapter);
    }

    @Override
    public void showEmptyState() {
        mShoppingCartList.setVisibility(View.INVISIBLE);
        mEmptyStateView.setVisibility(View.VISIBLE);
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
