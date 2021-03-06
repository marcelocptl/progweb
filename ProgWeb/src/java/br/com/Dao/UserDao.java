package br.com.Dao;

import br.com.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vitor Mesaque
 */
public class UserDao implements GenericDao<User> {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int save(User user) {
        
        int id = -1;
        
        try {

            if (user.getPassword() != null) {
                user.setPassword("'" + user.getPassword() + "'");
            }

            String sql = "INSERT INTO userdb (name,email,active,password,profile) VALUES ('" + user.getName() + "','" + user.getEmail() + "','" + user.getActive() + "', " + user.getPassword() + " ,'" + user.getProfile() + "')";

            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.executeUpdate();
            
            ResultSet rsID = stmt.getGeneratedKeys();  

            if(rsID.next())  
                id = rsID.getInt("id");            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return id;
    }

    @Override
    public void update(User user) {

        try {
            String sql = "UPDATE userdb SET name = '" + user.getName() + "', email = '" + user.getEmail() + "', active = '" + user.getActive() + "', profile = '" + String.valueOf(user.getProfile()) + "'  WHERE id = " + user.getId();

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            String sql = "DELETE FROM userdb WHERE id = '" + id + "'";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getById(int id) {
        try {
            String sql = "SELECT * FROM userdb WHERE id = '" + id + "'";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setActive(rs.getBoolean("active"));
                user.setId(rs.getInt("id"));
                user.setProfile(rs.getInt("profile"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public User authenticate(String email) {
        try {

            String sql = "SELECT * FROM userdb WHERE email = '" + email + "'";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return new User();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> list() {

        try {
            String sql = "SELECT * FROM userdb ORDER BY name ASC";

            Statement stmt = this.connection.createStatement();

            ArrayList<User> users = new ArrayList<>();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setActive(rs.getBoolean("active"));
                user.setId(rs.getInt("id"));
                user.setProfile(rs.getInt("profile"));

                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePass(User user) {
        try {

            String sql = "UPDATE userdb SET password = '" + user.getPassword() + "' WHERE id = " + user.getId();

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
