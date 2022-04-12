package com.example.shopingprojecta.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.FragmentOtherBinding;
import com.example.shopingprojecta.viewmodels.ShopViewModel;


public class OtherFragment extends Fragment {

    FragmentOtherBinding fragmentOtherBinding;
    NavController navController;
    ShopViewModel shopViewModel;

    public OtherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_other, container, false);

        fragmentOtherBinding = FragmentOtherBinding.inflate(inflater,container,false);
        return fragmentOtherBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);


        fragmentOtherBinding.continueShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.resetCart();
                navController.navigate(R.id.action_otherFragment_to_shopFragment);
            }
        });


    }
}