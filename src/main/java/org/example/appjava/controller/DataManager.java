package org.example.appjava.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.example.appjava.model.Film;
import org.example.appjava.model.Reservation;
import org.example.appjava.model.Seance;
import org.example.appjava.model.Utilisateur;

import java.time.LocalDateTime;

public class DataManager {
    private static DataManager instance;
    private ObservableList<Utilisateur> users;
    private ObservableList<Reservation> mesreservations;

    private DataManager() {
        users = FXCollections.observableArrayList();
        mesreservations=FXCollections.observableArrayList();

        // Ajoutez des utilisateurs par défaut si nécessaire
        users.add(new Utilisateur("laanani", "salma", "salmalaanani@gmail.com", "123", "admin"));
        users.add(new Utilisateur("Oufkir", "Hasnaa", "hasnaa@gmail.com", "1345", "user"));

   mesreservations.add(new Reservation(new Utilisateur("Oufkir","Hasnaa","hasnaa@gmail.com","1345","user"),new Seance(new Film("mon voisin totoro", "anime", 148, "Un film d'un bus chat.",new Image("file:src/main/resources/org/example/appjava/Images/totoro.jpg")), LocalDateTime.of(2025,1,15,18,30),100),3));
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ObservableList<Utilisateur> getUsers() {
        return users;
    }

    public void addUser(Utilisateur user) {
        if (user != null && users.stream().noneMatch(u -> u.getEmail().equals(user.getEmail()))) {
            users.add(user);

        }
    }

    public ObservableList<Reservation> getReservation(){
        return  mesreservations;
    }
    public void addReservation(Reservation reservation){
        if (reservation != null && mesreservations.stream().noneMatch(r->r.getUtilisateur().getEmail().equals(reservation.getUtilisateur().getEmail()) &&  r.getSeance().equals(reservation.getSeance())))
               {
          mesreservations.add(reservation);
      }
    }

    public ObservableList<Reservation> getMesreservations(String email){

        return mesreservations.filtered(reservation->reservation.getUtilisateur().getEmail().equals(email));
    }
}
