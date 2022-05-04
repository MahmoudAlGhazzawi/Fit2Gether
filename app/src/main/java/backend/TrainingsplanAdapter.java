package backend;


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

public class TrainingsplanAdapter extends RecyclerView.Adapter <TrainingsplanAdapter.TrainingsplanHolder>{

    private List<Trainingsplan> trainingsplaene = new ArrayList<>();

    @NonNull
    @Override
    public TrainingsplanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leon_trainingsplan_item,parent,false);
        return new TrainingsplanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingsplanHolder holder, int position) {
        Trainingsplan currentPlan = trainingsplaene.get(position);
        holder.textTrainingsPlanUeberschrift.setText(currentPlan.getTpName());
        holder.textTrainingsPlanDescription.setText("");


        if(currentPlan.getTpName().contains("Pull")) {
            holder.imageTrainingsPlan.setImageResource(R.drawable.pull_day);
        }
        else if(currentPlan.getTpName().contains("Push")){
            holder.imageTrainingsPlan.setImageResource(R.drawable.bench_press);
        }
        else if(currentPlan.getTpName().contains("Leg")){
            holder.imageTrainingsPlan.setImageResource(R.drawable.leg_day);
        }
        else if(currentPlan.getTpName().contains("Ganz")){
            holder.imageTrainingsPlan.setImageResource(R.drawable.ganz_koerper);
        }
        else if(currentPlan.getTpName().contains("Arm")){
            holder.imageTrainingsPlan.setImageResource(R.drawable.arm);
        }
        else {
            holder.imageTrainingsPlan.setImageResource(R.drawable.default_workout);
        }
    }

    @Override
    public int getItemCount() {
        return trainingsplaene.size();
    }

    public void setTrainingsplaene(List<Trainingsplan> trainingsplaene){
        this.trainingsplaene = trainingsplaene;
        notifyDataSetChanged();
    }
    class TrainingsplanHolder extends RecyclerView.ViewHolder{
        private TextView textTrainingsPlanUeberschrift;
        private TextView textTrainingsPlanDescription;
        private ImageView imageTrainingsPlan;

        public TrainingsplanHolder(@NonNull View itemView) {
            super(itemView);
            textTrainingsPlanUeberschrift = itemView.findViewById(R.id.trainingsPlanUeberschrift);
            textTrainingsPlanDescription = itemView.findViewById(R.id.trainingsPlanDescription);
            imageTrainingsPlan = itemView.findViewById(R.id.trainingsPlanImage);
        }
    }
}
