package br.com.business;

import br.com.Dao.DaoFactory;
import br.com.Dao.GenericDao;
import br.com.Dao.UserDao;
import br.com.model.User;
import br.com.util.Md5;
import java.util.ArrayList;

/**
 *
 * @author Vitor Mesaque
 */
public class UserBO {

    public void insertUser(User user) {
        GenericDao<User> userDao = DaoFactory.getDaoFactory().getUserDao();

        user.setPassword(Md5.encrypt(user.getPassword()));

        userDao.save(user);
    }

    public ArrayList<User> getAllUsers() {
        GenericDao<User> userDao = DaoFactory.getDaoFactory().getUserDao();

        return userDao.list();
    }

    public User getUser(int id) 
    {
        GenericDao<User> userDao = DaoFactory.getDaoFactory().getUserDao();

        return userDao.getById(id);
    }

    public void updateUser(User user) 
    {
        GenericDao<User> userDao = DaoFactory.getDaoFactory().getUserDao();

        userDao.update(user);
    }

    public Boolean deleteUser(int id) 
    {
        GenericDao<User> userDao = DaoFactory.getDaoFactory().getUserDao();

        if(userDao.delete(id))
            return true;
        
        return false;
    }

    public void updatePass(User user) 
    {
        GenericDao<User> userDao = DaoFactory.getDaoFactory().getUserDao();
        
        user.setPassword(Md5.encrypt(user.getPassword()));
        
        userDao.updatePass(user);
    }
}
