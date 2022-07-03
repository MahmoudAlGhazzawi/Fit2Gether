package com.hawstudent.fitnesshaw.Nutzerdatenbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hawstudent.fitnesshaw.DashboardActivity;
import com.hawstudent.fitnesshaw.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;

public class SignUpActivity extends AppCompatActivity {

    TextView txtLogin;
    private Button registerUser;
    private EditText firstName, lastName,eMail, password, password2 ;
    private FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private String userID;
    TrainingsplanViewModel trainingsplanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);

        registerUser = (Button) findViewById(R.id.buttonSignUp2);
        firstName =(EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        eMail =(EditText) findViewById(R.id.emailTxt);
        password =(EditText) findViewById(R.id.passwordTxt);
        password2 =(EditText) findViewById(R.id.password2Txt);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        txtLogin = findViewById(R.id.loginTxt);

        changeStatusBarColor();

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                finish();
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });
    }

    private void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void registerUser(){
        String firstname = firstName.getText().toString().trim();
        String lastname  = lastName.getText().toString().trim();
        String email     = eMail.getText().toString().trim();
        String passWord  = password.getText().toString().trim();
        String passWord2 = password2.getText().toString().trim();

        if(firstname.isEmpty()){
            firstName.setError("Gib dein vor Namen ein");
            firstName.requestFocus();
            return;
        }

        if(lastname.isEmpty()){
            lastName.setError("Gib dein nach Namen ein");
            lastName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            eMail.setError("Mail MUSS");
            eMail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eMail.setError("Mail MUSS Richtig sein mit Domäne und so ");
            eMail.requestFocus();
            return;

        }

        if(passWord.isEmpty()){
            password.setError("Password fehlt ");
            password.requestFocus();
            return;
        }
        if (passWord.length()< 6) {
            password.setError("Password länge muss 6 sein");
            password.requestFocus();
            return;
        }

        if (!passWord.equals(passWord2)) {
            password.setError("Passwörter stimmen nicht überein ");
            password.requestFocus();
            return;
        }


        fAuth.createUserWithEmailAndPassword(email,passWord).addOnCompleteListener( task -> {

            if (task.isSuccessful()) {
                setData(Arrays.asList(firstname, lastname,email));
                fAuth.signInWithEmailAndPassword(email, passWord);
                User.setTrainingsplanViewModel(trainingsplanViewModel);
                User.loadFromFirebase();
                Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(SignUpActivity.this, "Error hat net geklappt", Toast.LENGTH_LONG).show();
            }
        });



    }

    private void setData(List<String> userDaten) {
        User.setUserData(userDaten);

    }



}
