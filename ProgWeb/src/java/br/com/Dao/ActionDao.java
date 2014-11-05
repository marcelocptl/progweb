/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Dao;

import br.com.model.Action;
import br.com.model.Action;
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
public class ActionDao implements GenericDao<Action> {

    private Connection connection;

    public ActionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Action action) {
        try {
            String sql = "INSERT INTO action (name,description,active) VALUES ('" + action.getName() + "','" + action.getDescription() + "','" + action.getActive() + "')";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Action action) {
         try
        {
           String sql = "UPDATE action SET name = '"+action.getName()+"', description = '"+action.getDescription()+"', active = '" + action.getActive()+ "' WHERE id = " + action.getId();
          System.out.println(sql);
           PreparedStatement stmt = this.connection.prepareStatement(sql);
           
           stmt.executeUpdate();
          
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
    }

    @Override
    public Boolean delete(int id) {
        try
        {
           String sql = "DELETE FROM action WHERE id = '" + id + "'";
           
           Statement stmt = this.connection.createStatement();
           
           stmt.executeQuery(sql); 
           
           return true;
          
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
        return false;
    }

    @Override
    public ArrayList<Action> list() {
        try {
            String sql = "SELECT * FROM action";

            Statement stmt = this.connection.createStatement();

            ArrayList<Action> actions = new ArrayList<>();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Action action = new Action(rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));

                action.setId(rs.getInt("id"));

                actions.add(action);

            }

            return actions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Action getById(int id) {
          try{
           String sql = "SELECT * FROM action WHERE id = '" + id + "'";
           
           Statement stmt = this.connection.createStatement();
           
           ResultSet rs = stmt.executeQuery(sql);
           
           if (rs.next()) 
           {
                Action action =  new Action(rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
           
                action.setId(rs.getInt("id"));
           
                return action;        
           }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return null;
    
    }

    @Override
    public void updatePass(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
