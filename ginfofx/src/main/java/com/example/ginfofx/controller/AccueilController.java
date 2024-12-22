package com.example.ginfofx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AccueilController {

    @FXML
    private VBox loginForm; // Formulaire de connexion

    @FXML
    private Button btnLogin; // Bouton "Se connecter"

    @FXML
    private Button btnCreateAccount; // Bouton "Créer un compte"

    @FXML
    private TextField usernameField; // Champ de texte pour le nom d'utilisateur

    @FXML
    private PasswordField passwordField; // Champ de texte pour le mot de passe

    // Afficher le formulaire de login lorsque l'utilisateur clique sur "Se connecter"
    @FXML
    private void handleLoginButton() {
        loginForm.setVisible(true); // Rendre le formulaire de login visible
    }

    // Méthode appelée lors du clic sur "Se connecter" dans le formulaire
    @FXML
    private void handleSubmitLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Logique de validation de l'authentification
        System.out.println("Nom d'utilisateur: " + username);
        System.out.println("Mot de passe: " + password);

        // Ajouter ici la logique pour valider le login et, par exemple, passer à une autre vue
    }
}

