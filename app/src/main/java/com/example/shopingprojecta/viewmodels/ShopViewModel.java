package com.example.shopingprojecta.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopingprojecta.models.Product;
import com.example.shopingprojecta.reporsitories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo = new ShopRepo();

    public LiveData<List<Product>>getProduct(){

        return shopRepo.getProducts();
    }

}
