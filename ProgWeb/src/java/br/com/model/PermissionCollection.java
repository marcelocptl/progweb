/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class PermissionCollection<Type> extends ArrayList<Type>{
    
    public boolean check(int profile, String module, String action)
    {            
        Permission permission = new Permission();
        
        permission.setProfile(profile);
        
        permission.setModuleName(module);
        
        permission.setActionName(action);               
        
        return this.contains(permission);
    }            
    
}
