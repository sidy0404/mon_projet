package com.example.ginfofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);

        // Appliquer le fichier CSS à la scène
        scene.getStylesheets().add(getClass().getResource("/com/example/ginfofx/css/accueil.css").toExternalForm());


        stage.setTitle("Gestion de Matériel Informatique");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
