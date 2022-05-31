package backend;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class TrainingsplanViewModel extends AndroidViewModel {

    private TrainingsplanRepository repository;
    private UebungRepository uebungRepository;
    private LiveData<List<Uebung>> allUebungen;
    private LiveData<List<Trainingsplan>> allTrainingsplaene;

    public TrainingsplanViewModel(@NonNull Application application) {
        super(application);
        repository = new TrainingsplanRepository(application);
        allTrainingsplaene = repository.getAllTrainingsplaene();
        uebungRepository = new UebungRepository(application);
        allUebungen = uebungRepository.getAllUebungen();
    }

    public void insertUebung(Uebung uebung) {
        uebungRepository.insertUebung(uebung);
    }

    public void deleteUebung(Uebung uebung) {
        uebungRepository.deleteUebung(uebung);
    }

    public void deleteAllUebungen(){
        uebungRepository.deleteAllUebungen();
    }

    public LiveData<List<Uebung>> getAllUebungen()
    {
        return allUebungen;
    }

    public LiveData<List<TrainingsplanUebungCrossRef>> getAllUebungenByTrainingsplan(Trainingsplan trainingsplan)
    {
        return repository.getCrossRefByTrainingsplan(trainingsplan.getTrainingsplanId());
    }

    public LiveData<List<Trainingsplan>> getTrainingsplanByName(String name)
    {
        return repository.getByName(name);
    }

    public LiveData<List<Uebung>> getUebungByName(String name)
    {
        return uebungRepository.getByName(name);
    }

    public LiveData<List<TrainingsplanUebungCrossRef>> getGewichtWdhSaetze(Trainingsplan trainingsplan, Uebung uebung)
    {
        return repository.getCrossRef(trainingsplan.getTrainingsplanId(), uebung.getUebungId());
    }

    public void deleteTrainingsplan(Trainingsplan trainingsplan)
    {
        repository.deleteTrainingsplan(trainingsplan);
    }

    public void deleteAllTrainingsplaene()
    {
        repository.deleteAllTrainingsplaene();
    }

    public void insertTrainingsplan(Trainingsplan trainingsplan) {
        repository.insertTrainingsplan(trainingsplan);
    }

    public void insertTrainingsplan(String name)
    {
        repository.insertTrainingsplan(new Trainingsplan(name));
    }

    public void deleteCrossRef(TrainingsplanUebungCrossRef crossRef){
        repository.deleteCrossRef(crossRef);
    }

    public void deleteCrossRefByTrainingsplan(Trainingsplan trainingsplan){
        repository.deleteCrossRefByTrainingsplan(trainingsplan.getTrainingsplanId());
    }

    public void insertUebungenIntoTrainingplan(Trainingsplan trainingsplan, List<Uebung> uebungen) {
        List<TrainingsplanUebungCrossRef> crossRefList = new ArrayList<>();
        for(int i = 0; i< uebungen.size();i++)
        {
            crossRefList.add(new TrainingsplanUebungCrossRef(trainingsplan.getTrainingsplanId(), uebungen.get(i).getUebungId(), uebungen.get(i).getUebungName()));
        }
        repository.insertCrossRef(crossRefList);
    }

    public void setGewichtWdhSaetze(Trainingsplan trainingsplan, Uebung uebung, int gewicht, int wdh, int saetze)
    {
        TrainingsplanUebungCrossRef crossRef = new TrainingsplanUebungCrossRef(trainingsplan.getTrainingsplanId(), uebung.getUebungId(), uebung.getUebungName());
        crossRef.setGewicht(gewicht);
        crossRef.setAnzahlWdh(wdh);
        crossRef.setAnzahlSaetze(saetze);
        repository.updateCrossRef(crossRef);
    }

    public LiveData<List<Trainingsplan>> getAllTrainingsplaene(){
        return allTrainingsplaene;
    }



    public LiveData<TrainingsplanWithUebungen> getTrainingsplanWithUebungen(Trainingsplan trainingsplan)
    {
        return repository.getPlanWithUebungen(trainingsplan.getTrainingsplanId());
    }


}

