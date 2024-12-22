package com.example.ginfofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFormController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    // Méthode appelée lors du clic sur "Se connecter" dans le formulaire
    @FXML
    private void handleSubmitLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Logique de validation de l'utilisateur

        System.out.println("Nom d'utilisateur: " + username);
        System.out.println("Mot de passe: " + password);
    }
}
