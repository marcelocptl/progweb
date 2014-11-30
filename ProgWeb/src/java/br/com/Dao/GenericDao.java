
package br.com.Dao;

import br.com.model.User;
import java.util.ArrayList;

/**
 *
 * @author Vitor Mesaque
 */
public interface GenericDao<Type> {
    
    public int save (Type obj);
    
    public void update (Type obj);
    
    public Boolean delete (int id);
    
    public ArrayList<Type> list ();
    
    public Type getById (int id);

    public void updatePass(User user);

    public User authenticate(String email);
    
}

