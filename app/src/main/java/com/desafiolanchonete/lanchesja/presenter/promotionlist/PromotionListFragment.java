package com.desafiolanchonete.lanchesja.presenter.promotionlist;

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
import com.desafiolanchonete.lanchesja.data.model.Promotion;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PromotionListFragment extends Fragment implements PromotionListContract.View {

    private PromotionListContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Bind(R.id.rv_promotion_list) RecyclerView mPromotionList;
    @Bind(R.id.fragment_empty_state) View mEmptyStateView;

    public static PromotionListFragment newInstance() {
        return new PromotionListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotion_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void setPresenter(PromotionListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showEmptyState() {
        mPromotionList.setVisibility(View.INVISIBLE);
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

    @Override
    public void showMessage(String message) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).showToast(getContext(), message);
        }
    }

    @Override
    public void showPromotionList(List<Promotion> promotions) {
        PromotionListAdapter promotionListAdapter = new PromotionListAdapter(promotions);
        mPromotionList.setLayoutManager(new LinearLayoutManager(getContext()));
        mPromotionList.setAdapter(promotionListAdapter);
    }
}
