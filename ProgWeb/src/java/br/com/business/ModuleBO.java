
package br.com.business;

import br.com.Dao.DaoFactory;
import br.com.Dao.GenericDao;
import br.com.model.Module;
import java.util.ArrayList;

/**
 *
 * @author Vitor Mesaque
 */
public class ModuleBO
{
        
    public void insertModule(Module module)
    {
        GenericDao<Module> moduleDao= DaoFactory.getDaoFactory().getModuleDao();
       
        moduleDao.save(module);
    }
    
    public void updateModule(Module module)
    {
        GenericDao<Module> moduleDao= DaoFactory.getDaoFactory().getModuleDao();
       
        moduleDao.update(module);
    }

    public void deleteModule(int id)
    {
        GenericDao<Module> moduleDao= DaoFactory.getDaoFactory().getModuleDao();
       
        moduleDao.delete(id);
    }
    
    public ArrayList<Module> getAllModules()
    {
        GenericDao<Module> moduleDao= DaoFactory.getDaoFactory().getModuleDao();
       
        return moduleDao.list();
    }
    
     public Module getModule(int id)
    {
        GenericDao<Module> moduleDao= DaoFactory.getDaoFactory().getModuleDao();
       
        return moduleDao.getById(id);
    }
}
