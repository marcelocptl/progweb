
package br.com.jdbc.factory;

import br.com.Dao.ActionDao;
import br.com.Dao.AuthenticateDao;
import br.com.Dao.DaoFactory;
import br.com.Dao.UserDao;
import br.com.Dao.GenericDao;
import br.com.Dao.LogDao;
import br.com.Dao.ProfileDao;
import br.com.Dao.ModuleDao;
import br.com.Dao.PermissionDao;
import br.com.model.Action;
import br.com.model.Profile;
import br.com.model.Module;
import br.com.model.User;
import br.com.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory{

    private Connection connection;

    public JDBCDaoFactory() 
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
    
    @Override
    public GenericDao<User> getUserDao() 
    {
        return new UserDao(this.connection);
    }
    
    @Override
    public GenericDao<Profile> getProfileDao() 
    {
        return new ProfileDao(this.connection);
    }
    
    @Override
    public AuthenticateDao getAuthenticateDao() 
    {
        return new AuthenticateDao(this.connection);
    }

    @Override
    public GenericDao<Action> getActionDao() {
        return new ActionDao(this.connection);
    }

    @Override
    public GenericDao<Module> getModuleDao() {
        return new ModuleDao(this.connection);
    }
    
    @Override
    public PermissionDao getPermissionDao() {
        return new PermissionDao(this.connection);
    }
    
    @Override
    public LogDao getLogDao() {
        return new LogDao(this.connection);
    }
    
}