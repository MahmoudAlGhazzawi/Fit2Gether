package com.hawstudent.fitnesshaw.Uebungen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hawstudent.fitnesshaw.Nutzerdatenbank.User;
import com.hawstudent.fitnesshaw.R;

import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;

public class ActivityUebungenInTrainingsplan extends AppCompatActivity {

    private static final String TAG = "UebungenInTrainingsplan";
    private RecyclerView recyclerView;
    FirebaseFirestore fStore;
    private FirebaseAuth mAuth;

    private TextView trainingsplanName;

    private ImageView addUebung;

    private TrainingsplanViewModel trainingsplanViewModel;

    private TextView weight;
    private TextView sets;
    private TextView repeats;
    private ImageView uebungImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebungen_in_trainingsplan);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Trainingsplan trainingsplan =(Trainingsplan) getIntent().getSerializableExtra("EXTRA_TRAININGSPLAN");
        Log.d(TAG, "onCreate: " + trainingsplan.getTrainingsplanId());

        addUebung = findViewById(R.id.addUebung);
        trainingsplanName = findViewById(R.id.textMyTrainingsplanUeberschrift);
        trainingsplanViewModel = new ViewModelProvider(this).get(TrainingsplanViewModel.class);
        recyclerView = findViewById(R.id.recyclerviewUebungen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        trainingsplanName.setText(trainingsplan.getTpName());



        CrossRefAdapter adapter = new CrossRefAdapter(trainingsplan);
        recyclerView.setAdapter(adapter);

        // TODO: ergänzen um layout trainingsplan_uebungen_item zu benutzen

        /*
        weight = findViewById(R.id.kgText);
        sets = findViewById(R.id.setsText);
        repeats = findViewById(R.id.repeatsText);
        uebungImage = findViewById(R.id.uebungImageTrainingsplan);



        if (trainingsplanName.getText().toString().contains("Pullups")) {
            uebungImage.setImageResource(R.drawable.pullup_img);
        } else if (trainingsplanName.getText().toString().contains("Rudern")) {
            uebungImage.setImageResource(R.drawable.rudern_img);
        }else if (trainingsplanName.getText().toString().contains("Cable Flies")) {
            uebungImage.setImageResource(R.drawable.cableflies_img);
        }else if (trainingsplanName.getText().toString().contains("Bankdruecken")) {
            uebungImage.setImageResource(R.drawable.bankdruecken_img);
        }else if (trainingsplanName.getText().toString().contains("Pushups")) {
            uebungImage.setImageResource(R.drawable.pushup_img);
        }else if (trainingsplanName.getText().toString().contains("Dips")) {
            uebungImage.setImageResource(R.drawable.dips_img);
        }else if (trainingsplanName.getText().toString().contains("Squads")) {
            uebungImage.setImageResource(R.drawable.squads_img);
        }else if (trainingsplanName.getText().toString().contains("LegPress")) {
            uebungImage.setImageResource(R.drawable.legpress_img);
        }else if (trainingsplanName.getText().toString().contains("Calf Raises")) {
            uebungImage.setImageResource(R.drawable.calfraises_img);
        }
        */



        trainingsplanViewModel.getAllUebungenByTrainingsplan(trainingsplan).observe(this, new Observer<List<TrainingsplanUebungCrossRef>>() {
            @Override
            public void onChanged(List<TrainingsplanUebungCrossRef> uebungen) {
                adapter.setUebungen(uebungen);

//                User.updateUserData(uebungen,trainingsplan.getTpName());
                User.updateCrossref(uebungen);
            }
        });

        addUebung.setOnClickListener( new View.OnClickListener() {
                @Override
        public void onClick(View view) {
            Intent intent = new Intent(ActivityUebungenInTrainingsplan.this, AddUebungInTrainingsplanActivity.class);
            intent.putExtra("EXTRA_TRAININGSPLAN", trainingsplan);
            startActivity(intent);
//            overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
//            finish();
        }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityUebungenInTrainingsplan.this);
                builder.setTitle("Willst du " + adapter.getCrossRefAt(viewHolder.getAdapterPosition()).getUebungName() + " wirklich löschen?");
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
                        trainingsplanViewModel.deleteCrossRef(adapter.getCrossRefAt(viewHolder.getAdapterPosition()));
                        Toast.makeText(ActivityUebungenInTrainingsplan.this,
                                adapter.getCrossRefAt(viewHolder.getAdapterPosition()).getUebungName() +"  entfernt", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        }).attachToRecyclerView(recyclerView);


    }
}
