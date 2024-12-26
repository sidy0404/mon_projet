package com.example.ginfofx.controller;

import com.example.ginfofx.Database.Repository;
import com.example.ginfofx.Model.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Repository repository = new Repository();

    public void handleLoginAction() throws IOException {
        String login = usernameField.getText();
        String password = passwordField.getText();

        Account account = repository.verifyLoginAndPassword(login, password);

        if (account != null) {
            // Connexion réussie
            System.out.println("Connexion réussie !");

            // Chargez la HomePageAdminController et passez l'utilisateur connecté au contrôleur
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ginfofx/view/HomePageAdmin.fxml"));
            Parent root = loader.load();
            HomePageAdminController homePageAdminController = loader.getController();
            homePageAdminController.setLoggedInAccount(account);  // Passe l'utilisateur connecté au contrôleur

            // Affichez la nouvelle scène (page d'accueil)
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
        }
    }

}
