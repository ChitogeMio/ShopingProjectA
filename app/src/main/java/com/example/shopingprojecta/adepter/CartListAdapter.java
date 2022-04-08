package com.example.shopingprojecta.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopingprojecta.databinding.CartRowBinding;
import com.example.shopingprojecta.models.CartItem;

public class CartListAdapter extends ListAdapter<CartItem,CartListAdapter.CartVH> {
    public CartListAdapter() {
        super(CartItem.itemCallback);
    }

    @NonNull
    @Override
    public CartListAdapter.CartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartRowBinding cartRowBinding = CartRowBinding.inflate(layoutInflater,parent,false);

        return new CartVH(cartRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.CartVH holder, int position) {

        holder.cartRowBinding.setCartItem(getItem(position));
        holder.cartRowBinding.executePendingBindings();

    }

    class CartVH extends RecyclerView.ViewHolder{


        CartRowBinding cartRowBinding;

        public CartVH(@NonNull CartRowBinding cartRowBinding) {
            super(cartRowBinding.getRoot());
            this.cartRowBinding=cartRowBinding;

        }
    }

}
