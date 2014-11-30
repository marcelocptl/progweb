package br.com.Dao;


import br.com.model.Action;
import br.com.model.Module;
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
public class ModuleDao implements GenericDao<Module> 
{
    private Connection connection;

    public ModuleDao(Connection connection) {
        this.connection = connection;
    }

    public int save(Module module) {
        
        try
        {
           String sql = "INSERT INTO module (name,description,active) VALUES ('"+module.getName()+"','"+module.getDescription()+"','"+module.getActive()+"')";
          
           PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           
           stmt.executeUpdate();
           
           ResultSet id = stmt.getGeneratedKeys();    
           
           id.next();  
           
           int lastId = id.getInt(1);
           
           for(Action action : module.getActions())
           {
                String sqlAux = "INSERT INTO module_action (action,module) VALUES ('"+action.getId()+"','"+lastId+"')";

                PreparedStatement stmtAux = this.connection.prepareStatement(sqlAux);

                stmtAux.executeUpdate();
           }
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        }
        
        return -1;
    }

    @Override
    public ArrayList<Module> list() {

        try
        {
           String sql = "SELECT * FROM module ORDER BY id";
           
           Statement stmt = this.connection.createStatement();
           
           ArrayList<Module> modules = new ArrayList<> ();
           
           ResultSet rs = stmt.executeQuery(sql);
           
           while(rs.next())
           {      
                Module module =  new Module(rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
           
                module.setId(rs.getInt("id"));
                
                String sqlAux = "SELECT * FROM module_action INNER JOIN action ON action.id = module_action.action WHERE module = '" + module.getId() + "'";
           
                Statement stmtAux = this.connection.createStatement();
           
                ResultSet rsAux = stmtAux.executeQuery(sqlAux);

                ArrayList<Action> actions = new ArrayList<> ();
                
                while(rsAux.next())
                { 
                    Action action = new Action(rsAux.getString("name"), rsAux.getString("description"), rsAux.getBoolean("active"));
               
                    action.setId(rsAux.getInt("action"));
               
                    actions.add(action);   
                }
                
                module.setActions(actions);
                
                modules.add(module);
           }
           
           return modules;
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
        return null;
    }
    @Override
    public Module getById(int id)   
    {
        
        try
        {
           String sql = "SELECT * FROM module WHERE id = '" + id + "'";
           
           Statement stmt = this.connection.createStatement();
           
           ResultSet rs = stmt.executeQuery(sql);
           
           if (rs.next()) 
           {
                Module module =  new Module(rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
           
                module.setId(rs.getInt("id"));
                
                String sqlAux = "SELECT * FROM module_action INNER JOIN action ON action.id = module_action.action WHERE module = '" + module.getId() + "'";
           
                Statement stmtAux = this.connection.createStatement();
           
                ResultSet rsAux = stmt.executeQuery(sqlAux);

                ArrayList<Action> actions = new ArrayList<> ();
                
                while(rsAux.next())
                { 
                    Action action = new Action(rsAux.getString("name"), rsAux.getString("description"), rsAux.getBoolean("active"));
               
                    action.setId(rsAux.getInt("action"));
               
                    actions.add(action);   
                }
                
                module.setActions(actions);
                
                return module;        
           }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public void update(Module module) {
 
        try
        {
           String sql = "UPDATE module SET name = '"+module.getName()+"', description = '"+module.getDescription()+"', active = '" + module.getActive()+ "' WHERE id = " + module.getId();
          
           PreparedStatement stmt = this.connection.prepareStatement(sql);
           
           stmt.executeUpdate();
          
           for(Action action : module.getActions())
           {
                /*String sqlDel = "DELETE FROM module_action WHERE module = '"+ module.getId() +"'";

                PreparedStatement stmtDel = this.connection.prepareStatement(sqlDel);

                stmtDel.executeUpdate();
                */
                String sqlAux = "INSERT INTO module_action (action,module) VALUES ('"+action.getId()+"','"+module.getId()+"')";

                PreparedStatement stmtAux = this.connection.prepareStatement(sqlAux);

                stmtAux.executeUpdate();
           }

        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
    }

    @Override
    public Boolean delete(int id) 
    {
       try
        {
            String sql = "DELETE FROM module_action WHERE module = '" + id + "'";
           
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();
            
            sql = "DELETE FROM module WHERE id = '" + id + "'";
           
            stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();
           
           return true;
          
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
        return false;
    }

    @Override
    public void updatePass(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public User authenticate(String email){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
}