package com.example.shopingprojecta.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.ActivityRegistrationPageBinding;
import com.example.shopingprojecta.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPage extends AppCompatActivity {

    private static final String TAG = "RegistrationPage";

    //ActivityRegistrationPageBinding registrationPageBinding;
    ActivityRegistrationPageBinding registrationPageBinding;
    FirebaseAuth myAuthencation;
    DatabaseReference registrationDatabase;

    private User user = new User();

    int time1=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationPageBinding = ActivityRegistrationPageBinding.inflate(getLayoutInflater());
        setContentView(registrationPageBinding.getRoot());

        myAuthencation = FirebaseAuth.getInstance();
        registrationDatabase  = FirebaseDatabase.getInstance().getReference();

        registrationPageBinding.buttonDKUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });

        checkedbox();

    }

/////////////////////////////////////////////////////////////////
   //                   Son programming
/////////////////////////////////////////////////////////////////

    ///// dang tai khoang thong qua firebase///
    private void SignUp(){

        String email = registrationPageBinding.edtDKTextEmail.getText().toString();
        String password = registrationPageBinding.edtDKPassword.getText().toString();
        //String userID;

            if (email.isEmpty()||password.isEmpty()){

                Toast.makeText(RegistrationPage.this," Haven't entered password or account ",Toast.LENGTH_SHORT).show();

            }else {
                myAuthencation.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                           // FirebaseUser userID = myAuthencation.getCurrentUser();
                            updateDatabaseUser();
                            Toast.makeText(RegistrationPage.this," Sign Up Success ",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegistrationPage.this," Sign Up Fail ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
    }

    ////// update du lieu len firebase data////
    private void updateDatabaseUser(){

        FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();
        if (userID != null) {
            // Name, email address, and profile photo Url
            user.setNameEmail(userID.getEmail());
            user.setDisplayNameEmail(userID.getDisplayName());
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            user.setUidEmail(userID.getUid());
        }
        registrationDatabase.child("UserInfomation").push().setValue(user);

    }
    //// kiem tra da du thong tin chua ////
    private void checkedbox(){

        registrationPageBinding.ckboxXacThuc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    getIfomation();
                    if (user.getNameUser().isEmpty()|| user.getDiaChi().isEmpty()|| user.getyOfB().isEmpty()||
                            (!registrationPageBinding.radioButtonFemale.isChecked()&&!registrationPageBinding.radioButtonMale.isChecked())){

                        ErrorToast();
                        registrationPageBinding.buttonDKUser.setEnabled(false);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                registrationPageBinding.ckboxXacThuc.setChecked(false);
                            }
                        },500);

                    }else {

                        registrationPageBinding.buttonDKUser.setEnabled(true);
                        Toast.makeText(RegistrationPage.this,"Da xac nhan",Toast.LENGTH_SHORT).show();
                    }

                }else {

                    registrationPageBinding.buttonDKUser.setEnabled(false);

                }

            }
        });

    }
    ///// nhan thong tin tu cac edittext////
    private void getIfomation(){

        user.setNameUser(registrationPageBinding.edtNameUser.getText().toString());
        user.setDiaChi(registrationPageBinding.edtAddcessUser.getText().toString());
        user.setyOfB(registrationPageBinding.edtYODUser.getText().toString());
        GT();


    }
    ///// mot so loi khi nhap thieu thong tin////
    private void ErrorToast(){

        switch (time1){

            case 0:
                Toast.makeText(RegistrationPage.this,"Vui long nhap day du thong tin",Toast.LENGTH_SHORT).show();
                time1++;
                break;
            case 1:
                Toast.makeText(RegistrationPage.this,"Vui long nhap day du thong tin!!!!",Toast.LENGTH_SHORT).show();
                time1++;
                break;
            case 2:
                Toast.makeText(RegistrationPage.this,"Gian roi do \n Vui long nhap day du thong tin!!!!",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }
    //// GT /////
    private void GT(){

        registrationPageBinding.radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){

                    case R.id.radioButtonFemale:
                        user.setSex("Female");
                        Toast.makeText(RegistrationPage.this,"Female",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonMale:
                        user.setSex("Male");
                        Toast.makeText(RegistrationPage.this,"Male",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });

    }

}