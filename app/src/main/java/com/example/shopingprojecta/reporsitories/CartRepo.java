package com.example.shopingprojecta.reporsitories;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopingprojecta.models.CartItem;
import com.example.shopingprojecta.models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartRepo {

    private MutableLiveData<List<CartItem>>mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double>mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<CartItem>>getCart(){
        if(mutableCart.getValue()==null){
            initCart();
        }
        return mutableCart;
    }

    public void initCart(){

        mutableCart.setValue(new ArrayList<CartItem>());
        caculateCartTotal();
    }

    public boolean addItemtoCart(Product product){
        if(mutableCart.getValue()==null){
            initCart();
        }

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        for (CartItem cartItem:cartItemList){

            if(cartItem.getProduct().getId_a().equals(product.getId_a())){

                if(cartItem.getQuantity()==5){

                    return false;

                }

                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItemList.set(index,cartItem);

                mutableCart.setValue(cartItemList);
                caculateCartTotal();

                return true;
            }

        }

        CartItem cartItem = new CartItem(product,1);

        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        caculateCartTotal();
        return true;


    }

    public void removeItemFromCart(CartItem cartItem){

        if(mutableCart.getValue()==null){

            return;

        }

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        cartItemList.remove(cartItem);

        mutableCart.setValue(cartItemList);

        caculateCartTotal();

    }

    public void changeQuantily(CartItem cartItem, int quantily){

        if(mutableCart.getValue()==null) return;

        List<CartItem>cartItemList= new ArrayList<>(mutableCart.getValue());

        CartItem updatedItem= new CartItem(cartItem.getProduct(), quantily);

        cartItemList.set(cartItemList.indexOf(cartItem),updatedItem);

        mutableCart.setValue(cartItemList);

        caculateCartTotal();

    }

    private void caculateCartTotal(){

        if (mutableCart.getValue()== null)return;
        double total = 0.0;
        List<CartItem>cartItemList=mutableCart.getValue();
        for (CartItem cartItem:cartItemList){

            total += cartItem.getProduct().getPrice_a()*cartItem.getQuantity();

        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Double>getTotalPrice(){

        if (mutableTotalPrice.getValue()==null){

            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;

    }

}
