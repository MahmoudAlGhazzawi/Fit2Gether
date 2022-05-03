package backend;


import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class TrainingsplanWithUebungen {

    @Embedded public Trainingsplan trainingsplan;
    @Relation(
            parentColumn = "trainingsplanId",
            entityColumn = "uebungId",
            associateBy = @Junction(TrainingsplanUebungCrossRef.class))
    public List<Uebung> uebungen;
}
