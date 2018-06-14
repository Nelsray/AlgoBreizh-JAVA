package algobreizh.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL de connexion

    private String url = "jdbc:mysql://localhost/algoBreizhdb_Java";
    // Nom du user
    private String user = "root";
    // Mot de passe de l'utilisateur
    private String passwd = "root";
    // Objet Connection
    private static Connection connect;
    // Constructeur privÈ

    private DatabaseConnection() {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // MÈthode d'accËs au singleton
    public static Connection getInstance() {
        if (connect == null) {
            new DatabaseConnection();
        }

        return connect;
    }
}
