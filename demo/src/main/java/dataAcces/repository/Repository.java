package dataAcces.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Repository {
    private final String connectionUrl;
    private final String user;
    private final String pass;    

    public Repository(){
        connectionUrl = "jdbc:mysql://localhost:3306/Pizzeria";
        user = "root";
        pass = "Root1234!";
    }
    
    protected Connection GetConnection(){
        try {
            return DriverManager.getConnection(connectionUrl, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
