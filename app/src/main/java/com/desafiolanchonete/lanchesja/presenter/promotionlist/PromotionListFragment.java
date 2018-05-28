package com.desafiolanchonete.lanchesja.presenter.promotionlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desafiolanchonete.lanchesja.R;

import butterknife.ButterKnife;

public class PromotionListFragment extends Fragment implements PromotionListContract.View {

    private PromotionListContract.Presenter mPresenter;

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
}
