package com.desafiolanchonete.lanchesja.presenter.ingredientlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Ingredient;
import com.desafiolanchonete.lanchesja.infrastructure.ImageHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientsViewHolder> {

    private List<Ingredient> mIngredientList;
    private IngredientListContract.AdapterItemAction mAdapterItemAction;

    public IngredientListAdapter(List<Ingredient> ingredientList, IngredientListContract.AdapterItemAction adapterItemAction) {
        mIngredientList = ingredientList;
        mAdapterItemAction = adapterItemAction;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        holder.initView(mIngredientList.get(position));
    }

    @Override
    public int getItemCount() {
        return mIngredientList.size();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_ingredient) ImageView mIngredientImage;
        @Bind(R.id.txt_ingredient_name) TextView mIngredientName;
        @Bind(R.id.txt_ingredient_price) TextView mIngredientPrice;
        @Bind(R.id.np_ingredient_quantity) NumberPicker mIngredientQuantity;

        public IngredientsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void initView(Ingredient ingredient) {
            ImageHelper.loadImageByUrl(ingredient.getImageUrl(), mIngredientImage, R.drawable.ic_launcher_background);
            mIngredientName.setText(ingredient.getName());
            mIngredientPrice.setText(ingredient.getFormattedPrice());
            mIngredientQuantity.setMinValue(0);
            mIngredientQuantity.setMaxValue(20);
            mIngredientQuantity.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    mAdapterItemAction.onQuantityUpdated(getAdapterPosition(), newVal);
                }
            });
        }
    }

}
