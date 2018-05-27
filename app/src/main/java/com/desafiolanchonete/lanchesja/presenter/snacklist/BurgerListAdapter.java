package com.desafiolanchonete.lanchesja.presenter.snacklist;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Burger;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BurgerListAdapter extends RecyclerView.Adapter<BurgerListAdapter.BurgerViewHolder> {

    private List<Burger> mBurgerList;

    public BurgerListAdapter(List<Burger> burgerList) {
        mBurgerList = burgerList;
    }

    @Override
    public BurgerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_burger, parent, false);
        return new BurgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BurgerViewHolder holder, int position) {
//        Picasso.with(holder.mBurguerImage.getContext())
//                .load("https://goo.gl/W9WdaF")
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(holder.mBurguerImage);

        holder.populateView(mBurgerList.get(position));
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

        public void populateView(Burger burger) {
            loadImage(burger.getImageUrl());
            mName.setText(burger.getName());
            mPrice.setText(burger.getFormattedPrice());
            mIngredients.setText(burger.getFormattedIngredientsList());
        }

        private void loadImage(String imageUrl) {
            Picasso.Builder builder = new Picasso.Builder(mBurgerImage.getContext());
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                /*holder.getIvSpeakerPicture()
                        .setImageDrawable(context.getResources()
                                .getDrawable("your drawable id"));*/
                }
            });
            builder.downloader(new OkHttpDownloader(mBurgerImage.getContext()));
            builder.build().load(imageUrl).placeholder(R.drawable.ic_launcher_background).into(mBurgerImage);
        }
    }
}
