package com.example.shopingprojecta.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.ActivityLoginPageBinding;
import com.example.shopingprojecta.view.CheckinPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    ActivityLoginPageBinding loginPageBinding;
    FirebaseAuth myLGAuthencation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login_page);
        loginPageBinding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(loginPageBinding.getRoot());

        myLGAuthencation = FirebaseAuth.getInstance();

        loginPageBinding.buttonSignUp.setOnClickListener(v ->{
            Intent intent = new Intent(LoginPage.this,RegistrationPage.class);
            startActivity(intent);

        });

        loginPageBinding.buttonLogin.setOnClickListener(v->{

            SignIN();

        });


    }

    private void SignIN(){

        String email = loginPageBinding.edtTextPersonName.getText().toString();
        String password = loginPageBinding.edtTextPassword.getText().toString();

        myLGAuthencation.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Intent intentCheckPage = new Intent(LoginPage.this, CheckinPage.class);
                    startActivity(intentCheckPage);

                }else {

                    Toast.makeText(LoginPage.this,"Sign In Fail\n You can wrong Email or Password",Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}