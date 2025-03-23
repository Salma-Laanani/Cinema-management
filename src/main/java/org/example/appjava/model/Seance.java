package org.example.appjava.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class Seance {
    private final ObjectProperty<LocalDateTime> dateHeure;
    private final IntegerProperty placesDisponibles;
    private Film film;

    // Constructor
    public Seance(Film film,LocalDateTime dateHeure, int disp) {
        this.film=film;
        this.dateHeure = new SimpleObjectProperty<>(dateHeure);
        this.placesDisponibles = new SimpleIntegerProperty(disp);
    }

    // Getter for 'dateHeure'
    public ObjectProperty<LocalDateTime> dateHeureProperty() {
        return dateHeure;
    }


    // Getter and Setter for 'placesDisponibles'
    public IntegerProperty placesDisponiblesProperty() {
        return placesDisponibles;
    }


    public int getPlacesDisponibles() {
        return placesDisponibles.get();
    }

    public void setPlacesDisponibles(int places) {
        this.placesDisponibles.set(places);
    }

    // Getter and Setter for 'film'
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    // Reserve Places Method
    public void reserverPlaces(int nombre) {
        if (this.placesDisponibles.get() >= nombre) {
            this.placesDisponibles.set(this.placesDisponibles.get() - nombre);
            System.out.println(nombre + " places réservées.");
        } else {
            System.out.println("Pas assez de places disponibles.");
        }
    }

    // Get Seance Details Method
    public String getDetails() {
        return "La séance de film " + film + " à l'heure " + dateHeure + " et il y a " + placesDisponibles.get() + " places disponibles.";
    }
}
