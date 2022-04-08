package com.example.shopingprojecta.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopingprojecta.models.CartItem;
import com.example.shopingprojecta.models.Product;
import com.example.shopingprojecta.reporsitories.CartRepo;
import com.example.shopingprojecta.reporsitories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();
    CartRepo cartRepo = new CartRepo();

    MutableLiveData<Product>mutableProduct = new MutableLiveData<>();

    public LiveData<List<Product>>getProducts(){

        return shopRepo.getProducts();
    }

    public void setProduct(Product product){

        mutableProduct.setValue(product);

    }

    public LiveData<Product>getProduct(){
        return mutableProduct;
    }

    public LiveData<List<CartItem>> getCart(){
        return cartRepo.getCart();
    }

    public boolean addItemtoCart(Product product){
        return cartRepo.addItemtoCart(product);
    }
}
