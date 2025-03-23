package org.example.appjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("connecter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 550);

        scene.getStylesheets().add(getClass().getResource("/style/styleconnecter.css").toExternalForm());
        stage.setTitle("Cinema GHIBLI");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}