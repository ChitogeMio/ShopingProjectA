package com.example.shopingprojecta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.ActivityFunctionPageBinding;

public class FunctionPage extends AppCompatActivity {

    ActivityFunctionPageBinding functionPageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        functionPageBinding= ActivityFunctionPageBinding.inflate(getLayoutInflater());
        setContentView(functionPageBinding.getRoot());
        //setContentView(R.layout.activity_function_page);

        functionPageBinding.cardViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //// chua chinh sua trang
                Intent intent = new Intent(FunctionPage.this,MapsActivityPage.class);
                startActivity(intent);

            }
        });

    }
}