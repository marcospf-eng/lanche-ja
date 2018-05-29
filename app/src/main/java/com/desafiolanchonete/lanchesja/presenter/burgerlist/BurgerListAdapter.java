package com.desafiolanchonete.lanchesja.presenter.burgerlist;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.desafiolanchonete.lanchesja.infrastructure.ImageHelper;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BurgerListAdapter extends RecyclerView.Adapter<BurgerListAdapter.BurgerViewHolder> {

    private List<Burger> mBurgerList;
    private BurgerListContract.AdapterItemAction mAdapterItemAction;

    public BurgerListAdapter(List<Burger> burgerList, BurgerListContract.AdapterItemAction adapterItemAction) {
        mBurgerList = burgerList;
        mAdapterItemAction = adapterItemAction;
    }

    @Override
    public BurgerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_burger, parent, false);
        return new BurgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BurgerViewHolder holder, int position) {
        holder.initView(mBurgerList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBurgerList.size();
    }

    public class BurgerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_burger)
        ImageView mBurgerImage;
        @Bind(R.id.txt_burger_name)
        TextView mName;
        @Bind(R.id.txt_burger_price)
        TextView mPrice;
        @Bind(R.id.txt_burger_ingredients)
        TextView mIngredients;

        public BurgerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void initView(final Burger burger) {
            ImageHelper.loadImageByUrl(burger.getImageUrl(), mBurgerImage, R.drawable.ic_launcher_background);
            mName.setText(burger.getName());
            mPrice.setText(burger.getFormattedPrice());
            mIngredients.setText(burger.getFormattedIngredientList());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapterItemAction.onClick(burger);
                }
            });
        }
    }
}
