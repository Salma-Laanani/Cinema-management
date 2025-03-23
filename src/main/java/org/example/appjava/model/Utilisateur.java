package org.example.appjava.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Utilisateur {
   private  StringProperty nom;
   private  StringProperty prenom;
     private  StringProperty email;
     private StringProperty password;
     private  StringProperty role;
  ObservableList<Reservation> mesreservations;

  public Utilisateur (String nom,String prenom,String email, String password,String role){
      this.nom=new SimpleStringProperty(nom);
      this.prenom=new SimpleStringProperty(prenom);
      this.email=new SimpleStringProperty(email);
      this.password=new SimpleStringProperty(password);
      this.role=new SimpleStringProperty(role);

      this.mesreservations=FXCollections.observableArrayList();
  }
  public String getNom(){
      return this.nom.get();
  }
    public String getPrenom(){
        return this.prenom.get();
    }
    public String getEmail(){
        return this.email.get();
    }
    public String getRole(){
      return this.role.get();
    }

    public String getPassword(){
        return this.password.get();
    }

    public StringProperty nomProperty(){
      return nom;
    }
    public StringProperty prenomProperty(){
        return prenom;
    }
    public StringProperty emailProperty(){
        return email;
    }
public StringProperty roleProperty(){
      return role;
}
    public StringProperty passProperty(){
        return password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return email.equals(that.email); // Comparaison basée sur l'email
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }


    public void ajouterReservation(Reservation reservation){
        for (int i = 0; i <mesreservations.size(); i++) {
            if(!mesreservations.contains(reservation) ){
                mesreservations.add(reservation);
            }
            else{
                System.out.println("La reservation deja existe");
            }

        }
    }
   public ObservableList<Reservation> getReservation(){

        for (int i = 0; i < mesreservations.size() ; i++) {
            mesreservations.get(i);
        }
        return mesreservations;
    }
}

