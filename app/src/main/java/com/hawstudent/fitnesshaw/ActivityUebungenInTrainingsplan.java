package com.hawstudent.fitnesshaw;

import static com.hawstudent.fitnesshaw.ListWorkoutsActivity.staticTrainingsplan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import backend.CrossRefAdapter;
import backend.Trainingsplan;
import backend.TrainingsplanAdapter;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;

public class ActivityUebungenInTrainingsplan extends AppCompatActivity {

    private static final String TAG = "UebungenInTrainingsplan";
    private RecyclerView recyclerView;

    private TextView trainingsplanName;

    private TrainingsplanViewModel trainingsplanViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebungen_in_trainingsplan);

        Trainingsplan trainingsplan =(Trainingsplan) getIntent().getSerializableExtra("EXTRA_TRAININGSPLAN");
        Log.d(TAG, "onCreate: " + trainingsplan.getTrainingsplanId());

        trainingsplanName = findViewById(R.id.textMyTrainingsplanUeberschrift);
        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);
        recyclerView = findViewById(R.id.recyclerviewUebungen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        trainingsplanName.setText(trainingsplan.getTpName());


        CrossRefAdapter adapter = new CrossRefAdapter(trainingsplan);
        recyclerView.setAdapter(adapter);


        trainingsplanViewModel.getAllUebungenByTrainingsplan(trainingsplan).observe(this, new Observer<List<TrainingsplanUebungCrossRef>>() {
            @Override
            public void onChanged(List<TrainingsplanUebungCrossRef> uebungen) {
                adapter.setUebungen(uebungen);
            }
        });

    }
}
