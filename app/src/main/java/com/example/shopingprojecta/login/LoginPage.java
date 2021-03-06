package com.example.shopingprojecta.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.ActivityLoginPageBinding;
import com.example.shopingprojecta.view.CheckinPage;
import com.example.shopingprojecta.view.FunctionPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.sql.Time;

public class LoginPage extends AppCompatActivity {

    ActivityLoginPageBinding loginPageBinding;
    FirebaseAuth myLGAuthencation;

    int time1=0;

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

            if (loginPageBinding.edtTextPersonName.getText().toString().isEmpty()||
                loginPageBinding.edtTextPassword.getText().toString().isEmpty()){

                ErrorToast();

            }else {
                time1=0;
                SignIN();
            }

        });


    }

/////////////////////////////////////////////////////////////////
    //                   Son programming
/////////////////////////////////////////////////////////////////

    private void SignIN(){

        String email = loginPageBinding.edtTextPersonName.getText().toString();
        String password = loginPageBinding.edtTextPassword.getText().toString();


        myLGAuthencation.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Intent intentFunctionPage = new Intent(LoginPage.this, FunctionPage.class);
                    startActivity(intentFunctionPage);
                    //userID.getUid();
                    getUidUserLogin();


                }else {

                    Toast.makeText(LoginPage.this,"Sign In Fail\n You can wrong Email or Password",Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private  void getUidUserLogin(){

        FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();

        if (userID != null) {
            String emaail = userID.getEmail();
            for (UserInfo profile : userID.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();
                // UID specific to the provider
                String uid = profile.getUid();
                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();

            }
        }

    }

    private void ErrorToast(){

        switch (time1){

            case 0:
                Toast.makeText(LoginPage.this,"Vui long nhap day du thong tin",Toast.LENGTH_SHORT).show();
                time1++;
                break;
            case 1:
                Toast.makeText(LoginPage.this,"Vui long nhap day du thong tin!!!!",Toast.LENGTH_SHORT).show();
                time1++;
                break;
            case 2:
                Toast.makeText(LoginPage.this,"Gian roi do \n Vui long nhap day du thong tin!!!!",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }

}