package com.example.shopingprojecta.reporsitories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;


    public LiveData<List<Product>> getProducts(){

        if (mutableProductList==null){

            mutableProductList = new MutableLiveData<>();
            loadProducts();

        }

        return mutableProductList;

    }

    private void loadProducts() {

        List<Product> productList = new ArrayList<>();

        productList.add(new Product(UUID.randomUUID().toString(),"Ech",2.1,true,R.drawable.item));
        productList.add(new Product(UUID.randomUUID().toString(),"mon2",3.1,true,R.drawable.item1));
        productList.add(new Product(UUID.randomUUID().toString(),"mon3",4.1,true,R.drawable.item2));
        productList.add(new Product(UUID.randomUUID().toString(),"mon4",5.1,true,R.drawable.item3));
        productList.add(new Product(UUID.randomUUID().toString(),"mon5",6.1,true,R.drawable.item4));
        productList.add(new Product(UUID.randomUUID().toString(),"EchA",2.5,true,R.drawable.item));
        productList.add(new Product(UUID.randomUUID().toString(),"mon2a",3.5,true,R.drawable.item1));
        productList.add(new Product(UUID.randomUUID().toString(),"mpn3a",4.5,true,R.drawable.item2));

        mutableProductList.setValue(productList);

    }

}
