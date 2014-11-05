/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Dao;

import br.com.model.Log;
import br.com.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ricardo.marcacini
 */
public class LogDao {

    private Connection connection;

    public LogDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Log> list() {
        try {
            String sql = "SELECT * FROM log INNER JOIN userdb ON userdb.id = log.userdb";

            Statement stmt = this.connection.createStatement();

            ArrayList<Log> logs = new ArrayList<>();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                
                Log log = new Log();
                User user = new User();
                
                user.setName(rs.getString("name"));
                log.setId(rs.getInt("id"));
                log.setModule(rs.getString("module"));
                log.setAction(rs.getString("action"));
                log.setDate(rs.getDate("date"));
                log.setMessage(rs.getString("message"));
                log.setUser(user);
                
                logs.add(log);
            }

            return logs;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }   
}
