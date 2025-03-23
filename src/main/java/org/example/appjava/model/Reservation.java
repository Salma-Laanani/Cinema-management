package org.example.appjava.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Reservation {
    Utilisateur user;
    Seance seance;
   private  IntegerProperty nombreBillets;

   public Reservation(Utilisateur user,Seance seance,int nbrbillets){
       this.user=user;
       this.seance= seance;
       this.nombreBillets=new SimpleIntegerProperty(nbrbillets);
   }
   public Utilisateur getUtilisateur(){
       return user;
   }
   public Seance getSeance(){
       return seance;
   }
   public int getNombreBillets(){
       return nombreBillets.get();
   }
   public IntegerProperty nbrbilleProperty(){return this.nombreBillets;}

    public String toString(){
        return " L'utilisateur  "+user+" a reserve une seance de"  +seance+"avec "+nombreBillets;
    }

}
