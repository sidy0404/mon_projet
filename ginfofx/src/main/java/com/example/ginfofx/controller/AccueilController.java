package com.example.ginfofx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;

public class AccueilController {
    @FXML
    private StackPane contentArea;  // Zone où les formulaires seront chargés dynamiquement

    @FXML
    private StackPane backgroundImageContainer;

    @FXML
    private ImageView backgroundImage;

/*
    @FXML
    public void initialize() {
        // Charger l'image
        backgroundImage.setImage(new Image(getClass().getResource("/com/example/ginfofx/img/imgs.png").toExternalForm()));
    }
*/

    private void changeView(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent newView = loader.load();
        contentArea.getChildren().setAll(newView);
    }

    // Charge la vue du formulaire de connexion
    @FXML
    private void loadLoginForm() throws IOException {
        changeView("/com/example/ginfofx/view/loginForm.fxml");
    }

    // Charge la vue du formulaire d'inscription
    @FXML
    private void loadRegisterForm() throws IOException {
        changeView("/com/example/ginfofx/view/registerForm.fxml");
    }

    // Méthode générique pour charger une vue dans le contentArea
    private void loadView(String fxml) {
        System.out.println("fxml : " + fxml);
        URL resource = getClass().getResource("/com/example/ginfofx/view/loginForm.fxml");
        System.out.println("URL du fichier FXML : " + resource);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ginfofx/view/" + fxml));
            Node node = loader.load();
            contentArea.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Impossible de charger la vue : " + fxml);
        }
    }


}
