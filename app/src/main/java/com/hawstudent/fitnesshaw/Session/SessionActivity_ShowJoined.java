package com.hawstudent.fitnesshaw.Session;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hawstudent.fitnesshaw.R;
import com.hawstudent.fitnesshaw.Uebungen.CrossRefAdapter;

import java.util.ArrayList;
import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;

public class SessionActivity_ShowJoined extends AppCompatActivity {
    private RecyclerView recyclerView;

    private TextView workoutName;
    private Button sessionIdButton;
    DatabaseReference reference;
    List<TrainingsplanUebungCrossRef> uebungen = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_session_joined);

        recyclerView = findViewById(R.id.recyclerviewUebungen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        CrossRefAdapter adapter = new CrossRefAdapter(new Trainingsplan("Test"));
        recyclerView.setAdapter(adapter);

        String sessionId = (String) getIntent().getSerializableExtra("EXTRA_SESSIONID");
        reference = FirebaseDatabase.getInstance().getReference().child("workout").child(sessionId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                        uebungen.add(snapshot1.getValue(TrainingsplanUebungCrossRef.class));
                        System.out.println("------------------" + snapshot1.getValue(TrainingsplanUebungCrossRef.class).getUebungName());
                    }
                    adapter.setUebungen(uebungen);
                }
                else
                {
                    Toast.makeText(SessionActivity_ShowJoined.this, "Session wurde beendet!",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        workoutName = findViewById(R.id.textMyTrainingsplanUeberschrift);
        sessionIdButton = findViewById(R.id.CopySessionIdButton);



    }
}
