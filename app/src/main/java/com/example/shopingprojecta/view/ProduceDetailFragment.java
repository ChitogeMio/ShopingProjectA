package com.example.shopingprojecta.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.FragmentProduceDetailBinding;
import com.example.shopingprojecta.viewmodels.ShopViewModel;


public class ProduceDetailFragment extends Fragment {

    FragmentProduceDetailBinding fragmentProduceDetailBinding;
    ShopViewModel shopViewModel;


    public ProduceDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProduceDetailBinding = FragmentProduceDetailBinding.inflate(inflater,container,false);


        return fragmentProduceDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProduceDetailBinding.setShopViewModel(shopViewModel);

    }
}