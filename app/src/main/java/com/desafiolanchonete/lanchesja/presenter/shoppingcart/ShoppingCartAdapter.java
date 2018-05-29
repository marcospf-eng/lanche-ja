package com.desafiolanchonete.lanchesja.presenter.shoppingcart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desafiolanchonete.lanchesja.R;
import com.desafiolanchonete.lanchesja.data.model.Order;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.CartViewHolder> {

    private List<Order> mOrderList;

    public ShoppingCartAdapter(List<Order> orderList) {
        mOrderList = orderList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        holder.initView(mOrderList.get(position));
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_order_burger_name) TextView mBurgerName;
        @Bind(R.id.txt_order_burger_extras) TextView mExtras;
        @Bind(R.id.txt_order_burger_date) TextView mOrderDate;

        public CartViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void initView(final Order order) {
            mBurgerName.setText(order.getBurger().getName());
            mExtras.setText(itemView.getContext().getString(R.string.custom_burger_ingredients_extras,
                    order.getFormattedIngredients()));
            mOrderDate.setText(order.getFormattedDate());
        }
    }

}
