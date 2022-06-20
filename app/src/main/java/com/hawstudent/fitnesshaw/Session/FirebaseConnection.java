package com.hawstudent.fitnesshaw.Session;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;

public class FirebaseConnection {

    private boolean sessionExists = false;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("workout");


    /*
    Return den Session-Key, der von der DB unique erstellt wurde.
     */
    public String createSession(List<TrainingsplanUebungCrossRef> uebungen) {

        DatabaseReference newReference = reference.push();
        newReference.setValue(uebungen);
        return newReference.getKey();
    }

    public void deleteSession(String sessionId)
    {
        reference.child(sessionId).removeValue();
    }

    public List<TrainingsplanUebungCrossRef> getSessionData(String sessionId)
    {

        return null;
    }





}