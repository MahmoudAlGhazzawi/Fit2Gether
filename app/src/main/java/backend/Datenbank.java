package backend;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(version = 6, entities = {Trainingsplan.class, Uebung.class, TrainingsplanUebungCrossRef.class
})
public abstract class Datenbank extends RoomDatabase {

    private static Datenbank instance;

    public abstract TrainingsplanDao trainingsplanDao();

    public abstract UebungDao uebungDao();

    public static synchronized Datenbank getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Datenbank.class, "Datenbank").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}


