package com.hawstudent.fitnesshaw.Session;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hawstudent.fitnesshaw.R;

public class SessionCreateActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marvin_session_create);

        //TODO: Ansicht wie: "My Workouts" ohne plus unten
        // + Auf workout drauf klicken sieht man auch die übungen
        // + ohne plus unten, da eher commit oder so um das workout für die session freizugeben
        // + get workout -> übungen - pro übung: name/sätze/wiederholungen/gewicht in die DB firebase
        // + andere können über generierte ID Session ansehen und das workout trainiren
        // + übersicht von allen selbst geöffneten sessions -> option zu schließen und ansicht der ID




    }
}
