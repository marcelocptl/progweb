/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.business;

import br.com.Dao.DaoFactory;
import br.com.Dao.GenericDao;
import br.com.model.Action;
import java.util.ArrayList;

/**
 *
 * @author ricardo.marcacini
 */
public class ActionBO {
       public void insertAction(Action action) {
        GenericDao<Action> ActionDao = DaoFactory.getDaoFactory().getActionDao();
        ActionDao.save(action);
    }

    public Action getAction(int id) {
        GenericDao<Action> ActionDao = DaoFactory.getDaoFactory().getActionDao();
        return ActionDao.getById(id);
    }

    public ArrayList getAllActions() {
         GenericDao<Action> ActionDao = DaoFactory.getDaoFactory().getActionDao();
         return ActionDao.list();
    }

    public void updateAction(Action actionp) {
         GenericDao<Action> ActionDao = DaoFactory.getDaoFactory().getActionDao();
         ActionDao.update(actionp);
    }
    
    public void deleteAction(int id){
        GenericDao<Action> ActionDao = DaoFactory.getDaoFactory().getActionDao();
        ActionDao.delete(id);
    }
}
