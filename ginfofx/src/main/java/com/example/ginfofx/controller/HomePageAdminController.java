package com.example.ginfofx.controller;

import com.example.ginfofx.Database.Repository;
import com.example.ginfofx.Model.Account;
import com.example.ginfofx.Model.Role;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageAdminController {

    @FXML
    private Label userProfileLabel;  // Label pour afficher le profil de l'utilisateur connecté
    @FXML
    private TableView<Account> userTable;  // Table pour afficher la liste des utilisateurs
    @FXML
    private TableColumn<Account, String> idColumn;  // Colonne pour l'ID
    @FXML
    private TableColumn<Account, String> loginColumn;  // Colonne pour le login
    @FXML
    private TableColumn<Account, String> roleColumn;  // Colonne pour le rôle
    @FXML
    private TableColumn<Account, String> prenomColumn;  // Colonne pour le prenom
    @FXML
    private TableColumn<Account, String> nomColumn;  // Colonne pour le nom
    @FXML
    private TableColumn<Account, String> mailColumn;  // Colonne pour le mail
    @FXML
    private TableColumn<Account, String> telephoneColumn;  // Colonne pour le telephone
    @FXML
    private TableColumn<Account, String> actionsColumn;  // Colonne pour les actions CRUD
    @FXML
    private Label userListSectionLabel;  // Label pour la section des utilisateurs

    private Account loggedInAccount;  // Compte de l'utilisateur connecté

    private Repository repository = new Repository();

    // Initialisation de la page
    public void initialize() {
        // Affiche les informations de profil pour l'utilisateur connecté
        if (loggedInAccount != null) {
            userProfileLabel.setText("Nom: " + loggedInAccount.getPrenom() + " " + loggedInAccount.getNom() +
                    "\nRôle: " + loggedInAccount.getRole().name());
        }

        // Si l'utilisateur est un administrateur, on affiche la liste des utilisateurs et permet les actions CRUD
        if (loggedInAccount != null && loggedInAccount.getRole() == Role.Admin) {
            userListSectionLabel.setVisible(true);
            userTable.setVisible(true);
            loadUserTable();
        }
    }

    // Cette méthode est appelée lors du clic sur le bouton "Afficher les utilisateurs"
    @FXML
    private void handleShowUsers() {
        // Rendre la section visible
        userListSectionLabel.setVisible(true);
        userTable.setVisible(true);

        // Charger et afficher les utilisateurs (vous devez déjà avoir une méthode pour cela)
        loadUserTable();
    }

    // Charger la liste des utilisateurs dans la table
    private void loadUserTable() {
        ObservableList<Account> users = FXCollections.observableArrayList(repository.findAll());

        //idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        loginColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLogin()));
        roleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole().name()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        mailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMail()));
        telephoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephone()));

        // Définir les actions CRUD (par exemple, un bouton pour supprimer un utilisateur)
        actionsColumn.setCellFactory(column -> new TableCell<Account, String>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> handleDeleteUser(getTableRow().getItem()));
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        userTable.setItems(users);
    }

    // Méthode pour gérer la suppression d'un utilisateur
    private void handleDeleteUser(Account account) {
        // Vérifiez si l'utilisateur connecté est bien un admin
        if (loggedInAccount.getRole() == Role.Admin) {
            repository.delete(account.getId());
            loadUserTable();  // Rafraîchir la liste après suppression
            System.out.println("Utilisateur supprimé : " + account.getLogin());
        } else {
            System.out.println("Action non autorisée. Seul un administrateur peut supprimer des utilisateurs.");
        }
    }

    // Méthode pour gérer la déconnexion
    @FXML
    private void handleLogout() throws IOException {
        // Logique de déconnexion (par exemple, retour à la page de connexion)
        System.out.println("Déconnexion réussie.");

        // Charger la page d'accueil (accueil.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ginfofx/view/accueil.fxml"));  // Assurez-vous que le chemin est correct
        Parent root = loader.load();

        // Récupérer la scène de la fenêtre actuelle (la fenêtre principale)
        Stage stage = (Stage) userProfileLabel.getScene().getWindow();

        // Créer une nouvelle scène avec la racine chargée
        Scene scene = new Scene(root);

        // Ajouter les feuilles de style à la nouvelle scène
        scene.getStylesheets().add(getClass().getResource("/com/example/ginfofx/css/accueil.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/com/example/ginfofx/css/style.css").toExternalForm());

        // Changer la scène pour la page d'accueil
        stage.setScene(scene);
        stage.show();  // Afficher la nouvelle scène
    }


    // Méthode pour définir l'utilisateur connecté
    public void setLoggedInAccount(Account account) {
        this.loggedInAccount = account;
    }
}

