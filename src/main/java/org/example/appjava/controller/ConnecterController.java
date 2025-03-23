package org.example.appjava.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.appjava.model.Reservation;
import org.example.appjava.model.Seance;
import org.example.appjava.model.Utilisateur;

public class ConnecterController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> roleComboBox;

    private DataManager dataManager = DataManager.getInstance();

    private Utilisateur utilisateurconnecte;
    public void setutilisateurconnecte(Utilisateur utilisateur){
        this.utilisateurconnecte=utilisateur;
    }

    public String getprenom(){
        return prenom.getText();
    }


    @FXML
    public Utilisateur getcuruser(){
        Utilisateur curuser=new Utilisateur(nom.getText(), prenom.getText(), emailField.getText(), passwordField.getText(), roleComboBox.getValue());
        return curuser;
    }


    @FXML
    public void initialize() {
        String imagepath=getClass().getResource("/org/example/appjava/Images/ghiblifilms.jpeg").toExternalForm();

        Image img=new Image(imagepath);
        BackgroundImage ghibliimg=new BackgroundImage(img,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,true));

        anchorPane.setBackground(new Background(ghibliimg));

        roleComboBox.setItems(FXCollections.observableArrayList("admin", "user"));
    }





    @FXML
    private void handleLogin(){
      utilisateurconnecte=getcuruser();//voila la modification comme ca on peut resoudre le probleme
        String selectedRole = roleComboBox.getValue();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Vérification de l'email et du mot de passe (ici tu peux ajouter la logique de validation)
        if (nom.getText()!=null && prenom.getText()!=null && email != null && !email.isEmpty() && password != null && !password.isEmpty()) {

            // Recherche de l'utilisateur avec les informations de connexion


                if (selectedRole.equals("admin")  ) {
                     if (nom.getText().equals("Laanani")&&prenom.getText().equals("Salma")&& email.equals("salmalaanani@gmail.com")){
                         setutilisateurconnecte(getcuruser());
                    openAdminView(selectedRole);}// Vue d'administration

                    else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Acces refuse");
                    alert.setHeaderText("admin ");
                    alert.setContentText("seul l'admin a l'acces");
                    alert.showAndWait();}}

                    else if (selectedRole.equals("user")) {
                        setutilisateurconnecte(getcuruser());
                      dataManager.addUser(getcuruser());

                        openUserView(selectedRole); // Vue utilisateur, mais ici on ne fait rien si tu veux
                    }


        } else   {
             Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setTitle("champs invalides");
             alert.setHeaderText(null);
             alert.setContentText("veuillez remplir les deux champs");
             alert.showAndWait();
        }


    }



    private void openAdminView(String role){
        try{

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/appjava/film-view.fxml"));
            loader.setControllerFactory(param -> new FilmController(utilisateurconnecte));
                Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("/style/style1.css").toExternalForm());
               Scene scene = new Scene(root);
               // Appliquer le CSS

                 FilmController filmController = loader.getController();


                filmController.setUserRole(role);


                filmController.addcuruser(getcuruser());

                Seance selectedseance = filmController.seanceTable.getSelectionModel().getSelectedItem();
                if(selectedseance!=null){
                SeanceController seanceController=new SeanceController();
                int nombrebillets=seanceController.getNombrebilletssaisie();



                Reservation reservcuruser = new Reservation(utilisateurconnecte, selectedseance,nombrebillets);
               dataManager.addReservation(reservcuruser);}


                Stage stage = new Stage();
                stage.setTitle("AdminView");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void openUserView(String role){
        try{


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/appjava/film-view.fxml"));
             loader.setControllerFactory(param -> new FilmController(utilisateurconnecte));
                Parent root = loader.load();
               Scene scene = new Scene(root);
               root.getStylesheets().add(getClass().getResource("/style/style1.css").toExternalForm());// Appliquer le CSS


                FilmController filmController = loader.getController();
               filmController.setutilisateurconnecte(utilisateurconnecte);
                filmController.setUserRole(role);


                filmController.addcuruser(getcuruser());


                Seance selectedseance = filmController.seanceTable.getSelectionModel().getSelectedItem();
                if(selectedseance!=null){
                SeanceController seanceController=new SeanceController();
                int nombrebillets=seanceController.getNombrebilletssaisie();



                Reservation reservcuruser = new Reservation(getcuruser(), selectedseance,nombrebillets);
                dataManager.addReservation(reservcuruser);}


                Stage stage = new Stage();
                stage.setTitle("UserView");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


