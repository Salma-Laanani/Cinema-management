module org.example.appjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens org.example.appjava to javafx.fxml;
    opens org.example.appjava.controller to javafx.fxml;
    opens org.example.appjava.model to javafx.base;
    exports org.example.appjava;
    exports org.example.appjava.controller;



}