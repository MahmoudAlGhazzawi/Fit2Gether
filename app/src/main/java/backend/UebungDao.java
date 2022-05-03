package backend;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UebungDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertUebung(Uebung uebung);

    @Delete
    public void deleteUebung(Uebung uebung);

    @Query("SELECT * FROM Uebung WHERE uebungName = :name")
    public LiveData<List<Uebung>> getByName(String name);

    @Query("DELETE FROM Uebung")
    public void deleteAllUebungen();

    @Query("SELECT * FROM Uebung WHERE Uebung.uebungName= :name")
    public LiveData<Uebung> getUebungByName(String name);

    @Query("SELECT * FROM Uebung")
    public LiveData<List<Uebung>> getAllUebungen();
}

