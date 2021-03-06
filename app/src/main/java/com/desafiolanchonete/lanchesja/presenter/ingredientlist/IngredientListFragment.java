package com.desafiolanchonete.lanchesja.presenter.ingredientlist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.desafiolanchonete.lanchesja.BaseActivity;
import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class IngredientListFragment extends Fragment implements IngredientListContract.View {

    private IngredientListContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Bind(R.id.rv_ingredient_list) RecyclerView mIngredientList;
    @Bind(R.id.button_finish_ingredient_selection) Button mFinishSelection;
    @Bind(R.id.fragment_empty_state) View mEmptyStateView;

    private IngredientListContract.AdapterItemAction mAdapterItemAction = new IngredientListContract.AdapterItemAction() {
        @Override
        public void onQuantityUpdated(int position, int quantity) {
            mPresenter.onIngredientQuantityUpdated(position, quantity);
        }
    };

    private View.OnClickListener m0nFinishSelectionClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onFinishSelectionClick();
        }
    };

    public static IngredientListFragment newInstance() {
        return new IngredientListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void setPresenter(IngredientListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showIngredientList(List<Ingredient> ingredientList) {
        IngredientListAdapter ingredientListAdapter = new IngredientListAdapter(ingredientList, mAdapterItemAction);
        mIngredientList.setLayoutManager(new LinearLayoutManager(getContext()));
        mIngredientList.setAdapter(ingredientListAdapter);
    }

    @Override
    public void showEmptyState() {
        mIngredientList.setVisibility(View.INVISIBLE);
        mFinishSelection.setVisibility(View.INVISIBLE);
        mEmptyStateView.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishActivity(List<Ingredient> ingredientsSelection) {
        Intent data = new Intent();
        data.putExtra(IngredientListActivity.INTENT_DATA_INGREDIENTS, new Gson().toJson(ingredientsSelection));
        getActivity().setResult(RESULT_OK, data);
        getActivity().finish();
    }

    private void initView() {
        mFinishSelection.setOnClickListener(m0nFinishSelectionClick);
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

}
