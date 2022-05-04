package com.hawstudent.fitnesshaw;

import static com.hawstudent.fitnesshaw.ListWorkoutsActivity.staticTrainingsplan;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanAdapter;
import backend.TrainingsplanViewModel;

public class ActivityUebungenInTrainingsplan extends AppCompatActivity {

    private RecyclerView recyclerView;

    private TextView trainingsplanName;

    private TrainingsplanViewModel trainingsplanViewModel;

    private LiveData<List<Trainingsplan>> listTrainigplan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebungen_in_trainingsplan);

        trainingsplanName = findViewById(R.id.trainingsPlanUeberschrift);


        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);

        listTrainigplan = trainingsplanViewModel.getAllTrainingsplaene();

        recyclerView = findViewById(R.id.recyclerviewUebungen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //TODO noch bearbeiten (kein UebungenAdapter ist vorhanden)
        TrainingsplanAdapter adapter = new TrainingsplanAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainingsplanViewModel.getAllUebungenByTrainingsplan(staticTrainingsplan);

                trainingsplanName.setText(staticTrainingsplan.getTpName());
            }
        });

    }
}
