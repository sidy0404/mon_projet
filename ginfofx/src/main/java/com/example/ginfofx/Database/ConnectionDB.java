package com.example.ginfofx.Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TODO version de configuration avec postgres
public class ConnectionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/gestion_materiel";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

//TODO version de configuration avec mysql
/*public class ConnectionDB {
    // Remplacer l'URL par celle de MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_materiel";
    private static final String USER = "root";  // Par défaut, utilisateur root
    private static final String PASSWORD = "root";  // Remplacer par votre mot de passe MySQL

    public static Connection connect() throws SQLException {
        // Charger le driver MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trouvé !");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}*/
