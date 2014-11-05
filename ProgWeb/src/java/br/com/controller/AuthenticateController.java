package br.com.controller;

import br.com.business.AuthenticateBO;
import br.com.business.PermissionBO;
import br.com.business.ProfileBO;
import br.com.business.UserBO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vitor Mesaque
 */

public class AuthenticateController extends HttpServlet 
{    
    private static String LOGOFF = "/index.jsp";
    
    private static String LOGON  = "/view/index/home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String forward = LOGON;
        
        String action = request.getParameter("action");

        AuthenticateBO authenticateBo = new AuthenticateBO();
        
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        
        switch(action)
        {                 
            case "logon":
                
                request.setAttribute("pageContent", LOGON);
                
                view = request.getRequestDispatcher("/view/index/index.jsp");
            
                break;
                       
            case "logoff":
            
                HttpSession session = request.getSession(true); 
                    
                session.invalidate(); 

                break;
                
            default:     
        }

        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        User user;
        
        Profile profile;
        
        String action = request.getParameter("action");  
        
        AuthenticateBO authenticateBo = new AuthenticateBO();
        
        ProfileBO profileBo = new ProfileBO();
        
        UserBO userBo = new UserBO();
        PermissionBO permissionBo = new PermissionBO();
        
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        
        Message message = Message.singleton();
        
        try
        {
            switch(action)
            {
                case "logon":
                    
                    user = authenticateBo.logon(request.getParameter("email"), request.getParameter("password"));
                    
                    if(user == null)
                    {
                        view = request.getRequestDispatcher("index.jsp");
                       
                        message.addWarning("Usuário ou senha incorreto!");
                    }
                    else
                    {
                        HttpSession session = request.getSession(true); 
                    
                        session.setAttribute("_user",user);
                        
                        session.setAttribute("_permissions", permissionBo.getProfilePermissions(user.getProfile()));
                    
                        message.addMessage("Usuário autenticado com sucesso!");
                        
                        view = request.getRequestDispatcher("/view/index/index.jsp"); 
                    }        
                    break;
            }             
        }
        catch (Exception ex) 
        {
            Logger.getLogger(AuthenticateController.class.getName()).log(Level.SEVERE, null, ex); 
        }        
        
        request.setAttribute("message", message);
        
        view.forward(request, response); 
    } 
}