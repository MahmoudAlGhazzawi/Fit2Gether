package com.hawstudent.fitnesshaw.Nutzerdatenbank;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hawstudent.fitnesshaw.R;

public class FirebaseNutzerDbAnbindung {
    private static FirebaseAuth fAuth;
    private static FirebaseFirestore fStore;

    private static void verbinde(){
    fAuth = FirebaseAuth.getInstance();
    fStore = FirebaseFirestore.getInstance();
    }

    public FirebaseAuth gibfAuth () {
        verbinde();
    return fAuth;
}

    public FirebaseFirestore gibfStore () {
        verbinde();
        return fStore;
    }
}