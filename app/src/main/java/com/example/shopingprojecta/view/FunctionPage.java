package com.example.shopingprojecta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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

        animationQS();
        Press();

    }

    /////////////////////////////////////////////////////
        //       son programing
    ////////////////////////////////////////////////////

    private void animationQS() {

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.apha_animation);
        functionPageBinding.textQuestion.startAnimation(animation);

    }
    private void Press(){

        functionPageBinding.cardViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentMap = new Intent(FunctionPage.this,MapsActivityPage.class);
                startActivity(intentMap);

            }
        });
        functionPageBinding.cartFunctionEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEat = new Intent(FunctionPage.this,CheckinPage.class);
                startActivity(intentEat);
            }
        });

    }

}