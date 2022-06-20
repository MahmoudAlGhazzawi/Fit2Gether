package backend;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingsplanRepository {

    private TrainingsplanDao trainingsplanDao;
    private LiveData<List<Trainingsplan>> allTrainingsplaene;

    public TrainingsplanRepository(Application application)
    {
        Datenbank datenbank = Datenbank.getInstance(application);
        trainingsplanDao = datenbank.trainingsplanDao();
        allTrainingsplaene = trainingsplanDao.getAllTrainingsplaene();
    }

    public void deleteTrainingsplan(Trainingsplan trainingsplan)
    {
        new DeleteTrainingsplanAT(trainingsplanDao).execute(trainingsplan);
    }

    public LiveData<List<TrainingsplanUebungCrossRef>> getAllCrossRefs(){
        return trainingsplanDao.getAllCrossRefs();
    }

    public void deleteAllTrainingsplaene()
    {
        new DeleteAllTrainingsplaeneAT(trainingsplanDao).execute();
    }

    public  LiveData<List<Trainingsplan>>getByName(String name)
    {
        return trainingsplanDao.getByName(name);
    }

    public  LiveData<List<Trainingsplan>> getById(Long id)
    {
        return trainingsplanDao.getById(id);
    }

    public LiveData<List<TrainingsplanUebungCrossRef>> getCrossRefByTrainingsplan(long trainingsplanId)
    {
        return trainingsplanDao.getCrossRefByTrainingsplan(trainingsplanId);
    }

    public LiveData<List<TrainingsplanUebungCrossRef>> getCrossRef(long trainingsplanId, long uebungId)
    {
        return trainingsplanDao.getCrossRef(trainingsplanId, uebungId);
    }

    public void updateCrossRef(TrainingsplanUebungCrossRef crossRef)
    {
        new updateCrossRefAT(trainingsplanDao).execute(crossRef);
    }

    public void deleteCrossRef(TrainingsplanUebungCrossRef crossRef)
    {
        new DeleteCrossRefAT(trainingsplanDao).execute(crossRef);
    }

    public void deleteCrossRefByTrainingsplan(long trainingsplanId)
    {
        new DeleteCrossRefByTrainingsplanAT(trainingsplanDao).execute(trainingsplanId);
    }

    public void insertCrossRef(List<TrainingsplanUebungCrossRef> crossRefs)
    {
        new InsertCrossRefAT(trainingsplanDao).execute(crossRefs);
    }


    public LiveData<TrainingsplanWithUebungen> getPlanWithUebungen(long trainingsplanId){
        return trainingsplanDao.getPlanWithUebungen(trainingsplanId);
    }


    public void insertTrainingsplan(Trainingsplan trainingsplan)
    {
        new InsertTrainingsplanAsyncTaks(trainingsplanDao).execute(trainingsplan);
    }

    public LiveData<List<Trainingsplan>> getAllTrainingsplaene() {
        return allTrainingsplaene;
    }

    private static class InsertCrossRefAT extends AsyncTask<List<TrainingsplanUebungCrossRef>,Void,Void>{
        private TrainingsplanDao trainingsplanDao;

        private InsertCrossRefAT(TrainingsplanDao trainingsplanDao)
        {
            this.trainingsplanDao = trainingsplanDao;

        }

        @Override
        protected Void doInBackground(List<TrainingsplanUebungCrossRef>...crossRefs){
            trainingsplanDao.insertCrossRef(crossRefs[0]);
            return null;
        }
    }

    private static class InsertTrainingsplanAsyncTaks extends AsyncTask<Trainingsplan,Void,Void>{
        private TrainingsplanDao trainingsplanDao;
        private InsertTrainingsplanAsyncTaks(TrainingsplanDao trainingsplanDao)
        {
            this.trainingsplanDao = trainingsplanDao;
        }

        @Override
        protected Void doInBackground(Trainingsplan...trainingsplans){
            trainingsplanDao.insertTrainingsplan(trainingsplans[0]);
            return null;
        }
    }

    private static class DeleteTrainingsplanAT extends AsyncTask<Trainingsplan,Void,Void>{
        private TrainingsplanDao trainingsplanDao;
        private DeleteTrainingsplanAT(TrainingsplanDao trainingsplanDao)
        {
            this.trainingsplanDao = trainingsplanDao;
        }

        @Override
        protected Void doInBackground(Trainingsplan...trainingsplans){
            trainingsplanDao.deleteTrainingsplan(trainingsplans[0]);
            return null;
        }
    }

    private static class DeleteAllTrainingsplaeneAT extends AsyncTask<Void,Void,Void>{
        private TrainingsplanDao trainingsplanDao;
        private DeleteAllTrainingsplaeneAT(TrainingsplanDao trainingsplanDao)
        {
            this.trainingsplanDao = trainingsplanDao;
        }

        @Override
        protected Void doInBackground(Void...Void){
            trainingsplanDao.deleteAllTrainingsplaene();
            return null;
        }
    }


    private static class updateCrossRefAT extends AsyncTask<TrainingsplanUebungCrossRef,Void,Void>{
        private TrainingsplanDao trainingsplanDao;

        private updateCrossRefAT(TrainingsplanDao trainingsplanDao)
        {
            this.trainingsplanDao = trainingsplanDao;

        }

        @Override
        protected Void doInBackground(TrainingsplanUebungCrossRef... trainingsplanUebungCrossRefs) {
            trainingsplanDao.updateCrossRef(trainingsplanUebungCrossRefs[0]);
            return null;
        }
    }

    private static class DeleteCrossRefAT extends AsyncTask<TrainingsplanUebungCrossRef,Void,Void>{
        private TrainingsplanDao trainingsplanDao;
        private DeleteCrossRefAT(TrainingsplanDao trainingsplanDao)
        {
            this.trainingsplanDao = trainingsplanDao;
        }

        @Override
        protected Void doInBackground(TrainingsplanUebungCrossRef...crossRefs){
            trainingsplanDao.deleteCrossRef(crossRefs[0]);
            return null;
        }
    }

    private static class DeleteCrossRefByTrainingsplanAT extends AsyncTask<Long,Void,Void>{
        private TrainingsplanDao trainingsplanDao;
        private DeleteCrossRefByTrainingsplanAT(TrainingsplanDao trainingsplanDao)
        {
            this.trainingsplanDao = trainingsplanDao;
        }

        @Override
        protected Void doInBackground(Long...longs){
            trainingsplanDao.deleteCrossRefByTrainingsplan(longs[0]);
            return null;
        }
    }
}

