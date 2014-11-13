package br.com.controller;

import br.com.business.AuthenticateBO;
import br.com.business.PermissionBO;
import br.com.model.User;
import br.com.util.LoginFacebook;
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
        
        Message message = Message.singleton();
        
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        
        PermissionBO permissionBo = new PermissionBO();
        
        LoginFacebook loginFacebook = new LoginFacebook();
        
        String code = request.getParameter("code");
        
        if (code == null || code.equals("")) {
            
            switch(action)
            {                 
                case "logonFacebook":
                    
                    response.setContentType("text/html");

                    String site = loginFacebook.getLoginRedirectURL();

                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site);  

                    return;

                case "logoff":

                    HttpSession session = request.getSession(true); 

                    session.invalidate(); 

                    break;

                default:  

                    break;
            }
        } else {
            try {
                User user = loginFacebook.obterUsuarioFacebook(code);
                
                HttpSession session = request.getSession(true); 

                session.setAttribute("_user",user);

                session.setAttribute("_permissions", permissionBo.getProfilePermissions(user.getProfile()));

                message.addMessage("Usuário autenticado com sucesso!");

                view = request.getRequestDispatcher("/view/index/index.jsp"); 
                
            } catch (Exception e) {
                message.addWarning("Não foi possível se conectar!");
            }
        }
        
        request.setAttribute("message", message);
        
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        User user;
                
        String action = request.getParameter("action");  
        
        AuthenticateBO authenticateBo = new AuthenticateBO();
        
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