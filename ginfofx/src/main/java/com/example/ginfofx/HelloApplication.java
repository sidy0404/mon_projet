package com.example.ginfofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Charger l'FXML de la page d'accueil
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/ginfofx/view/accueil.fxml"));
        Parent root = fxmlLoader.load();

        // Obtenir la taille de l'écran
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getVisualBounds().getWidth();
        double screenHeight = screen.getVisualBounds().getHeight();

        // Calculer la taille de la fenêtre comme 90% de l'écran
        double windowWidth = screenWidth * 0.9;
        double windowHeight = screenHeight * 0.9;

        // Créer une nouvelle scène avec la racine chargée
        Scene scene = new Scene(root, windowWidth, windowHeight);

        // Ajouter les feuilles de style à la scène
        scene.getStylesheets().add(getClass().getResource("/com/example/ginfofx/css/accueil.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/ginfofx/css/style.css").toExternalForm());

        // Configurer la fenêtre (stage)
        stage.setTitle("Gestion de Matériel Informatique");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
