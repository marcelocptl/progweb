
package br.com.business;

import br.com.Dao.DaoFactory;
import br.com.Dao.AuthenticateDao;
import br.com.model.User;
import br.com.util.Md5;

/**
 *
 * @author Vitor Mesaque
 */
public class AuthenticateBO 
{
    public User logon(String email, String password)
    {
        AuthenticateDao authenticateDao = DaoFactory.getDaoFactory().getAuthenticateDao();
        
        if (password != null) password = Md5.encrypt(password);
        
        return authenticateDao.authenticate(email, password);
    }
}
