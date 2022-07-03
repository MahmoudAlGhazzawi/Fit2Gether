package com.hawstudent.fitnesshaw.Uebungen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hawstudent.fitnesshaw.R;

import java.util.ArrayList;
import java.util.List;

import backend.Trainingsplan;
import backend.TrainingsplanUebungCrossRef;

public class CrossRefAdapter extends RecyclerView.Adapter<CrossRefAdapter.CrossRefHolder> {

    private List<TrainingsplanUebungCrossRef> uebungen = new ArrayList<>();

    private Trainingsplan trainingsplan;

    public CrossRefAdapter(Trainingsplan trainingsplan)
    {
        this.trainingsplan = trainingsplan;
    }

    @NonNull
    @Override
    public CrossRefHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trainingsplan_uebungen_item, parent, false);
        return new CrossRefHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrossRefHolder holder, int position) {
    TrainingsplanUebungCrossRef currentUebung = uebungen.get(position);
    holder.textUebungName.setText(currentUebung.getUebungName());
    holder.textKg.setText(currentUebung.getGewicht() + "");
    holder.textSets.setText(currentUebung.getAnzahlSaetze() + "");
    holder.textRepeats.setText(currentUebung.getAnzahlWdh() + "");

        if (currentUebung.getUebungName().contains("Pullups")) {
            holder.imageUebung.setImageResource(R.drawable.pullup_img);
        } else if (currentUebung.getUebungName().contains("Rudern")) {
            holder.imageUebung.setImageResource(R.drawable.rudern_img);
        }else if (currentUebung.getUebungName().contains("Cable Flies")) {
            holder.imageUebung.setImageResource(R.drawable.cableflies_img);
        }else if (currentUebung.getUebungName().contains("Bankdruecken")) {
            holder.imageUebung.setImageResource(R.drawable.bankdruecken_img);
        }else if (currentUebung.getUebungName().contains("Pushups")) {
            holder.imageUebung.setImageResource(R.drawable.pushup_img);
        }else if (currentUebung.getUebungName().contains("Dips")) {
            holder.imageUebung.setImageResource(R.drawable.dips_img);
        }else if (currentUebung.getUebungName().contains("Squads")) {
            holder.imageUebung.setImageResource(R.drawable.squads_img);
        }else if (currentUebung.getUebungName().contains("LegPress")) {
            holder.imageUebung.setImageResource(R.drawable.legpress_img);
        }else if (currentUebung.getUebungName().contains("Calf Raises")) {
            holder.imageUebung.setImageResource(R.drawable.calfraises_img);
        }

    }

    public TrainingsplanUebungCrossRef getCrossRefAt(int position)
    {
        return uebungen.get(position);
    }

    @Override
    public int getItemCount() {
        return uebungen.size();
    }

    public void setUebungen(List<TrainingsplanUebungCrossRef> uebungen)
    {
        this.uebungen = uebungen;
        notifyDataSetChanged();
    }

    class CrossRefHolder extends RecyclerView.ViewHolder {
        private TextView textUeberschrift;
        private TextView textUebungName;
        private ImageView imageUebung;

        private TextView textKg;
        private TextView textSets;
        private TextView textRepeats;

        public CrossRefHolder(@NonNull View itemView) {
            super(itemView);

            textUebungName = itemView.findViewById(R.id.uebungUeberschriftTrainingsplan);
            imageUebung = itemView.findViewById(R.id.uebungImageTrainingsplan);
            textKg = itemView.findViewById(R.id.kgText);
            textSets = itemView.findViewById(R.id.setsText);
            textRepeats = itemView.findViewById(R.id.repeatsText);

        }
    }
}
