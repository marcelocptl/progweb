/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.business.LogBO;
import br.com.model.Log;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Message message = Message.singleton();
         
        String forward = LIST;

        String action = request.getParameter("action");

        LogBO logBo = new LogBO();

        switch (action) 
        {
            case "list":

                request.setAttribute("logs", logBo.getAllLogs());

                break;            
            default:

                forward = LIST;
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

        RequestDispatcher view = request.getRequestDispatcher("/view/index/index.jsp");

        view.forward(request, response);
    }
}
