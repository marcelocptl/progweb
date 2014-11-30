/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.business.LogBO;
import br.com.model.Log;
import br.com.model.Permission;
import br.com.model.PermissionCollection;
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
 * @author ricardo.marcacini
 */
public class LogController extends HttpServlet {

    private static String LIST = "/view/log/list.jsp";
    
        private static String MODULE = "Log";

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

        LogBO logBo = new LogBO();

        switch (action) 
        {
            case "list":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    request.setAttribute("logs", logBo.getAllLogs());
                    forward = LIST;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
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
        
        Log log;

        String forward = LIST;

        String action = request.getParameter("action");

        LogBO logBo = new LogBO();

        try {
            switch (action) 
            {
                default:
                    
                forward = LIST;

                break;
            }
        } catch (Exception ex) {
            Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("pageContent", forward);

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");

        view.forward(request, response);
    }
}
