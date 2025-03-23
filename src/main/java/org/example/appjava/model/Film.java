package org.example.appjava.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Film {
    private final SimpleObjectProperty<Image> image;
    private  StringProperty titre;
    private  StringProperty genre;
    private  IntegerProperty duree;
    private  StringProperty description;
    private  ObservableList<Seance> seances;

    public Film(String titre, String genre, int duree, String description,Image image) {
        this.titre = new SimpleStringProperty(titre);
        this.genre = new SimpleStringProperty(genre);
        this.duree = new SimpleIntegerProperty(duree);
        this.description = new SimpleStringProperty(description);
        this.seances= FXCollections.observableArrayList();
        this.image=new SimpleObjectProperty<>(image);
    }
    public Image getImage() {
        return image.get();
    }

    public SimpleObjectProperty<Image> imageProperty() {
        return image;
    }

    public void setImage(Image image) {
        this.image.set(image);
    }

    /*public Film(String titre) {
        this.titre = new SimpleStringProperty(titre);
        this.genre = new SimpleStringProperty("");
        this.duree = new SimpleIntegerProperty(0);
        this.description = new SimpleStringProperty("");
        this.seances= FXCollections.observableArrayList();

    }*/

    // Getters pour les propriétés JavaFX
    public String getTitre() {
        return titre.get();
    }

    public StringProperty titreProperty() {
        return titre;
    }
  public void setTitre(StringProperty titre){
        this.titre=titre;
  }

    public String getGenre() {
        return genre.get();
    }


    public StringProperty genreProperty() {
        return genre;
    }
    public void setGenre(StringProperty genre){
        this.genre=genre;
    }


    public int getDuree() {
        return duree.get();
    }

    public IntegerProperty dureeProperty() {
        return duree;
    }
    public void setDuree(IntegerProperty duree){
        this.duree=duree;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(StringProperty desc){
        this.description=desc;
    }

    public ObservableList<Seance> getSeances() {
        return seances;
    }
    public void addSeance(Seance seance){
        seances.add(seance);
    }
    public void removeSeance(Seance seance){
        seances.remove(seance);
    }
    @Override
    public String toString() {
        return "le film est de titre " + titre.get() + ", de genre " + genre.get() + ", de durée " + duree.get() + " et de description " + description.get();
    }
}
