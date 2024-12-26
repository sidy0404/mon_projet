package com.example.ginfofx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HelloController {
    private StackPane contentArea;

    // Cette méthode sera utilisée pour changer dynamiquement les vues
    public void changeView(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent newView = loader.load();
        contentArea.getChildren().setAll(newView);  // Remplacer la vue actuelle par la nouvelle
    }

    public void initialize() throws IOException {
        // Par défaut, on charge la page d'accueil
        changeView("/com/example/ginfofx/view/Accueil.fxml");
    }
}
