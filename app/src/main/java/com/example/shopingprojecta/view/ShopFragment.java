package com.example.shopingprojecta.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.adepter.ShopListAdapter;
import com.example.shopingprojecta.databinding.FragmentShopBinding;
import com.example.shopingprojecta.models.Product;
import com.example.shopingprojecta.viewmodels.ShopViewModel;

import java.util.List;


public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

    FragmentShopBinding fragmentShopBinding;
    private ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    private static final String TAG = "ShopFragment";
    private NavController navController;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_shop, container, false);

        fragmentShopBinding = FragmentShopBinding.inflate(inflater,container,false);
        return fragmentShopBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter(this);
        fragmentShopBinding.rcvShop.setAdapter(shopListAdapter);
        // tao duong phan cach
        fragmentShopBinding.rcvShop.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        //fragmentShopBinding.rcvShop.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {

                shopListAdapter.submitList(products);

            }
        });

        navController = Navigation.findNavController(view);

    }


    @Override
    public void addItem(Product product) {

       // Log.d(TAG, "addItem:"+product.toString());
        boolean isAdded = shopViewModel.addItemtoCart(product);
        Log.d(TAG,"addItem:"+product.getName_a()+isAdded);

    }

    @Override
    public void onItemClick(Product product) {

        //Log.d(TAG,"onItemClick:"+product.toString());
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFragment_to_produceDetailFragment);
    }
}