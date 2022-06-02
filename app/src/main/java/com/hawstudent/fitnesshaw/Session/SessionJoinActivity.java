package com.hawstudent.fitnesshaw.Session;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hawstudent.fitnesshaw.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SessionJoinActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marvin_session_list);

        EditText myTextBox = (EditText) findViewById(R.id.txt_sessionID);

        TextView name = (TextView) findViewById(R.id.txt_nameID);
        TextView saetze = (TextView) findViewById(R.id.txt_saetzeID);
        TextView wiederholung = (TextView) findViewById(R.id.txt_wiederholungID);
        TextView gewicht = (TextView) findViewById(R.id.txt_gewichtID);

        myTextBox.addTextChangedListener(new TextWatcher() {



            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count)
            {

                //DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("workout").child("123456789");
                //DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("workout").child(String.valueOf(s));
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists() && !(s == null)) {
                            try {
                                String workout_name = snapshot.child("name").getValue().toString();
                                String workout_wiederholung = snapshot.child("wiederholung").getValue().toString();
                                String workout_saetze = snapshot.child("saetze").getValue().toString();
                                String workout_gewicht = snapshot.child("gewicht").getValue().toString();

                                name.setText("Name: " + workout_name);
                                saetze.setText("Sätze: " + workout_saetze);
                                wiederholung.setText("Wiederholung: " + workout_wiederholung);
                                gewicht.setText("Gewicht: " + workout_gewicht);
                            }catch (NullPointerException nullPointerException)
                            {
                                Toast.makeText(SessionJoinActivity.this, "No Session found!",
                                        Toast.LENGTH_LONG).show();
                            }



                            //TODO: layout kaputt durch darkmode
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}

