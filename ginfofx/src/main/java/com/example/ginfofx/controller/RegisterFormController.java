package com.example.ginfofx.controller;

import com.example.ginfofx.Database.Repository;
import com.example.ginfofx.Model.Account;
import com.example.ginfofx.Model.Role;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;

public class RegisterFormController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField prenom;

    @FXML
    private TextField nomField;

    @FXML
    private TextField mail;

    @FXML
    private TextField telephone;

    @FXML
    private void handleRegister() {
        // Récupération des valeurs des champs
        String login = loginField.getText();
        String password = passwordField.getText();
        String prenomValue = prenom.getText();
        String nomValue = nomField.getText();
        String mailValue = mail.getText();
        String telephoneValue = telephone.getText();
        String roleString = roleComboBox.getValue();  // Récupère la valeur du rôle

        // Vérification que tous les champs sont remplis
        if (login.isEmpty() || password.isEmpty() || prenomValue.isEmpty() || nomValue.isEmpty()
                || mailValue.isEmpty() || telephoneValue.isEmpty() || roleString == null) {
            System.out.println("Veuillez remplir tous les champs.");
            return ;
        }

        // Conversion de la valeur du rôle en enum
        try {
            // Convertit la chaîne en constante de l'énumération
            Role role = Role.valueOf(roleString);


            // Création d'un nouvel objet Account avec les valeurs saisies
            Account newAccount = new Account(prenomValue, nomValue, mailValue, telephoneValue, login, password, role);


            // Affichage du succès
            System.out.println("Inscription réussie !");
            System.out.println("Utilisateur: " + newAccount.getId());
            System.out.println("Utilisateur: " + login);
            System.out.println("Prénom: " + prenomValue);
            System.out.println("Nom: " + nomValue);
            System.out.println("E-mail: " + mailValue);
            System.out.println("Téléphone: " + telephoneValue);
            System.out.println("Rôle: " + role);

            // Création d'un nouvel objet Repository pour acceder à la methode saveUserToDatabase()
            Repository repository = new Repository();

            // Enregistre le nouveau compte dans la base de données
            repository.saveUserToDatabase(newAccount);

            System.out.println("******Inscription réussie pour******** : " + newAccount);

        } catch (IllegalArgumentException e) {
            System.out.println("Rôle invalide: " + roleString); // Gère les rôles invalides
        }

    }

}
