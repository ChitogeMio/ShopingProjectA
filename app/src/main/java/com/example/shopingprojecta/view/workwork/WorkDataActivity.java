package com.example.shopingprojecta.view.workwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shopingprojecta.R;
import com.example.shopingprojecta.databinding.ActivityWorkDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class WorkDataActivity extends AppCompatActivity {

    ActivityWorkDataBinding workDataBinding;
    private FirebaseFirestore firebaseFirestoreWorkData;
    private FirebaseAuth firebaseAuthWorkData;
    private FirebaseUser userID;
    private String providerId,uid,name,email,emaill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.getSupportActionBar().hide();;
        workDataBinding = ActivityWorkDataBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_work_data);
        //setContentView(R.layout.activity_work_data);


        firebaseFirestoreWorkData = FirebaseFirestore.getInstance();

        getUI();

        workDataBinding.buttondownInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseFirestoreWorkData.collection(emaill)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Map data2 = document.getData();
                                        String name1 = (String) data2.get("Work");
                                    }

                                }else {
                                    Toast.makeText(WorkDataActivity.this,"Error Update data",Toast.LENGTH_SHORT).show();
                                }
                            }

                        });

            }
        });

    }

    private void getUI (){

        userID = firebaseAuthWorkData.getInstance().getCurrentUser();

        if (userID != null) {
            for (UserInfo profile : userID.getProviderData()) {
                // Id of the provider (ex: google.com)
                providerId = profile.getProviderId();
                // UID specific to the provider
                uid = profile.getUid();
                // Name, email address, and profile photo Url
                name = profile.getDisplayName();
                email = profile.getEmail();
                emaill = userID.getEmail(); // read email user now

            }
        }

    }
}