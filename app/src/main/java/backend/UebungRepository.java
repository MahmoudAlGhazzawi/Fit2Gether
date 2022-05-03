package backend;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UebungRepository {

    private UebungDao uebungDao;
    private LiveData<List<Uebung>> allUebungen;

    public UebungRepository(Application application)
    {
        Datenbank datenbank = Datenbank.getInstance(application);
        uebungDao = datenbank.uebungDao();
        allUebungen = uebungDao.getAllUebungen();
    }

    public LiveData<List<Uebung>> getAllUebungen()
    {
        return allUebungen;
    }

    public void insertUebung(Uebung uebung)
    {
        new UebungRepository.InsertUebungAsyncTaks(uebungDao).execute(uebung);
    }

    public LiveData<List<Uebung>> getByName(String name)
    {
        return uebungDao.getByName(name);
    }
    public void deleteUebung(Uebung uebung){
        new UebungRepository.DeleteUebungAsyncTaks(uebungDao).execute(uebung);
    }

    public void deleteAllUebungen(){
        new UebungRepository.DeleteAllUebungenAT(uebungDao).execute();
    }

    private static class InsertUebungAsyncTaks extends AsyncTask<Uebung,Void,Void> {
        private UebungDao uebungDao;
        private InsertUebungAsyncTaks(UebungDao uebungDao)
        {
            this.uebungDao = uebungDao;
        }

        @Override
        protected Void doInBackground(Uebung...uebungen){
            uebungDao.insertUebung(uebungen[0]);
            return null;
        }
    }

    private static class DeleteUebungAsyncTaks extends AsyncTask<Uebung,Void,Void>{
        private UebungDao uebungDao;
        private DeleteUebungAsyncTaks(UebungDao uebungDao)
        {
            this.uebungDao = uebungDao;
        }

        @Override
        protected Void doInBackground(Uebung...uebungen){
            uebungDao.deleteUebung(uebungen[0]);
            return null;
        }
    }

    private static class DeleteAllUebungenAT extends AsyncTask<Void,Void,Void>{
        private UebungDao uebungDao;
        private DeleteAllUebungenAT(UebungDao uebungDao)
        {
            this.uebungDao = uebungDao;
        }

        @Override
        protected Void doInBackground(Void...Voids){
            uebungDao.deleteAllUebungen();
            return null;
        }
    }
}

