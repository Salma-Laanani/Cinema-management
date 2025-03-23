package org.example.appjava.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.appjava.model.Film;
import org.example.appjava.model.Reservation;
import org.example.appjava.model.Seance;
import org.example.appjava.model.Utilisateur;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class FilmController {
    private Utilisateur utilisateurconnecte;
    public FilmController(Utilisateur utilisateurconnecte){
        this.utilisateurconnecte=utilisateurconnecte;
    }
@FXML
private Button adminbutton;
    @FXML
    private Button adminbutton2;
    @FXML
    private Button adminbutton3;
    @FXML
   private TableView<Film> FilmTable;

    private DataManager dataManager = DataManager.getInstance();


    public void setutilisateurconnecte(Utilisateur utilisateur){
        this.utilisateurconnecte=utilisateur;
    }

    @FXML
    private TableColumn<Film,String> titlecolumn;
    @FXML
    private TableColumn<Film,String> genrecolumn;
    @FXML
    private TableColumn<Film,Integer> dureecolumn;
    @FXML
    private TableColumn<Film,String> desccolumn;
    @FXML
    private TableColumn<Film,Image> imagecolumn;

    @FXML
    private TextField searchField;

    @FXML
    TableView<Seance> seanceTable;
    @FXML
    private TableColumn<Seance,String> filmColumn;
    @FXML
    private TableColumn<Seance,LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Seance,Integer> placesColumn;

    @FXML
    private Tab usertab;
    @FXML
    private Tab seanceTab;
    @FXML
    private Tab reservationTab;
    @FXML
    private TableView<Utilisateur> utilisateurTable;
    @FXML
    private TableColumn<Utilisateur,String> nomColumn;
    @FXML
    private TableColumn<Utilisateur,String> prenomColumn;
    @FXML
    private TableColumn<Utilisateur,String> emailColumn;


    private String userrole;

    @FXML
    private TableView<Reservation>  reservationTable;
    @FXML
    private TableColumn<Reservation,String>  utilisateurColumn;
    @FXML
    private TableColumn<Reservation,String> seanceColumn;
    @FXML
    TableColumn<Reservation, Integer>    nombrebilletColumn;

  ObservableList<Reservation> allreservations=FXCollections.observableArrayList(new Reservation(new Utilisateur("Oufkir","Hasnaa","hasnaa@gmail.com","1345","user"),new Seance(new Film("mon voisin totoro", "anime", 148, "Un film d'un bus chat.",new Image("file:src/main/resources/org/example/appjava/Images/totoro.jpg")),LocalDateTime.of(2025,1,15,18,30),100),3));
    ObservableList<Utilisateur> users= FXCollections.observableArrayList(new Utilisateur("laanani","salma","salmalaanani@gmail.com","123","admin"),new Utilisateur("Oufkir","Hasnaa","hasnaa@gmail.com","1345","user"));

    ObservableList<Seance> allseances=FXCollections.observableArrayList(new Seance(new Film("mon voisin totoro", "anime", 148, "Un film d'un bus chat.",new Image("file:src/main/resources/org/example/appjava/Images/totoro.jpg")),LocalDateTime.of(2025,1,15,18,30),100),new Seance(new Film("mon voisin totoro", "anime", 148, "Un film d'un bus chat.",new Image("file:/org/example/appjava/Images/totoro.jpg")),LocalDateTime.of(2025, 1, 16, 20, 00),100));

    ObservableList<Film> films= FXCollections.observableArrayList(new Film("mon voisin totoro", "anime", 148, "Un film d'un bus chat.",new Image("file:src/main/resources/org/example/appjava/Images/totoro.jpg")),
            new Film("Kiki la petite sorciere", "anime", 195, "film d'une petite sorciere.",new Image("file:src/main/resources/org/example/appjava/Images/kiki.jpg")),
            new Film("le voyage de chihiro ","anime de studio ghibli",200,"le voyage d'une petite fille a un monde fictif ",new Image("file:src/main/resources/org/example/appjava/Images/chihiro.jpg")), new Film("le secret su monde de Arriety ","anime de studio ghibli",200,"une decouverte du monde de Arriety ",new Image("file:src/main/resources/org/example/appjava/Images/Arriety.jpg")));




    @FXML
    private void applyVisibilityBasedOnRole() {
        if (userrole != null) {
            if (userrole.equals("admin")) {
                adminbutton.setVisible(true);
                adminbutton.setManaged(true);
                adminbutton2.setVisible(true);
                adminbutton2.setManaged(true);
                adminbutton3.setVisible(true);
                adminbutton3.setManaged(true);
                usertab.setDisable(false);
                seanceTab.setDisable(false);
                reservationTab.setDisable(false);


            } else if (userrole.equals("user")) {
                adminbutton.setVisible(false);
                adminbutton.setManaged(false);
                adminbutton2.setVisible(false);
                adminbutton2.setManaged(false);
                adminbutton3.setVisible(false);
                adminbutton3.setManaged(false);
                usertab.setDisable(true);
                seanceTab.setDisable(true);
                reservationTab.setDisable(true);

            }
        }
    }
    public void setUserRole(String role) {
        this.userrole = role;
        applyVisibilityBasedOnRole();
    }

    public void addcuruser(Utilisateur curuser){
        dataManager.addUser(curuser);
            utilisateurTable.refresh();
        }



    public void addreservacuruser(Reservation reservcuruser){
        dataManager.addReservation(reservcuruser);
            reservationTable.refresh();
        }



    @FXML
    private void initialize(){
        if (utilisateurconnecte == null) {
            System.err.println("utilisateurconnecte est null !");
            return;
        }
        applyVisibilityBasedOnRole();



        Film totoro = films.get(0);
        Film sorciere = films.get(1);
        Film chateauCiel=films.get(2);
        Film secretWorld=films.get(3);

        // Ajouter des séances pour le film "Inception"
        totoro.addSeance(new Seance( totoro,LocalDateTime.of(2025,1,15,18,30),100));
        totoro.addSeance(new Seance(totoro, LocalDateTime.of(2025, 1, 16, 20, 00), 150));

        // Ajouter des séances pour le film "Titanic"
        sorciere.addSeance(new Seance(sorciere,LocalDateTime.of(2025, 1, 17, 17, 00), 120));
        sorciere.addSeance(new Seance(sorciere, LocalDateTime.of(2025, 1, 18, 19, 00), 130));
        titlecolumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        genrecolumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        dureecolumn.setCellValueFactory(new PropertyValueFactory<>("duree")) ;
        desccolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        imagecolumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        imagecolumn.setCellFactory(column -> new TableCell<Film, Image>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);
                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    imageView.setFitWidth(100); // Ajustez la largeur de l'image
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });

        FilmTable.setItems(films);



        filmColumn.setCellValueFactory(cellData->new SimpleStringProperty(cellData.getValue().getFilm().getTitre()));
        dateColumn.setCellValueFactory(cellData->cellData.getValue().dateHeureProperty());
        placesColumn.setCellValueFactory(cellData->cellData.getValue().placesDisponiblesProperty().asObject());

     seanceTable.setItems(allseances);
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        utilisateurTable.setItems(dataManager.getUsers());
        utilisateurTable.refresh();


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
    }

    @FXML
    private void onFilmSelected() {
        Film selectedFilm = FilmTable.getSelectionModel().getSelectedItem();
        if (selectedFilm != null) {
            try {
                // Obtient l'instance du contrôleur de séances
                SeanceController seanceController = getSeanceController();
                seanceController.setSelectedFilm(selectedFilm);// passele film sélectionné
                seanceController.setutilisateurconnecte(utilisateurconnecte);
            } catch (IOException e) {
                e.printStackTrace();  // Gérer l'exception (vous pouvez afficher un message ou faire autre chose)
            }
        }
    }

    private SeanceController getSeanceController() throws IOException{
        // Chargez le FXML de la vue Seance
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/appjava/seance-view.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("/style/styleseance.css").toExternalForm());// Charge la vue

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestion des Séances");
        stage.show();
        SeanceController controller = loader.getController();// Récupère le contrôleur

        return controller;
    }

    @FXML
    private void onAddFilm() {
        // Crée une boîte de dialogue pour saisir les informations du nouveau film
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter un film");
        dialog.setHeaderText("Ajouter un nouveau film");
        dialog.setContentText("Veuillez entrer les informations du film:");

        // Ajoute des champs pour chaque propriété du film
        TextField titreField = new TextField();
        TextField genreField = new TextField();
        TextField dureeField = new TextField();
        TextField descField = new TextField();

        GridPane grid = new GridPane();
        grid.add(new Label("Titre:"), 0, 0);
        grid.add(titreField, 1, 0);
        grid.add(new Label("Genre:"), 0, 1);
        grid.add(genreField, 1, 1);
        grid.add(new Label("Durée:"), 0, 2);
        grid.add(dureeField, 1, 2);
        grid.add(new Label("Description:"), 0, 3);
        grid.add(descField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Affiche la boîte de dialogue et attend la réponse de l'utilisateur
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // Crée un nouveau film avec les informations saisies
            Film newFilm = new Film(
                    titreField.getText(),
                    genreField.getText(),
                    Integer.parseInt(dureeField.getText()),
                    descField.getText(),
                    new Image("file:src/main/resources/org/example/appjava/Images/la Coline.jpg") // Image par défaut
            );

            // Vérifie si le film existe déjà dans la liste
            if (!films.contains(newFilm)) {
                films.add(newFilm); // Ajoute le film à la liste
                FilmTable.refresh(); // Rafraîchit la table des films
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Film existant");
                alert.setHeaderText(null);
                alert.setContentText("Le film existe déjà.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void onEditFilm() {
        Film selectedFilm = FilmTable.getSelectionModel().getSelectedItem();
        if (selectedFilm != null) {
            // Crée une boîte de dialogue pour modifier les informations du film
            TextInputDialog dialog = new TextInputDialog(selectedFilm.getTitre());
            dialog.setTitle("Modifier le film");
            dialog.setHeaderText("Modifier les informations du film");
            dialog.setContentText("Veuillez entrer les nouvelles informations:");

            // Ajoute des champs pour chaque propriété du film
            TextField titreField = new TextField(selectedFilm.getTitre());
            TextField genreField = new TextField(selectedFilm.getGenre());
            TextField dureeField = new TextField(String.valueOf(selectedFilm.getDuree()));
            TextField descField = new TextField(selectedFilm.getDescription());

            GridPane grid = new GridPane();
            grid.add(new Label("Titre:"), 0, 0);
            grid.add(titreField, 1, 0);
            grid.add(new Label("Genre:"), 0, 1);
            grid.add(genreField, 1, 1);
            grid.add(new Label("Durée:"), 0, 2);
            grid.add(dureeField, 1, 2);
            grid.add(new Label("Description:"), 0, 3);
            grid.add(descField, 1, 3);

            dialog.getDialogPane().setContent(grid);

            // Affiche la boîte de dialogue et attend la réponse de l'utilisateur
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                // Met à jour les propriétés du film avec les nouvelles valeurs
                selectedFilm.titreProperty().set(titreField.getText()); // Utilise StringProperty
                selectedFilm.genreProperty().set(genreField.getText()); // Utilise StringProperty
                selectedFilm.dureeProperty().set(Integer.parseInt(dureeField.getText())); // Utilise IntegerProperty
                selectedFilm.descriptionProperty().set(descField.getText()); // Utilise StringProperty

                FilmTable.refresh(); // Rafraîchit la table des films
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun film sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un film à modifier.");
            alert.showAndWait();
        }
    }


    @FXML
    private void onDeleteFilm() {
        Film selectedfilm=FilmTable.getSelectionModel().getSelectedItem();
        if(selectedfilm!=null){
            films.remove(selectedfilm);
        }else{

        }
        FilmTable.refresh();


    }
    @FXML
    public void onAddUser() {
        // Crée un nouvel utilisateur
        Utilisateur user1 = new Utilisateur("Laanaya", "Alaae", "LaanayaAlaae@gmail.com.com", "1345", "user");

        // Vérifie si l'utilisateur existe déjà dans la liste
        if (!users.contains(user1)) {
            users.add(user1); // Ajoute l'utilisateur à la liste
            utilisateurTable.refresh(); // Rafraîchit la table des utilisateurs
        } else {
            System.out.println("L'utilisateur existe déjà.");
        }
    }
    @FXML
    public void onEditUser(){
        Utilisateur selecteduser= utilisateurTable.getSelectionModel().getSelectedItem();
        if(selecteduser!=null){
            selecteduser.nomProperty().set("Moundouti");
            selecteduser.prenomProperty().set("Luce");
            selecteduser.emailProperty().set("LuceMoundouti@gmail.com");
        }
        utilisateurTable.refresh();
    }
    @FXML
    public void  onDeleteUser(){
        Utilisateur selecteduser= utilisateurTable.getSelectionModel().getSelectedItem();
        if (selecteduser!=null){
            users.remove(selecteduser);
        }
        utilisateurTable.refresh();
    }


    @FXML
    public void onFiltrer(){
        String startchar=searchField.getText().toLowerCase();

        ObservableList<Film> filterd=films.filtered(film->film.getTitre().startsWith(startchar));
       FilmTable.setItems(filterd);
    }











}
