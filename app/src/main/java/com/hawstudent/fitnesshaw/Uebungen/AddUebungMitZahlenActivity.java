package com.hawstudent.fitnesshaw.Uebungen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.hawstudent.fitnesshaw.R;
import com.hawstudent.fitnesshaw.Trainingsplaene.AddTrainingsplanActivity;
import com.hawstudent.fitnesshaw.Trainingsplaene.ListWorkoutsActivity;

import java.util.ArrayList;
import java.util.Arrays;

import backend.Trainingsplan;
import backend.TrainingsplanViewModel;
import backend.Uebung;

public class AddUebungMitZahlenActivity extends AppCompatActivity {
    private TextView uebungName;
    private Button submitButton;
    private EditText gewicht;
    private EditText wdh;
    private EditText saetze;

    private TrainingsplanViewModel trainingsplanViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_uebung_zahlen);
        uebungName = findViewById(R.id.uebungName);
        submitButton = findViewById(R.id.submitUebung);
        gewicht = findViewById(R.id.editTextGewicht);
        wdh = findViewById(R.id.editTextWiederholungen);
        saetze = findViewById(R.id.editTextSaetze);

        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);


        Uebung uebung =(Uebung) getIntent().getSerializableExtra("EXTRA_UEBUNG");
        Trainingsplan trainingsplan = (Trainingsplan) getIntent().getSerializableExtra("EXTRA_TRAININGSPLAN");
        uebungName.setText(uebung.getUebungName());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextEmpty(gewicht,wdh,saetze))
                {
                    Toast.makeText(AddUebungMitZahlenActivity.this,"Bitte alle Felder ausfüllen!", Toast.LENGTH_LONG).show();
                }
                else {

                    int gewichtInt = Integer.parseInt(gewicht.getText().toString());
                    int wdhInt = Integer.parseInt(wdh.getText().toString());
                    int saetzeInt = Integer.parseInt(saetze.getText().toString());
                    trainingsplanViewModel.insertUebungenIntoTrainingplan(trainingsplan, Arrays.asList(uebung));
                    trainingsplanViewModel.setGewichtWdhSaetze(trainingsplan, uebung, gewichtInt, wdhInt, saetzeInt);
                    finish();
                }
                }

        });
    }

    public boolean editTextEmpty(EditText gewicht,EditText wdh, EditText saetze)
    {
        if(gewicht.getText().toString().isEmpty())
        {
            return true;
        }

        else if(wdh.getText().toString().isEmpty())
        {
            return true;
        }
        else if(saetze.getText().toString().isEmpty())
        {
            return true;
        }
        return false;
    }

}
