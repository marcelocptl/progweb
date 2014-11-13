
package br.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private Connection conn;
    
    private static ConnectionFactory instance;
    
    private ConnectionFactory() throws SQLException {
    
        try
        {
            Class.forName("org.postgresql.Driver");
            
            this.conn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost/webapp","postgres","postgres");
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex.getMessage());
        } 
    
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public static ConnectionFactory singleton() throws SQLException {
      
        if (instance == null)
         instance = new ConnectionFactory();
      
      return instance;
    }    
    
}