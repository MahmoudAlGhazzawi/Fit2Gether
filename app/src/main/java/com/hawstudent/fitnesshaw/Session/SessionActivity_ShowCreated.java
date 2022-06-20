package com.hawstudent.fitnesshaw.Session;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hawstudent.fitnesshaw.R;
import com.hawstudent.fitnesshaw.Uebungen.CrossRefAdapter;

import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;

public class SessionActivity_ShowCreated extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TrainingsplanViewModel trainingsplanViewModel;

    private TextView workoutName;
    private Button beendenButton;
    private Button sessionIdButton;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_session);

        Trainingsplan trainingsplan = (Trainingsplan) getIntent().getSerializableExtra("EXTRA_TRAININGSPLAN");
        String sessionId = (String) getIntent().getSerializableExtra("EXTRA_SESSIONID");

        workoutName = findViewById(R.id.textMyTrainingsplanUeberschrift);
        beendenButton = findViewById(R.id.beendenButton);
        sessionIdButton = findViewById(R.id.CopySessionIdButton);

        workoutName.setText(trainingsplan.getTpName());


        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);
        recyclerView = findViewById(R.id.recyclerviewUebungen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        CrossRefAdapter adapter = new CrossRefAdapter(trainingsplan);
        recyclerView.setAdapter(adapter);

        trainingsplanViewModel.getAllUebungenByTrainingsplan(trainingsplan).observe(this, new Observer<List<TrainingsplanUebungCrossRef>>() {
            @Override
            public void onChanged(List<TrainingsplanUebungCrossRef> uebungen) {
                adapter.setUebungen(uebungen);
            }
        });


        beendenButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseConnection firebaseConnection = new FirebaseConnection();
                firebaseConnection.deleteSession(sessionId);
                Toast.makeText(SessionActivity_ShowCreated.this, "Session beendet", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        sessionIdButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("SessionId", sessionId);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(SessionActivity_ShowCreated.this, "SessionID in die Zwischenablage kopiert!", Toast.LENGTH_LONG).show();
            }
        });


    }
}
