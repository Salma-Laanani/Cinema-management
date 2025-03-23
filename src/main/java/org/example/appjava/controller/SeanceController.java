package org.example.appjava.controller;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.appjava.model.Film;
import org.example.appjava.model.Reservation;
import org.example.appjava.model.Seance;
import org.example.appjava.model.Utilisateur;

import java.time.LocalDateTime;
import java.util.Optional;

public class SeanceController {
    @FXML
    private TableView<Seance> seanceTable;
    @FXML
    private TableColumn<Seance,String> filmColumn;
    @FXML
    private TableColumn<Seance,LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Seance,Integer> placesColumn;

    private DataManager dataManager=DataManager.getInstance();
    public int nombrebilletssaisie;

    private Utilisateur utilisateurconnecte;
    public void setutilisateurconnecte(Utilisateur utilisateur){
        this.utilisateurconnecte=utilisateur;
    }



    ObservableList<Seance> filterdseances= FXCollections.observableArrayList();
    private Film selectedFilm;
    ObservableList<Reservation> reservedseance;
    public void setSelectedFilm(Film film) {
        this.selectedFilm = film;
        if (selectedFilm != null) {
            filterdseances=selectedFilm.getSeances();
            seanceTable.getItems().setAll(filterdseances);

        }
    }
   ObservableList<Seance> allseances=FXCollections.observableArrayList(new Seance(new Film("mon voisin totoro", "anime", 148, "Un film d'un bus chat.",new Image("file:src/main/resources/org/example/appjava/Images/totoro.jpg")),LocalDateTime.of(2025,1,15,18,30),100),new Seance(new Film("mon voisin totoro", "anime", 148, "film d'un bus chat.",new Image("file:src/main/resources/org/example/appjava/Images/totoro.jpg")),LocalDateTime.of(2025, 1, 16, 20, 00),100));

    public void initialize() {


        // Lier les colonnes de la table aux propriétés
        filmColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getFilm().getTitre()));
        dateColumn.setCellValueFactory(cellData->cellData.getValue().dateHeureProperty());
        placesColumn.setCellValueFactory(cellData->cellData.getValue().placesDisponiblesProperty().asObject());
        seanceTable.setItems(allseances);
}
    @FXML
    private  void onReserver(){
     Seance selectedseance=seanceTable.getSelectionModel().getSelectedItem();
        Utilisateur utilisateur = new Utilisateur("Oufkir", "Hasnaa", "hasnaa@gmail.com", "1345", "user");
        Reservation newReservation = new Reservation(utilisateurconnecte, selectedseance, 1); // Par e


     if(selectedseance!=null){
   try{
       TextInputDialog billetdialog=new TextInputDialog();
       billetdialog.setTitle("Réservation");
       billetdialog.setHeaderText("Réservez des billets");
       billetdialog.setContentText("Entrez le nombre de billets que vous souhaitez réserver :");

       Optional<String> result=billetdialog.showAndWait();
       if(result.isPresent()){try{
           int nombrebillets=Integer.parseInt(result.get());
           this.nombrebilletssaisie=nombrebillets;
           if(selectedseance.getPlacesDisponibles()>=newReservation.getNombreBillets()) {
               selectedseance.reserverPlaces(newReservation.getNombreBillets());
               selectedseance.reserverPlaces(nombrebillets);
               dataManager.addReservation(newReservation);
               Alert alert=new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("reservation reussie");
               alert.setHeaderText(null);
               alert.setContentText("la reservation est faite avec succees");
               alert.showAndWait();
           } else{
               Alert alert=new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("reservation Impossible");
               alert.setHeaderText(null);
               alert.setContentText( "pas de places disponibles pour cette seance");
               alert.showAndWait();

           } } catch (Exception e) {
           throw new RuntimeException(e);
       }
       }

       FXMLLoader reservView=new FXMLLoader(getClass().getResource("/org/example/appjava/reservation-view.fxml")) ;
      reservView.setControllerFactory(param -> new ReservationController(utilisateurconnecte));
       Parent root=reservView.load();
       root.getStylesheets().add(getClass().getResource("/style/stylereservation.css").toExternalForm());//appliquer css
       ReservationController reservationController=reservView.getController();

       reservationController.setutilisateurconnecte(utilisateurconnecte);
       reservationController.refreshReservations();
       //reservationController.addreservation(newReservation);
       Stage stage=new Stage();
       stage.setTitle("Mes reservations");
       stage.setScene(new Scene(root));
       stage.show();
   } catch (Exception e) {
       e.printStackTrace();
   }
     }else{
         Alert alert=new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Nonselected Seance");
         alert.setHeaderText(null);
         alert.setContentText("veuillez selectionner une seance on premier");
         alert.showAndWait();
     }
    }

    public int getNombrebilletssaisie(){
        return nombrebilletssaisie;
    }

}
