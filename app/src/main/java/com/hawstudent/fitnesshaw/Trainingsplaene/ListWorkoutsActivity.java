package com.hawstudent.fitnesshaw.Trainingsplaene;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hawstudent.fitnesshaw.Nutzerdatenbank.User;
import com.hawstudent.fitnesshaw.Uebungen.ActivityUebungenInTrainingsplan;
import com.hawstudent.fitnesshaw.R;
import com.hawstudent.fitnesshaw.TrainingsItems;

import java.util.ArrayList;
import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;
import backend.Uebung;

public class ListWorkoutsActivity extends AppCompatActivity implements TrainingsplanAdapter.OnTrainingsplanListener{

    private static final String TAG = "ListWorkoutsActivity";
    private RecyclerView recyclerView;

    public static Trainingsplan staticTrainingsplan;

    private ImageView addWorkout;

    private TrainingsplanViewModel trainingsplanViewModel;

    private LiveData<List<Trainingsplan>> listTrainigplan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workouts);

        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);

        listTrainigplan = trainingsplanViewModel.getAllTrainingsplaene();


        recyclerView = findViewById(R.id.recyclerviewTrainingsPlaene);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TrainingsplanAdapter adapter = new TrainingsplanAdapter(this);
        recyclerView.setAdapter(adapter);


//        ArrayList<TrainingsItems> trainingsItems = new ArrayList<>();
//        trainingsItems.add(new TrainingsItems(R.drawable.deadlift, "Line 1", "Line 2"));
//        trainingsItems.add(new TrainingsItems(R.drawable.deadlift, "Line 1", "Line 2"));
//        trainingsItems.add(new TrainingsItems(R.drawable.deadlift, "Line 1", "Line 2"));


        trainingsplanViewModel.getAllTrainingsplaene().observe(this, new Observer<List<Trainingsplan>>() {
            @Override
            public void onChanged(List<Trainingsplan> trainingsplans) {
                adapter.setTrainingsplaene(trainingsplans);
                User.updateTrainingsplaene(trainingsplans);
            }
        });



        addWorkout = findViewById(R.id.addWorkoutImage);

        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListWorkoutsActivity.this, AddTrainingsplanActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListWorkoutsActivity.this);
                builder.setTitle("Willst du " + adapter.getTraingingsplanAt(viewHolder.getAdapterPosition()).getTpName() + " wirklich löschen?");
                builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Trainingsplan trainingsplan = adapter.getTraingingsplanAt(viewHolder.getAdapterPosition());
                        trainingsplanViewModel.deleteTrainingsplan(trainingsplan);
                        User.deleteTrainingplan(trainingsplan);
                        trainingsplanViewModel.deleteCrossRefByTrainingsplan(trainingsplan);
//                        User.deleteCrossRefs(trainingsplan);
                        Toast.makeText(ListWorkoutsActivity.this,
                                adapter.getTraingingsplanAt(viewHolder.getAdapterPosition()).getTpName() +"  entfernt", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onTrainingsplanClick(Trainingsplan trainingsplan) {
        Log.d(TAG, "onTrainingsplanClick: " + trainingsplan.getTpName());
        Intent intent = new Intent(ListWorkoutsActivity.this, ActivityUebungenInTrainingsplan.class);
        intent.putExtra("EXTRA_TRAININGSPLAN", trainingsplan);
        startActivity(intent);
    }
}