package backend;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.hawstudent.fitnesshaw.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivityBackend extends AppCompatActivity {
    private TrainingsplanViewModel trainingsplanViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);



        Trainingsplan pullDay = new Trainingsplan("PullDay");
        trainingsplanViewModel.insertTrainingsplan(pullDay);
//        trainingsplanViewModel.insertTrainingsplan(new Trainingsplan("PushDay"));
//        trainingsplanViewModel.insertTrainingsplan(new Trainingsplan("LegDay"));
//        trainingsplanViewModel.insertTrainingsplan(new Trainingsplan("Ganzkoerper"));
//
//        Uebung pullups = new Uebung("Pullups");
//        trainingsplanViewModel.insertUebung(pullups);
//        Uebung rudern = new Uebung("Rudern");
//        trainingsplanViewModel.insertUebung(rudern);
//        Uebung cableFlies = new Uebung("Cable Flies");
//        trainingsplanViewModel.insertUebung(cableFlies);
//
//        Uebung bankdruecken = new Uebung("Bankdruecken");
//        trainingsplanViewModel.insertUebung(bankdruecken);
//        Uebung pushups = new Uebung("Pushups");
//        trainingsplanViewModel.insertUebung(pushups);
//        Uebung dips = new Uebung("Dips");
//        trainingsplanViewModel.insertUebung(dips);
//
//        Uebung squads = new Uebung("Squads");
//        trainingsplanViewModel.insertUebung(squads);
//        Uebung legPress = new Uebung("LegPress");
//        trainingsplanViewModel.insertUebung(legPress);
//        Uebung calfRaises = new Uebung("Calf Raises");
//        trainingsplanViewModel.insertUebung(calfRaises);
//
//        List<Uebung> list = Arrays.asList(pullups,rudern,cableFlies);
//        trainingsplanViewModel.insertUebungenIntoTrainingplan(pullDay,list);



        setContentView(R.layout.leon_activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TrainingsplanAdapter adapter = new TrainingsplanAdapter();
        recyclerView.setAdapter(adapter);

        trainingsplanViewModel.getAllTrainingsplaene().observe(this, new Observer<List<Trainingsplan>>() {
            @Override
            public void onChanged(List<Trainingsplan> trainingsplans) {
                adapter.setTrainingsplaene(trainingsplans);
            }
        });

    }
}
