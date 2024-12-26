package com.example.ginfofx.Database;

import com.example.ginfofx.Model.Account;
import com.example.ginfofx.Model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.ginfofx.Utils.checkPassword;
import static com.example.ginfofx.Utils.hashPassword;

public class Repository {

    public void saveUserToDatabase(Account account) {
        System.out.println("Saving user to database : "+ account);
        String insertQuery = "INSERT INTO users (id, login, password, prenom, nom, mail, telephone, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, account.getId());
            stmt.setString(2, account.getLogin());
            stmt.setString(3, hashPassword(account.getPassword()));
            stmt.setString(4, account.getPrenom());
            stmt.setString(5, account.getNom());
            stmt.setString(6, account.getMail());
            stmt.setString(7, account.getTelephone());
            stmt.setString(8, account.getRole().name());

            stmt.executeUpdate();

            System.out.println("Utilisateur enregistré dans la base de données.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement de l'utilisateur.");
        }
    }

    public Account getById(String id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapToAccount(rs);  // Fonction pour transformer un ResultSet en un objet Account
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération de l'utilisateur par ID.");
        }
        return null;
    }

    public Account getByEmail(String email) {
        String query = "SELECT * FROM users WHERE mail = ?";
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapToAccount(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération de l'utilisateur par email.");
        }
        return null;
    }

    public Account getByUsername(String username) {
        String query = "SELECT * FROM users WHERE login = ?";
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapToAccount(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération de l'utilisateur par login.");
        }
        return null;
    }

    public List<Account> getByRole(Role role) {
        String query = "SELECT * FROM users WHERE role = ?";
        List<Account> accounts = new ArrayList<>();
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, role.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                accounts.add(mapToAccount(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des utilisateurs par rôle.");
        }
        return accounts;
    }

    public Account verifyLoginAndPassword(String login, String password) {
        String query = "SELECT * FROM users WHERE login = ?";
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                // Vérifier si le mot de passe correspond au mot de passe stocké en utilisant BCrypt
                if (checkPassword(password, storedPassword)) {
                    return mapToAccount(rs);
                } else {
                    System.out.println("Mot de passe incorrect");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la vérification du login et mot de passe.");
        }
        return null;  // Si aucun utilisateur n'est trouvé ou si le mot de passe ne correspond pas
    }

    public void delete(String id) {
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Utilisateur avec ID " + id + " supprimé.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression de l'utilisateur.");
        }
    }

    public void update(Account account) {
        String updateQuery = "UPDATE users SET login = ?, password = ?, prenom = ?, nom = ?, mail = ?, telephone = ?, role = ? WHERE id = ?";
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, account.getLogin());
            stmt.setString(2, hashPassword(account.getPassword()));  // Ne jamais stocker de mot de passe en clair
            stmt.setString(3, account.getPrenom());
            stmt.setString(4, account.getNom());
            stmt.setString(5, account.getMail());
            stmt.setString(6, account.getTelephone());
            stmt.setString(7, account.getRole().name());
            stmt.setString(8, account.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Utilisateur avec ID " + account.getId() + " mis à jour.");
            } else {
                System.out.println("Aucun utilisateur trouvé avec l'ID " + account.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la mise à jour de l'utilisateur.");
        }
    }

    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection conn = ConnectionDB.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                accounts.add(mapToAccount(rs));
            }
            System.out.println("query : " + accounts);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération de tous les utilisateurs.");
        }
        return accounts;
    }



    private Account mapToAccount(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String login = rs.getString("login");
        String password = rs.getString("password");
        String prenom = rs.getString("prenom");
        String nom = rs.getString("nom");
        String mail = rs.getString("mail");
        String telephone = rs.getString("telephone");
        Role role = Role.valueOf(rs.getString("role"));  // Convertit le rôle en enum

        return new Account(id, prenom, nom, mail, telephone, login, password, role);
    }
























    /**
     CREATE TABLE users (
        id VARCHAR(255) PRIMARY KEY NOT NULL,
        login VARCHAR(255) UNIQUE NOT NULL,
        password VARCHAR(255) NOT NULL,
        prenom VARCHAR(255),
        nom VARCHAR(255),
        mail VARCHAR(255),
        telephone VARCHAR(255),
        role VARCHAR(50)
);
     */
}
