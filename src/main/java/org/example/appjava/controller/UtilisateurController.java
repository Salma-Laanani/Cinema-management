package org.example.appjava.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.appjava.model.Utilisateur;


public class UtilisateurController {
@FXML
    private TableView<Utilisateur> utilisateurTable;
@FXML
    private TableColumn<Utilisateur,String> nomColumn;
@FXML
private TableColumn<Utilisateur,String> prenomColumn;
@FXML
private TableColumn<Utilisateur,String> emailColumn;

ObservableList<Utilisateur> users= FXCollections.observableArrayList(new Utilisateur("laanani","salma","salmalaanani@gmail.com","123","admin"));
    public void initialize(){

    nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

    utilisateurTable.setItems(users);
}



}
