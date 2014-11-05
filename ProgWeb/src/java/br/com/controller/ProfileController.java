package br.com.controller;

import br.com.business.ModuleBO;
import br.com.business.ProfileBO;
import br.com.model.Profile;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Message message = Message.singleton();
        
        String forward = LIST;
        
        String action = request.getParameter("action");

        String id = request.getParameter("id");
        
        ProfileBO profileBo = new ProfileBO();
        
        ModuleBO moduleBo = new ModuleBO();
        
        Profile profile;
        
        switch(action)
        {                 
            case "add":
                
                forward = ADD;
            
                break;
                       
              case "edit":

                profile = profileBo.getProfile(Integer.parseInt(id)); 

                request.setAttribute("profile", profile); 
                
                forward = EDIT;
                
                break;

           case "delete":

                profileBo.deleteProfile(Integer.parseInt(id));
                
                message.addMessage("Perfil apagado com sucesso!");
                
                request.setAttribute("profiles", profileBo.getAllProfiles());

                break;

            case "list":
            
                request.setAttribute("profiles", profileBo.getAllProfiles());
                
                break;
                
            default:
                
                forward = ADD;    
        }

        request.setAttribute("message", message);
        
        request.setAttribute("pageContent", forward);
        
        RequestDispatcher view = request.getRequestDispatcher("/view/index/index.jsp");
         
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
                    
                    boolean active = request.getParameter("active") != null ? true : false;
                    
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
        
        RequestDispatcher view = request.getRequestDispatcher("/view/index/index.jsp");
        
        view.forward(request, response); 
    } 
}