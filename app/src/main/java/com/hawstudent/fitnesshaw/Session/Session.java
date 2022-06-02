package com.hawstudent.fitnesshaw.Session;

//Man sieht nur fortschritt von freunden? - macht es einfacher
// -> Deren Trainingsplan wird geladen mit gewichten etc und freunde können mit der SessionID(key) darauf zugreifen und sich das ansehen
public class Session {
    private int id;
    private String workout_name;
    private int workout_saetze;
    private int workout_wiederholungen;
    private int workout_gewicht;

    public Session(int id, String workout_name, int workout_saetze, int workout_wiederholungen, int workout_gewicht) {
        this.id = id;
        this.workout_name = workout_name;
        this.workout_saetze = workout_saetze;
        this.workout_wiederholungen = workout_wiederholungen;
        this.workout_gewicht = workout_gewicht;
    }

    public Session() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }

    public int getWorkout_saetze() {
        return workout_saetze;
    }

    public void setWorkout_saetze(int workout_saetze) {
        this.workout_saetze = workout_saetze;
    }

    public int getWorkout_wiederholungen() {
        return workout_wiederholungen;
    }

    public void setWorkout_wiederholungen(int workout_wiederholungen) {
        this.workout_wiederholungen = workout_wiederholungen;
    }

    public int getWorkout_gewicht() {
        return workout_gewicht;
    }

    public void setWorkout_gewicht(int workout_gewicht) {
        this.workout_gewicht = workout_gewicht;
    }
}
