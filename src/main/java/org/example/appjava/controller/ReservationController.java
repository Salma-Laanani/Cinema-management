package org.example.appjava.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import org.example.appjava.model.Film;
import org.example.appjava.model.Reservation;
import org.example.appjava.model.Seance;
import org.example.appjava.model.Utilisateur;

import java.time.LocalDateTime;

public class ReservationController {
    private Utilisateur utilisateurconnecte;
    public ReservationController(Utilisateur utilisateurconnecte){
        this.utilisateurconnecte=utilisateurconnecte;
    }
@FXML
    TableView<Reservation> reservationTable;
@FXML
    TableColumn<Reservation,String> utilisateurColumn;
@FXML
    TableColumn<Reservation,String > seanceColumn;
@FXML
    TableColumn<Reservation,Integer> nombrebilletColumn;


public void setutilisateurconnecte(Utilisateur utilisateur){
    this.utilisateurconnecte=utilisateur;
}

    private DataManager dataManager = DataManager.getInstance();
//ObservableList<Reservation> mesreservations= FXCollections.observableArrayList(new Reservation(new Utilisateur("Oufkir","Hasnaa","hasnaa@gmail.com","1345","user"),new Seance(new Film("mon voisin totoro", "anime", 148, "Un film d'un bus chat.",new Image("src/main/resources/org/example/appjava/Images/totoro.jpg")),LocalDateTime.of(2025,1,15,18,30),100),3));
@FXML
private void initialize() {
        // Lier la colonne utilisateur pour afficher uniquement le nom et le prénom
        utilisateurColumn.setCellValueFactory(cellData -> {
            // Accès à l'utilisateur associé à la réservation
            Utilisateur utilisateur = cellData.getValue().getUtilisateur();
            return new SimpleStringProperty(utilisateur.getNom() + " " + utilisateur.getPrenom());
        });

        // Lier la colonne séance pour afficher le titre du film et la date de la séance
        seanceColumn.setCellValueFactory(cellData -> {
            // Accès à la séance associée à la réservation
            Seance seance = cellData.getValue().getSeance ();
            return new SimpleStringProperty(
                    seance.getFilm().getTitre() + " | Séance : " + seance.dateHeureProperty().toString()
            );
        });

        // Lier la colonne nombre de billets
        nombrebilletColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getNombreBillets()).asObject()
        );

        reservationTable.setItems(dataManager.getMesreservations(utilisateurconnecte.getEmail()));
        refreshReservations();

        // Ajouter les réservations existantes à la table
        //reservationTable.setItems(dataManager.getReservation());
    }
    public void refreshReservations() {
        if (utilisateurconnecte != null) {
            reservationTable.setItems(dataManager.getMesreservations(utilisateurconnecte.getEmail()));
        }
    }


public void addreservation(Reservation reservation){
    if(reservation!=null){
        dataManager.addReservation(reservation);
        reservationTable.refresh();
    }
}

}
