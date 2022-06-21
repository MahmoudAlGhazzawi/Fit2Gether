package com.hawstudent.fitnesshaw.Nutzerdatenbank;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hawstudent.fitnesshaw.DashboardActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.TrainingsplanUebungCrossRef;

public class User {
    FirebaseFirestore fStore;
    public String firstName, lastName, age, email;
    private List<TrainingsplanUebungCrossRef> uebungen;

    public User() {

    }

    public User(String firstname, String lastName, String age, String email, List<TrainingsplanUebungCrossRef> uebungen) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
        this.uebungen = uebungen;
    }

    public static void setUserData(List<String> daten) {

        FirebaseNutzerDbAnbindung fb = new FirebaseNutzerDbAnbindung();
        String userID = fb.gibfAuth().getCurrentUser().getUid();

        DocumentReference documentReference = fb.gibfStore().collection("users").document(userID);

        Map<String, Object> user = new HashMap<>();
        List<TrainingsplanUebungCrossRef> uebungen = new ArrayList<>();
        user.put("firstName", daten.get(0));
        user.put("lastName", daten.get(1));
        user.put("email", daten.get(2));
        user.put("uebungen", uebungen);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        });
    }

    public static void updateUserData(List<TrainingsplanUebungCrossRef> uebungen) {
        FirebaseNutzerDbAnbindung fb = new FirebaseNutzerDbAnbindung();
        String userID = fb.gibfAuth().getCurrentUser().getUid();
        // Update an existing document
        DocumentReference docRef = fb.gibfStore().collection("users").document(userID);
        docRef.update("uebungen", uebungen);

    }

    public static boolean documentExists() {
        FirebaseNutzerDbAnbindung fb = new FirebaseNutzerDbAnbindung();
        String userID = fb.gibfAuth().getCurrentUser().getUid();
        final Boolean[] documentExissts = new Boolean[1];
        // Update an existing document
        DocumentReference docRef = fb.gibfStore().collection("users").document(userID);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        documentExissts[0] = true;
                    } else {
                        documentExissts[0] = false;
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        return documentExissts[0];
    }


    public static void getUserData(List<TrainingsplanUebungCrossRef> uebungen) {
        FirebaseNutzerDbAnbindung fb = new FirebaseNutzerDbAnbindung();
        String userID = fb.gibfAuth().getCurrentUser().getUid();
        // Update an existing document
        DocumentReference docRef = fb.gibfStore().collection("users").document(userID);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                }
            }
        });
    }
}

