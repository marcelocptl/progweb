
package br.com.Dao;

import br.com.Dao.GenericDao;
import br.com.jdbc.factory.JDBCDaoFactory;
import br.com.model.Action;
import br.com.model.Profile;
import br.com.model.Module;
import br.com.model.User;

/**
 *
 * @author Vitor Mesaque
 */
public abstract class DaoFactory {

    public static DaoFactory getDaoFactory() {
        return new JDBCDaoFactory();
    }

    public abstract GenericDao<User> getUserDao();

    public abstract GenericDao<Profile> getProfileDao();
    
    public abstract AuthenticateDao getAuthenticateDao();

    public abstract GenericDao<Action> getActionDao();
    
    public abstract GenericDao<Module> getModuleDao();
    
    public abstract PermissionDao getPermissionDao();
    
    public abstract LogDao getLogDao();
    
    public abstract CommentDao getCommentDao();
    
    public abstract UserFilmsDao getUserFilmsDao();
}
