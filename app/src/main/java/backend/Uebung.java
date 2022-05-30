package backend;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Uebung implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long uebungId;


    private String uebungName;

    public Uebung(String uebungName) {
        this.uebungName = uebungName;
    }

    public void setUebungName(String uebbungName) {
        this.uebungName = uebungName;
    }

    public String getUebungName() {
        return uebungName;
    }

    public long getUebungId() {
        return uebungId;
    }

    public void setUebungId(long uebungId) {
        this.uebungId = uebungId;
    }
}

