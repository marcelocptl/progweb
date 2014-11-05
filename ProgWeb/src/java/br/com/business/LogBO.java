/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.business;

import br.com.Dao.DaoFactory;
import br.com.Dao.LogDao;
import java.util.ArrayList;

/**
 *
 * @author ricardo.marcacini
 */
public class LogBO {

    public ArrayList getAllLogs() {
         LogDao logDao = DaoFactory.getDaoFactory().getLogDao();
        
        return logDao.list();
    }
}
