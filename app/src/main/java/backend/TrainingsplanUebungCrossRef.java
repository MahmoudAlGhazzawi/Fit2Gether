package backend;


import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"trainingsplanId", "uebungId"})
public class TrainingsplanUebungCrossRef {

    @NonNull
    private Long trainingsplanId;

    @NonNull
    private Long uebungId;

    private String uebungName;

    private int gewicht;

    private int anzahlSaetze;

    private int anzahlWdh;

    public TrainingsplanUebungCrossRef(long trainingsplanId, long uebungId, String uebungName) {
        this.trainingsplanId = trainingsplanId;
        this.uebungId = uebungId;
        this.uebungName = uebungName;
    }

    public TrainingsplanUebungCrossRef(){

        trainingsplanId = null;
    }

    public void setTrainingsplanId(@NonNull Long trainingsplanId) {
        this.trainingsplanId = trainingsplanId;
    }

    public void setUebungId(@NonNull Long uebungId) {
        this.uebungId = uebungId;
    }

    public void setUebungName(String uebungName) {
        this.uebungName = uebungName;
    }

    public String getUebungName()
    {
        return uebungName;
    }

    public Long getTrainingsplanId() {
        return trainingsplanId;
    }

    public Long getUebungId() {
        return uebungId;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getAnzahlSaetze() {
        return anzahlSaetze;
    }

    public void setAnzahlSaetze(int anzahlSaetze) {
        this.anzahlSaetze = anzahlSaetze;
    }

    public int getAnzahlWdh() {
        return anzahlWdh;
    }

    public void setAnzahlWdh(int anzahlWdh) {
        this.anzahlWdh = anzahlWdh;
    }
}
