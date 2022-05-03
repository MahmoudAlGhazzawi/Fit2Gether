package backend;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.text_view_name.setText(currentPlan.getTpName());
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
        private TextView text_view_name;

        public TrainingsplanHolder(@NonNull View itemView) {
            super(itemView);
            text_view_name = itemView.findViewById(R.id.text_view_name);
        }
    }
}
