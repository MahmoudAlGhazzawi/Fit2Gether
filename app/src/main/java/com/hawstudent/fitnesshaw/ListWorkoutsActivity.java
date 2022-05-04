package com.hawstudent.fitnesshaw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanAdapter;
import backend.TrainingsplanViewModel;
import backend.Uebung;

public class ListWorkoutsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ImageView addWorkout;

    private ListView listView;

    private TrainingsplanViewModel trainingsplanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts);

        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);



        RecyclerView recyclerView = findViewById(R.id.recyclerviewTrainingsPlaene);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TrainingsplanAdapter adapter = new TrainingsplanAdapter();
        recyclerView.setAdapter(adapter);



        ArrayList<TrainingsItems> trainingsItems = new ArrayList<>();
        trainingsItems.add(new TrainingsItems(R.drawable.deadlift, "Line 1", "Line 2"));
        trainingsItems.add(new TrainingsItems(R.drawable.deadlift, "Line 1", "Line 2"));
        trainingsItems.add(new TrainingsItems(R.drawable.deadlift, "Line 1", "Line 2"));


        trainingsplanViewModel.getAllTrainingsplaene().observe(this, new Observer<List<Trainingsplan>>() {
            @Override
            public void onChanged(List<Trainingsplan> trainingsplans) {
                adapter.setTrainingsplaene(trainingsplans);
            }
        });



        addWorkout = findViewById(R.id.addWorkoutImage);

        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListWorkoutsActivity.this, AddTrainingsplanActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

    }
}