/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author vitor
 */
public class LogRegister {
    
    private static LogRegister instance;
    
    private Connection connection;
    
    private LogRegister()
    {
        try
        {
            ConnectionFactory conn = ConnectionFactory.singleton();
            this.connection =  conn.getConnection() ;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static LogRegister singleton()
    {
        if(instance == null)
            instance = new LogRegister();
        
        return instance;
    }
    
    public void toLog(String module, String action, String message, int userId)
    {
        try
        {
           String sql = "INSERT INTO log (module,action,message,userdb) VALUES (?,?,?,?)";

           PreparedStatement stmt = this.connection.prepareStatement(sql);
           
           stmt.setString(1,module);
           stmt.setString(2,action);
           stmt.setString(3,message);
           stmt.setLong(4,userId);
           
           stmt.executeUpdate(); 
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
    }
}
