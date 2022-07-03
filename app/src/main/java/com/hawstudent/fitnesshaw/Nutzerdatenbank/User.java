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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hawstudent.fitnesshaw.DashboardActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;
import backend.Uebung;

public class User {
    FirebaseFirestore fStore;
    public String firstName, lastName, age, email;
    private List<TrainingsplanUebungCrossRef> uebungen;
    public static TrainingsplanViewModel trainingsplanViewModel = null;

    public User() {

    }

    public User(String firstname, String lastName, String age, String email, List<TrainingsplanUebungCrossRef> uebungen) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
        this.uebungen = uebungen;
    }

    public static void setTrainingsplanViewModel(TrainingsplanViewModel viewModel){
            trainingsplanViewModel = viewModel;
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

    public static void updateUserData(List<TrainingsplanUebungCrossRef> uebungen, String traingsplanName) {
        //updateTrainingsplaene(uebungen,traingsplanName);
        updateCrossref(uebungen);
//        updateUebungen(uebungen);

    }

    public static void loadFromFirebase(){
//        loadUebungenFromFirebase();
        loadTrainingsplaeneFromFirebase();
        loadCrossRefFromFirebase();
    }

    private static void loadUebungenFromFirebase(){

        trainingsplanViewModel.deleteAllUebungen();
                String userID = gibFB().gibfAuth().getCurrentUser().getUid();

                gibFB().gibfStore().collection("users").document(userID)
                        .collection("uebungen").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments())
                        {
                            trainingsplanViewModel.insertUebung(documentSnapshot.toObject(Uebung.class));
                        }

                    }
                });
    }

    private static void loadTrainingsplaeneFromFirebase(){
        trainingsplanViewModel.deleteAllTrainingsplaene();
        String userID = gibFB().gibfAuth().getCurrentUser().getUid();

        gibFB().gibfStore().collection("users").document(userID)
                .collection("trainingsplaene").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments())
                {
                    trainingsplanViewModel.insertTrainingsplan(documentSnapshot.toObject(Trainingsplan.class));
                }

            }
        });
    }

    private static void loadCrossRefFromFirebase(){
        trainingsplanViewModel.deleteAllCrossRefs();
        String userID = gibFB().gibfAuth().getCurrentUser().getUid();

        gibFB().gibfStore().collection("users").document(userID)
                .collection("crossRefs").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments())
                {
                    trainingsplanViewModel.insertCrossRef(documentSnapshot.toObject(TrainingsplanUebungCrossRef.class));
                }

            }
        });
    }



    private static void updateTrainingsplaene(List<TrainingsplanUebungCrossRef> uebungen, String trainingsplanName){

        String userID = gibFB().gibfAuth().getCurrentUser().getUid();
        Map<String, Object> triningsplan = new HashMap<>();
        triningsplan.put("trainingsplanId", uebungen.get(0).getTrainingsplanId()+"");
        triningsplan.put("tpName", trainingsplanName);
        DocumentReference messageRef = gibFB().gibfStore()
                .collection("users").document(userID)
                .collection("trainingsplaene").document(uebungen.get(0).getTrainingsplanId() + "");
        messageRef.set(triningsplan);
    }

    public static void deleteTrainingplan(Trainingsplan trainingsplan)
    {
        String userID = gibFB().gibfAuth().getCurrentUser().getUid();
        gibFB().gibfStore().collection("users").document(userID)
                .collection("trainingsplaene").document(trainingsplan.getTrainingsplanId().toString()).delete();
    }

//    public static void deleteCrossRefs(Trainingsplan trainingsplan){
//        String userID = gibFB().gibfAuth().getCurrentUser().getUid();
//        gibFB().gibfStore().collection("users").document(userID)
//                .collection("crossRefs").document().
//    }


    public static void updateTrainingsplaene(List<Trainingsplan> trainingsplaene){
        if(!trainingsplaene.isEmpty()) {
            String userID = gibFB().gibfAuth().getCurrentUser().getUid();
            for (Trainingsplan trainingsplan : trainingsplaene) {
                Map<String, Object> triningsplan = new HashMap<>();
                triningsplan.put("trainingsplanId", trainingsplan.getTrainingsplanId());
                triningsplan.put("tpName", trainingsplan.getTpName());
                DocumentReference messageRef = gibFB().gibfStore()
                        .collection("users").document(userID)
                        .collection("trainingsplaene").document(trainingsplan.getTrainingsplanId() + "");
                messageRef.set(triningsplan);
            }
        }
    }

    public static void updateCrossref(List<TrainingsplanUebungCrossRef> uebungen){
        if(!uebungen.isEmpty()) {
            String userID = gibFB().gibfAuth().getCurrentUser().getUid();

            for(TrainingsplanUebungCrossRef uebung : uebungen) {
                Map<String, Object> crossRefMap = new HashMap<>();
                crossRefMap.put("uebungName", uebung.getUebungName());
                crossRefMap.put("uebungId", uebung.getUebungId());
                crossRefMap.put("trainingsplanId", uebung.getTrainingsplanId());
                crossRefMap.put("gewicht", uebung.getGewicht());
                crossRefMap.put("anzahlWdh", uebung.getAnzahlWdh());
                crossRefMap.put("anzahlSaetze", uebung.getAnzahlSaetze());

                DocumentReference messageRef = gibFB().gibfStore()
                        .collection("users").document(userID)
                        .collection("crossRefs").document(uebung.getTrainingsplanId()+  " : " + uebung.getUebungName() + "");

                messageRef.set(crossRefMap);
            }
        }
    }
    public static void updateUebungen(List<Uebung> uebungen){
        String userID = gibFB().gibfAuth().getCurrentUser().getUid();
        for(Uebung uebung : uebungen) {
            Map<String, Object> uebungenMap = new HashMap<>();
            uebungenMap.put("uebungName", uebung.getUebungName());
            uebungenMap.put("uebungId", uebung.getUebungId());

            DocumentReference messageRef = gibFB().gibfStore()
                    .collection("users").document(userID)
                    .collection("uebungen").document(uebung.getUebungId() + "");

            messageRef.set(uebungenMap);
        }
    }

    public static boolean documentExists() {
        String userID = gibFB().gibfAuth().getCurrentUser().getUid();
        final Boolean[] documentExissts = new Boolean[1];
        // Update an existing document
        DocumentReference docRef = gibFB().gibfStore().collection("users").document(userID);

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
        if (documentExissts[0] == null) {
            return false;
        }
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
    private static FirebaseNutzerDbAnbindung gibFB() {
        return new FirebaseNutzerDbAnbindung();
    }
}

