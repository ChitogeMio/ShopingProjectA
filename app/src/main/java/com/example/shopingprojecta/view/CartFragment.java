package com.example.shopingprojecta.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.adepter.CartListAdapter;
import com.example.shopingprojecta.databinding.FragmentCartBinding;
import com.example.shopingprojecta.databinding.FragmentProduceDetailBinding;
import com.example.shopingprojecta.models.CartItem;
import com.example.shopingprojecta.viewmodels.ShopViewModel;

import java.util.List;

public class CartFragment extends Fragment {

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FragmentCartBinding fragmentCartBinding;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CartListAdapter cartListAdapter= new CartListAdapter();

        fragmentCartBinding.cartRCV.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRCV.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));

        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {

                cartListAdapter.submitList(cartItems);

            }
        });

    }
}