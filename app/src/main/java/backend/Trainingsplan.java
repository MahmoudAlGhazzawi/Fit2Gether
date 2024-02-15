package backend;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Trainingsplan implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long trainingsplanId;

    private String tpName;

    public Trainingsplan(){

        trainingsplanId = null;
    }

    public Trainingsplan(String tpName) {
        this.tpName = tpName;
        trainingsplanId = null;
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

