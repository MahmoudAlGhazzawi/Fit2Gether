package com.hawstudent.fitnesshaw.Session;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hawstudent.fitnesshaw.R;

public class SessionWhatActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marvin_session_what);


        Button btnC = (Button) findViewById(R.id.btn_create);
        Button btnJ = (Button) findViewById(R.id.btn_join);

        TextInputEditText sessionInputText = (TextInputEditText) findViewById(R.id.joinSessionInput);

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
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("workout");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sessionId = sessionInputText.getText().toString();
                        if(snapshot.hasChild(sessionId) && !(sessionId.isEmpty())){
                            Intent intent = new Intent(SessionWhatActivity.this, SessionActivity_ShowJoined.class);
                            intent.putExtra("EXTRA_SESSIONID", sessionId);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                        }
                        else
                        {
                            Toast.makeText(SessionWhatActivity.this, "Session existiert nicht!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
//                Intent intent = new Intent(SessionWhatActivity.this, SessionJoinActivity.class);
//                startActivity(intent);
//                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });


    }
}
