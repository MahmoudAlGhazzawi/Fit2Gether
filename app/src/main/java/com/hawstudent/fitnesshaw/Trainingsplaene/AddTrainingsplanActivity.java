package com.hawstudent.fitnesshaw.Trainingsplaene;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hawstudent.fitnesshaw.R;

import backend.Trainingsplan;
import backend.TrainingsplanViewModel;

public class AddTrainingsplanActivity extends AppCompatActivity {

    private EditText workoutName;
    private Button submitButton;
    private Button cancelButton;

    private TrainingsplanViewModel trainingsplanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trainingsplan);

        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);

        workoutName = findViewById(R.id.addWorkoutName);
        submitButton = findViewById(R.id.submitWorkout);
        cancelButton = findViewById(R.id.cancelWorkout);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(workoutName.getText().toString().isEmpty()){
                    Toast.makeText(AddTrainingsplanActivity.this, "Please type a name for your workout!", Toast.LENGTH_SHORT).show();
                }
                else{
//                    trainingsplanViewModel.insertTrainingsplan(workoutName.getText().toString());
                    Trainingsplan trainingsplan = new Trainingsplan(workoutName.getText().toString());
                    trainingsplanViewModel.insertTrainingsplan(trainingsplan);


                    Intent intent = new Intent(AddTrainingsplanActivity.this, ListWorkoutsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTrainingsplanActivity.this, ListWorkoutsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
                finish();
            }
        });
    }

}