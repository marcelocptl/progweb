package br.com.Dao;

import br.com.business.ActionBO;
import br.com.business.ModuleBO;
import br.com.business.ProfileBO;
import br.com.model.Action;
import br.com.model.Module;
import br.com.model.Permission;
import br.com.model.PermissionCollection;
import br.com.model.Profile;
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
public class PermissionDao {

    private Connection connection;

    public PermissionDao(Connection connection) {
        this.connection = connection;
    }

    public void save(ArrayList<Permission> permissions, int id) {
        try {
            
            this.delete(id);

            for (int i = 0; i < permissions.size(); i++) 
            {
                String sql = "INSERT INTO profile_module_action (profile, module, action) VALUES ("+permissions.get(i).getProfile()+","+ permissions.get(i).getModule()+","+permissions.get(i).getAction()+");";

                PreparedStatement stmt = this.connection.prepareStatement(sql);

                stmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
        try {
            
            String sql = "DELETE FROM profile_module_action WHERE profile = "+ id;

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Permission> list() {

        try
        {
           ProfileBO profileBo = new ProfileBO();
           ModuleBO moduleBo = new ModuleBO();
           ActionBO actionBo = new ActionBO();              
            
           String sql = "SELECT * FROM profile_module_action";
           
           Statement stmt = this.connection.createStatement();
           
           PermissionCollection<Permission> permissions = new PermissionCollection ();
           
           ResultSet rs = stmt.executeQuery(sql);
           
           while(rs.next())
           {      
               Permission permission = new Permission();
               
               permission.setProfile(rs.getInt("profile"));
               Profile p = profileBo.getProfile(permission.getProfile());
               permission.setProfileName(p.getName());
               
               permission.setModule(rs.getInt("module"));
               Module m = moduleBo.getModule(permission.getModule());
               permission.setModuleName(m.getName());
               
               permission.setAction(rs.getInt("action"));
               Action a = actionBo.getAction(permission.getAction());
               permission.setActionName(a.getName());
               
               permissions.add(permission);
           }
           
           return permissions;
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
        return null;
    }
    
    public ArrayList<Permission> profilePermissions(int profile) {

        try
        {
           ProfileBO profileBo = new ProfileBO();
           ModuleBO moduleBo = new ModuleBO();
           ActionBO actionBo = new ActionBO();                       
           
           String sql = "SELECT * FROM profile_module_action WHERE profile = " + profile;
           
           Statement stmt = this.connection.createStatement();
           
           PermissionCollection<Permission> permissions = new PermissionCollection ();
           
           ResultSet rs = stmt.executeQuery(sql);
           
           while(rs.next())
           {      
               Permission permission = new Permission();
               
               permission.setProfile(rs.getInt("profile"));
               Profile p = profileBo.getProfile(permission.getProfile());
               permission.setProfileName(p.getName());
               
               permission.setModule(rs.getInt("module"));
               Module m = moduleBo.getModule(permission.getModule());
               permission.setModuleName(m.getName());
               
               permission.setAction(rs.getInt("action"));
               Action a = actionBo.getAction(permission.getAction());
               permission.setActionName(a.getName());
               
               permissions.add(permission);
           }
           
           return permissions;
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
        return null;
    }    

}