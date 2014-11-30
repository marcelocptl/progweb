package br.com.controller;

import br.com.business.ModuleBO;
import br.com.business.ProfileBO;
import br.com.model.Permission;
import br.com.model.PermissionCollection;
import br.com.model.Profile;
import br.com.model.User;
import br.com.util.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vitor
 */

public class ProfileController extends HttpServlet 
{    
    private static String LIST = "/view/profile/list.jsp";
    
    private static String ADD  = "/view/profile/add.jsp";
    
    private static String EDIT  = "/view/profile/edit.jsp";
    
    private static String PERMISSION  = "/view/profile/permission.jsp";
    
    private static String MODULE = "Profile";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Message message = Message.singleton();
        
        PermissionCollection<Permission> _permissions = (PermissionCollection<Permission>) request.getSession(true).getAttribute("_permissions");
        User _user = (User) request.getSession(true).getAttribute("_user");         
        
        if ( _user == null ) {
            message.addWarning("É necessário estar logado em um usuário.");
            
            response.sendRedirect("AuthenticateController?action=logon");
            
            return;
        }         
        
        String forward = null;
        
        String action = request.getParameter("action");

        String id = request.getParameter("id");
        
        ProfileBO profileBo = new ProfileBO();
        
        ModuleBO moduleBo = new ModuleBO();
        
        Profile profile;       
        
        switch(action)
        {                 
            case "add":
                
                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    forward = ADD;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação ["+ action +"] no modulo ["+ MODULE +"].");
                }                
                
                break;
                       
              case "edit":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    profile = profileBo.getProfile(Integer.parseInt(id)); 

                    request.setAttribute("profile", profile); 

                    forward = EDIT;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação ["+ action +"] no modulo ["+ MODULE +"].");
                }                  
                
                break;

           case "delete":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    profileBo.deleteProfile(Integer.parseInt(id));

                    message.addMessage("Perfil apagado com sucesso!");

                    request.setAttribute("profiles", profileBo.getAllProfiles());

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação ["+ action +"] no modulo ["+ MODULE +"].");
                }               
               
                break;

            case "list":
            
                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    request.setAttribute("profiles", profileBo.getAllProfiles());
                    forward = LIST;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação ["+ action +"] no modulo ["+ MODULE +"].");
                }
                
                break;
                    
        }

        request.setAttribute("message", message);
        
        request.setAttribute("pageContent", forward);
        
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
         
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Message message = Message.singleton();
         
        Profile profile;
        
        String forward="";
        
        String action = request.getParameter("action");  
        
        String id = request.getParameter("id");

        ProfileBO profileBo = new ProfileBO();

        try
        {
            switch(action)
            {
                case "add":
                case "edit":
                    
                    String name = request.getParameter("name");
                     
                    String description = request.getParameter("description");
                    
                    boolean active = request.getParameter("active") != null;
                    
                    profile = new Profile(name, description, active);
                    
                    if(id == null || id.isEmpty())
                    {
                        profileBo.insertProfile(profile);
                        
                        message.addMessage("Perfil adicionado com sucesso!");
                    }
                    else
                    {
                        profile.setId(Integer.parseInt(id));
                        
                        profileBo.updateProfile(profile);
                        
                        message.addMessage("Perfil atualizado com sucesso!");
                    }
                    request.setAttribute("profiles", profileBo.getAllProfiles());
                    
                    forward = LIST;
                            
                    break;
            }             
        }
        catch (Exception ex) 
        {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex); 
        }        
        
        request.setAttribute("message", message);
        
        request.setAttribute("pageContent", forward);
        
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        
        view.forward(request, response); 
    } 
}