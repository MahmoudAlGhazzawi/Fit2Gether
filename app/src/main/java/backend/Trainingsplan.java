package backend;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Trainingsplan {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long trainingsplanId;

    private String tpName;

    public Trainingsplan(String tpName) {
        this.tpName = tpName;
    }

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName){
        this.tpName = tpName;
    }

    @NonNull
    public Long getTrainingsplanId() {
        return trainingsplanId;
    }

    public void setTrainingsplanId(@NonNull Long trainingsplanId) {
        this.trainingsplanId = trainingsplanId;
    }
}

