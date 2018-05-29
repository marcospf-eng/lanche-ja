package com.desafiolanchonete.lanchesja.presenter.promotionlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Promotion;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PromotionListAdapter extends RecyclerView.Adapter<PromotionListAdapter.PromotionViewHolder> {

    private List<Promotion> mPromotionList;

    public PromotionListAdapter(List<Promotion> promotionList) {
        mPromotionList = promotionList;
    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion, parent, false);
        return new PromotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position) {
        holder.initView(mPromotionList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPromotionList.size();
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_promotion_name) TextView mPromotionName;
        @Bind(R.id.txt_promotion_description) TextView mPromotionDescription;

        public PromotionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void initView(Promotion promotion) {
            mPromotionName.setText(promotion.getName());
            mPromotionDescription.setText(promotion.getDescription());
        }
    }

}
