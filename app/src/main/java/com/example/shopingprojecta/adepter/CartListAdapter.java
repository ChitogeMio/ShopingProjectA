package com.example.shopingprojecta.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopingprojecta.databinding.CartRowBinding;
import com.example.shopingprojecta.models.CartItem;

public class CartListAdapter extends ListAdapter<CartItem,CartListAdapter.CartVH> {

    private CartInterface cartInterface;
    public CartListAdapter(CartInterface cartInterface) {
        super(CartItem.itemCallback);
        this.cartInterface = cartInterface;
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

            cartRowBinding.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartInterface.deleteItem(getItem(getLayoutPosition()));

                }
            });

           cartRowBinding.quantitySpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   int quantily = i +1;
                   if (quantily==getItem(getBindingAdapterPosition()).getQuantity()){
                                        //why getBindingAdapterPosition not getAdapterPosition
                       return;
                   }
                   cartInterface.changeQuatily(getItem(getBindingAdapterPosition()), quantily); //why getBindingAdapterPosition not getAdapterPosition
               }

               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {

               }
           });

        }
    }

    public interface CartInterface {

        void deleteItem(CartItem cartItem);
        void changeQuatily(CartItem cartItem, int quantily);


    }

}
