
package br.com.model;

/**
 *
 * @author vitor
 */
public class Permission 
{
    private int profile;
    private String profileName;
    
    private int module;
    private String moduleName;
    
    private int action;
    private String actionName;

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }  

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }       
    
    @Override
    public boolean equals(Object o)
    {
        Permission permission = (Permission)o;        
        
        return this.getProfile() == permission.getProfile() && this.getModuleName().equals(permission.getModuleName()) && this.getActionName().equals(permission.getActionName());

     }
}
