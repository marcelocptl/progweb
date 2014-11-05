
package br.com.business;

import br.com.Dao.DaoFactory;
import br.com.Dao.GenericDao;
import br.com.model.Profile;
import br.com.model.User;
import java.util.ArrayList;

/**
 *
 * @author Vitor Mesaque
 */
public class ProfileBO
{
        
    public void insertProfile(Profile profile)
    {
        GenericDao<Profile> profileDao= DaoFactory.getDaoFactory().getProfileDao();
       
        profileDao.save(profile);
    }
    
    public void updateProfile(Profile profile)
    {
        GenericDao<Profile> profileDao= DaoFactory.getDaoFactory().getProfileDao();
       
        profileDao.update(profile);
    }

    public void deleteProfile(int id)
    {
        GenericDao<Profile> profileDao= DaoFactory.getDaoFactory().getProfileDao();
       
        profileDao.delete(id);
    }
    
    public ArrayList<Profile> getAllProfiles()
    {
        GenericDao<Profile> profileDao= DaoFactory.getDaoFactory().getProfileDao();
       
        return profileDao.list();
    }
    
     public Profile getProfile(int id)
    {
        GenericDao<Profile> profileDao= DaoFactory.getDaoFactory().getProfileDao();
       
        return profileDao.getById(id);
    }
}
