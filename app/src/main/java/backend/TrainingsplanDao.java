package backend;


import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrainingsplanDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertTrainingsplan(Trainingsplan trainingsplan);

    @Delete
    public void deleteTrainingsplan(Trainingsplan trainingsplan);

    @Query(("DELETE FROM Trainingsplan"))
    public void deleteAllTrainingsplaene();

    @Query("SELECT * FROM Trainingsplan WHERE tpName=:name")
    public LiveData<List<Trainingsplan>> getByName(String name);

    @Query("SELECT * FROM Trainingsplan WHERE trainingsplanId=:id")
    public  LiveData<List<Trainingsplan>> getById(Long id);

    @Query("SELECT * FROM TrainingsplanUebungCrossRef WHERE trainingsplanId = :id")
    public LiveData<List<TrainingsplanUebungCrossRef>> getCrossRefByTrainingsplan(long id);

    @Query("SELECT * FROM TrainingsplanUebungCrossRef WHERE trainingsplanId = :trainingplanId AND uebungId = :uebungId")
    public LiveData<List<TrainingsplanUebungCrossRef>> getCrossRef(long trainingplanId, long uebungId);

    @Query("SELECT * FROM Trainingsplan")
    public LiveData<List<Trainingsplan>> getAllTrainingsplaene();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertCrossRef(List<TrainingsplanUebungCrossRef> crossRefs);

    @Delete
    public void deleteCrossRef(TrainingsplanUebungCrossRef crossRef);

    @Transaction
    @Update(onConflict = OnConflictStrategy.IGNORE)
    public void updateCrossRef(TrainingsplanUebungCrossRef crossRef);

    @Transaction
    @Query("SELECT * FROM Trainingsplan WHERE trainingsplanId =:trainingsplanId")
    public LiveData<TrainingsplanWithUebungen> getPlanWithUebungen(long trainingsplanId);
}

