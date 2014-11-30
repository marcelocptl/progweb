/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.business.ActionBO;
import br.com.model.Action;
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
public class ActionController extends HttpServlet {

    private static String LIST = "/view/action/list.jsp";
    private static String ADD = "/view/action/add.jsp";
    private static String EDIT = "/view/action/edit.jsp";

     private static String MODULE = "Action";
    
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

        String _action = request.getParameter("action");
        
        String id = request.getParameter("id");

        ActionBO actionBo = new ActionBO();      
        
        switch (_action) 
        {
            case "add":

                if (_permissions.check(_user.getProfile(), MODULE, _action)) {

                    forward = ADD;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + _action + "] no modulo [" + MODULE + "].");
                }                 
                
                break;

            case "edit":

                if (_permissions.check(_user.getProfile(), MODULE, _action)) {

                    Action action = actionBo.getAction(Integer.parseInt(id));

                    request.setAttribute("action", action);

                    forward = EDIT;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + _action + "] no modulo [" + MODULE + "].");
                }   
                
                break;             
                
            case "list":

                if (_permissions.check(_user.getProfile(), MODULE, _action)) {

                    request.setAttribute("actions", actionBo.getAllActions());
                    forward = LIST;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + _action + "] no modulo [" + MODULE + "].");
                }                
                
                break;

            case "delete":

                if (_permissions.check(_user.getProfile(), MODULE, _action)) {

                    actionBo.deleteAction(Integer.parseInt(id));

                    message.addMessage("Ação apagada com sucesso!");

                    request.setAttribute("actions", actionBo.getAllActions());

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + _action + "] no modulo [" + MODULE + "].");
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
        
        Action action;

        String forward = "";

        String _action = request.getParameter("action");

        String id = request.getParameter("id");

        ActionBO actionBo = new ActionBO();

        try {
            switch (_action) 
            {
                case "add":
                case "edit":

                    String name = request.getParameter("name");

                    String description = request.getParameter("description");

                    boolean active = request.getParameter("active") != null ? true : false;

                    action = new Action(name, description, active);

                    if (id == null || id.isEmpty()) 
                    {
                        actionBo.insertAction(action);
                        
                        message.addMessage("Perfil adicionado com sucesso!");
                    } 
                    else 
                    {
                        action.setId(Integer.parseInt(id));

                        actionBo.updateAction(action);
                        
                        message.addMessage("Perfil alterado com sucesso!");
                    }
                    request.setAttribute("actions", actionBo.getAllActions());

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
