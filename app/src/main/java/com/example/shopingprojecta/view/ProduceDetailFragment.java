package com.example.shopingprojecta.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopingprojecta.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProduceDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProduceDetailFragment extends Fragment {

    public ProduceDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produce_detail, container, false);
    }
}