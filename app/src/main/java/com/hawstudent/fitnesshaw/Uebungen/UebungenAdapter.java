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

import backend.TrainingsplanUebungCrossRef;
import backend.Uebung;

public class UebungenAdapter extends RecyclerView.Adapter<UebungenAdapter.UebungHolder>{

    private List<Uebung> uebungen = new ArrayList<>();
    private OnUebungListener mOnUebungListener;

    public UebungenAdapter(OnUebungListener onUebungListener){
        mOnUebungListener = onUebungListener;
    }
    @NonNull
    @Override
    public UebungHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uebung_item, parent, false);
        return new UebungHolder(view,mOnUebungListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UebungHolder holder, int position) {
        Uebung currentUebung = uebungen.get(position);
        holder.textUebungName.setText(currentUebung.getUebungName());

        //
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

    @Override
    public int getItemCount() {
        return uebungen.size();
    }

    public void setUebungen(List<Uebung> uebungen)
    {
        this.uebungen = uebungen;
        notifyDataSetChanged();
    }



    class UebungHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textUebungName;
        private ImageView imageUebung;
        OnUebungListener onUebungListener;

        public UebungHolder(@NonNull View itemView, OnUebungListener onUebungListener) {
            super(itemView);
            textUebungName = itemView.findViewById(R.id.uebungUeberschrift);
            imageUebung = itemView.findViewById(R.id.uebungImage);
            itemView.setOnClickListener(this);
            this.onUebungListener = onUebungListener;
        }

        @Override
        public void onClick(View view) {
            onUebungListener.onUebungClick(uebungen.get(getAdapterPosition()));
        }
    }

    public interface OnUebungListener{
        void onUebungClick(Uebung uebung);
    }
}
