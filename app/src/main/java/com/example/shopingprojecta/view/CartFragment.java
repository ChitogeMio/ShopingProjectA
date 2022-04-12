package com.example.shopingprojecta.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
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

public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater,container,false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        CartListAdapter cartListAdapter= new CartListAdapter(this);

        fragmentCartBinding.cartRCV.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRCV.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));

        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {

                cartListAdapter.submitList(cartItems);

                fragmentCartBinding.buttonBuy.setEnabled(cartItems.size()>0);

            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.orderTotalTextView.setText("Total:($)"+aDouble.toString());
            }
        });

        fragmentCartBinding.buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.action_cartFragment_to_otherFragment);

            }
        });

    }

    @Override
    public void deleteItem(CartItem cartItem) {

        Log.d(TAG, "deleteItem: "+cartItem.getProduct().getName_a());
        shopViewModel.removeItemFromCart(cartItem);

    }

    @Override
    public void changeQuatily(CartItem cartItem, int quantily) {

        shopViewModel.changeQuantily(cartItem,quantily);

    }
}