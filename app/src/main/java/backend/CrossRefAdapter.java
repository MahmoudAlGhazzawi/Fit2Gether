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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_uebungen_in_trainingsplan, parent, false);
        return new CrossRefHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrossRefHolder holder, int position) {
    TrainingsplanUebungCrossRef currentUebung = uebungen.get(position);
    holder.textUeberschrift.setText(trainingsplan.getTpName());
    holder.textUebungName.setText(currentUebung.getUebungName());
    holder.imageUebung.setImageResource(R.drawable.default_workout);
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

        public CrossRefHolder(@NonNull View itemView) {
            super(itemView);
            textUeberschrift = itemView.findViewById(R.id.textMyTrainingsplanUeberschrift);
            textUebungName = itemView.findViewById(R.id.trainingsPlanUeberschrift);
            imageUebung = itemView.findViewById(R.id.addUebung);
        }
    }
}