package backend;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(version = 7, entities = {Trainingsplan.class, Uebung.class, TrainingsplanUebungCrossRef.class
})
public abstract class Datenbank extends RoomDatabase {

    private static Datenbank instance;

    public abstract TrainingsplanDao trainingsplanDao();

    public abstract UebungDao uebungDao();


    public static synchronized Datenbank getInstance(Context context){
        if (instance == null) {


//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    Datenbank.class, "Datenbank").fallbackToDestructiveMigration().build();
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Datenbank.class, "Datenbank").fallbackToDestructiveMigration().build();
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    Datenbank.class, "Datenbank").fallbackToDestructiveMigration().build();
            instance.populateInitialData();


        }
        return instance; 
    }

    private void populateInitialData() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(uebungDao().count() == 0) {
                        Uebung bankdrücken = new Uebung("Bankdruecken");
                        Uebung pullups = new Uebung("Pullups");
                        Uebung pushups = new Uebung("Pushups");
                        Uebung squats = new Uebung("Squads");
                        Uebung calfraises = new Uebung("Calf Raises");
                        Uebung cableflies = new Uebung("Cable Flies");
                        Uebung legPress = new Uebung("LegPress");
                        Uebung dips = new Uebung("Dips");
                        Uebung rudern = new Uebung("Rudern");

                        uebungDao().insertUebung(bankdrücken);
                        uebungDao().insertUebung(pullups);
                        uebungDao().insertUebung(pushups);
                        uebungDao().insertUebung(squats);
                        uebungDao().insertUebung(calfraises);
                        uebungDao().insertUebung(legPress);

                        uebungDao().insertUebung(cableflies);
                        uebungDao().insertUebung(dips);
                        uebungDao().insertUebung(rudern);
                    }
                }
            }).start();
        }
}


