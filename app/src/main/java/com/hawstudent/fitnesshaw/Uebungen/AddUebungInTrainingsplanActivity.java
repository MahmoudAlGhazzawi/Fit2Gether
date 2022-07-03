package com.hawstudent.fitnesshaw.Uebungen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hawstudent.fitnesshaw.Nutzerdatenbank.User;
import com.hawstudent.fitnesshaw.R;
import com.hawstudent.fitnesshaw.Trainingsplaene.AddTrainingsplanActivity;
import com.hawstudent.fitnesshaw.Trainingsplaene.ListWorkoutsActivity;

import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;
import backend.Uebung;

public class AddUebungInTrainingsplanActivity extends AppCompatActivity implements UebungenAdapter.OnUebungListener{

    private TextView trainingsplanName;
    private Button cancelButton;
    private Trainingsplan trainingsplan;

    private TrainingsplanViewModel trainingsplanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((savedInstanceState));
        setContentView(R.layout.activity_add_uebung);
        trainingsplanName = findViewById(R.id.text_uebung_traininsplan);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewTrainingsPlaene);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        trainingsplan =(Trainingsplan) getIntent().getSerializableExtra("EXTRA_TRAININGSPLAN");



        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);

        UebungenAdapter adapter = new UebungenAdapter(this);
        recyclerView.setAdapter(adapter);

        trainingsplanName.setText("Übung zu " + trainingsplan.getTpName() + " hinzufügen");

        trainingsplanViewModel.getAllUebungen().observe(this, new Observer<List<Uebung>>() {
            @Override
            public void onChanged(List<Uebung> uebungen) {
                adapter.setUebungen(uebungen);
            }
        });;

    }

    @Override
    public void onUebungClick(Uebung uebung) {
        Intent intent = new Intent(AddUebungInTrainingsplanActivity.this, AddUebungMitZahlenActivity.class);
        intent.putExtra("EXTRA_UEBUNG", uebung);
        intent.putExtra("EXTRA_TRAININGSPLAN", trainingsplan);
        startActivity(intent);
        finish();
    }
}
