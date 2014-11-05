package br.com.Dao;

import br.com.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vitor Mesaque
 */

public class AuthenticateDao  
{
    private Connection connection;

    public AuthenticateDao(Connection connection) 
    {
        this.connection = connection;
    }
    
    public User authenticate(String email, String password) 
    {
        try
        {
           String sql = "SELECT * FROM userdb WHERE email = '" + email + "' AND password = '" + password + "'";
           
           Statement stmt = this.connection.createStatement();
           
           ResultSet rs = stmt.executeQuery(sql);
           
           if (rs.next()) 
           {
                User user =  new User();
                
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setActive(rs.getBoolean("active"));
                user.setProfile(rs.getInt("profile"));
           
                user.setId(rs.getInt("id"));
           
                return user;        
           }
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
        return null;
    }
}