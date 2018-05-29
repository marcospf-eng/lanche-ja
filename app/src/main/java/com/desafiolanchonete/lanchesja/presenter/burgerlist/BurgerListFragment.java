package com.desafiolanchonete.lanchesja.presenter.burgerlist;

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
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.presenter.burgerchoice.BurgerChoiceActivity;
import com.desafiolanchonete.lanchesja.presenter.promotionlist.PromotionListActivity;
import com.desafiolanchonete.lanchesja.presenter.shoppingcart.ShoppingCartActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BurgerListFragment extends Fragment implements BurgerListContract.View {

    private BurgerListContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Bind(R.id.rv_burger_list) RecyclerView mBurgerList;
    @Bind(R.id.fragment_empty_state) View mEmptyStateView;

    public BurgerListContract.AdapterItemAction adapterItemAction = new BurgerListContract.AdapterItemAction() {
        @Override
        public void onClick(Burger selectedBurger) {
            mPresenter.onBurgerSelected(selectedBurger);
        }
    };

    public static BurgerListFragment newInstance() {
        return new BurgerListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_burger_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        mPresenter.start();
    }

    @Override
    public void setPresenter(BurgerListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMessage(String message) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).showToast(getContext(), message);
        }
    }

    @Override
    public void showBurgerList(List<Burger> burgerList) {
        BurgerListAdapter burgerListAdapter = new BurgerListAdapter(burgerList, adapterItemAction);
        mBurgerList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBurgerList.setAdapter(burgerListAdapter);
    }

    @Override
    public void showEmptyState() {
        mBurgerList.setVisibility(View.INVISIBLE);
        mEmptyStateView.setVisibility(View.VISIBLE);
    }

    @Override
    public void callBurgerChoiceActivity(Burger selectedBurger) {
        startActivity(BurgerChoiceActivity.getNewIntent(getContext(), selectedBurger));
    }

    @Override
    public void callPromotionListActivity() {
        startActivity(PromotionListActivity.getNewIntent(getContext()));
    }

    @Override
    public void callShoppingCartActivity() {
        startActivity(ShoppingCartActivity.getNewIntent(getContext()));
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
