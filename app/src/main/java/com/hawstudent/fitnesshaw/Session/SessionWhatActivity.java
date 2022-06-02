package com.hawstudent.fitnesshaw.Session;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.hawstudent.fitnesshaw.R;

public class SessionWhatActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marvin_session_what);


        Button btnC = (Button) findViewById(R.id.btn_create);
        Button btnJ = (Button) findViewById(R.id.btn_join);

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SessionWhatActivity.this, com.hawstudent.fitnesshaw.Session.SessionCreateActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        btnJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SessionWhatActivity.this, SessionJoinActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });


    }
}
