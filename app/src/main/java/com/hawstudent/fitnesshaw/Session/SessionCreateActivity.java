package com.hawstudent.fitnesshaw.Session;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hawstudent.fitnesshaw.R;
import com.hawstudent.fitnesshaw.Trainingsplaene.TrainingsplanAdapter;

import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;

public class SessionCreateActivity extends AppCompatActivity implements TrainingsplanAdapter.OnTrainingsplanListener{

    private RecyclerView recyclerView;

    private TrainingsplanViewModel trainingsplanViewModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts);

        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);



        recyclerView = findViewById(R.id.recyclerviewTrainingsPlaene);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TrainingsplanAdapter adapter = new TrainingsplanAdapter(this);
        recyclerView.setAdapter(adapter);

        trainingsplanViewModel.getAllTrainingsplaene().observe(this, new Observer<List<Trainingsplan>>() {
            @Override
            public void onChanged(List<Trainingsplan> trainingsplans) {
                adapter.setTrainingsplaene(trainingsplans);
            }
        });






    }

    @Override
    public void onTrainingsplanClick(Trainingsplan trainingsplan) {
        FirebaseConnection firebaseConnection = new FirebaseConnection();
        trainingsplanViewModel.getAllUebungenByTrainingsplan(trainingsplan).observe(this, new Observer<List<TrainingsplanUebungCrossRef>>() {
            @Override
            public void onChanged(List<TrainingsplanUebungCrossRef> uebungen) {
                String sessionId;
                sessionId = firebaseConnection.createSession(uebungen);

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("SessionId", sessionId);
                clipboard.setPrimaryClip(clip);

                Intent intent = new Intent(SessionCreateActivity.this, SessionActivity_ShowCreated.class);
                intent.putExtra("EXTRA_TRAININGSPLAN", trainingsplan);
                intent.putExtra("EXTRA_SESSIONID", sessionId);
                startActivity(intent);

                Toast.makeText(SessionCreateActivity.this, "Session erstellt und ID in die Zwischenablage kopiert!", Toast.LENGTH_LONG).show();
            }
        });
        finish();
    }
}
