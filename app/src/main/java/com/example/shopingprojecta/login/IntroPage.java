package com.example.shopingprojecta.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.view.CheckinPage;

public class IntroPage extends AppCompatActivity {

    TextView txtWell,txtWell2;
    private static int Splash_time=5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_intro_page);
        setContentView(R.layout.activity_intro_page);
        txtWell=findViewById(R.id.txtViewWecom);
        txtWell2=findViewById(R.id.txtWellcom2);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroPage.this,CheckinPage.class);
                startActivity(intent);
                finish();
            }
        },Splash_time);
        Animation myanimtion = AnimationUtils.loadAnimation(IntroPage.this,R.anim.animation_intro2);
        txtWell.startAnimation(myanimtion);
        Animation myanimtion2 = AnimationUtils.loadAnimation(IntroPage.this,R.anim.animation_intro);
        txtWell2.startAnimation(myanimtion2);

    }
}