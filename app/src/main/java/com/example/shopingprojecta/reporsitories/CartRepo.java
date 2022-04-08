package com.example.shopingprojecta.reporsitories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopingprojecta.models.CartItem;
import com.example.shopingprojecta.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {

    private MutableLiveData<List<CartItem>>mutableCart = new MutableLiveData<>();

    public LiveData<List<CartItem>>getCart(){
        if(mutableCart.getValue()==null){
            initCart();
        }
        return mutableCart;
    }

    public void initCart(){
        mutableCart.setValue(new ArrayList<CartItem>());
    }

    public boolean addItemtoCart(Product product){
        if(mutableCart.getValue()==null){
            initCart();
        }

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        CartItem cartItem = new CartItem(product,1);

        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        return true;


    }

}
