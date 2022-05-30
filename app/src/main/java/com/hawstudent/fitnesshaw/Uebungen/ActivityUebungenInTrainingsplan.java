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

import com.hawstudent.fitnesshaw.R;

import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;
import backend.TrainingsplanViewModel;

public class ActivityUebungenInTrainingsplan extends AppCompatActivity {

    private static final String TAG = "UebungenInTrainingsplan";
    private RecyclerView recyclerView;

    private TextView trainingsplanName;

    private ImageView addUebung;

    private TrainingsplanViewModel trainingsplanViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebungen_in_trainingsplan);

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


        trainingsplanViewModel.getAllUebungenByTrainingsplan(trainingsplan).observe(this, new Observer<List<TrainingsplanUebungCrossRef>>() {
            @Override
            public void onChanged(List<TrainingsplanUebungCrossRef> uebungen) {
                adapter.setUebungen(uebungen);
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
