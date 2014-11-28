package br.com.controller;

import br.com.business.ProfileBO;
import br.com.business.UserBO;
import br.com.model.User;
import br.com.model.UserFilm;
import br.com.util.LogRegister;
import br.com.util.Message;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author vitor
 */
public class FilmeController extends HttpServlet {

    private static final String LIST = "/view/user/list.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Message message = Message.singleton();

        String forward = LIST;

        String action = request.getParameter("action");

        String id = request.getParameter("id");
        ArrayList<UserFilm> list = null;
        switch (action) 
        {
            case "recentes":

                break;

            case "melhores":

                break;

            case "list":

                break;

            case "favoritos":

                break;
            case "pretendidos":

                break;
            case "assistidos":

                break;
        }

        request.setAttribute("message", message);
        request.setAttribute("filmes", list);
        request.setAttribute("pageContent", forward);

        RequestDispatcher view = request.getRequestDispatcher("/view/index/index.jsp");

        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Message message = Message.singleton();
        
        User user;

        String forward = "";

        String action = request.getParameter("action");

        String id = request.getParameter("id");

        UserBO userBo = new UserBO();

        String password = request.getParameter("password");
                    
        HttpSession session = request.getSession(true);
        
        User _user = (User) session.getAttribute("_user");
        
        try 
        {
            switch (action) 
            {
                case "add":

                case "edit":

                    String name = request.getParameter("name");

                    String email = request.getParameter("email");

                    boolean active = request.getParameter("active") != null ? true : false;

                    int profileId = Integer.parseInt(request.getParameter("profile"));
                    
                    user = new User(name, email, active, password, profileId);

                    if (id == null || id.isEmpty()) 
                    {
                        userBo.insertUser(user);
                        
                        LogRegister.singleton().toLog("User", action, "Usuário ["+ user.getName()+"] inserido.", _user.getId());
                        
                        message.addMessage("Usuário adicionado com sucesso!");
                    } 
                    else 
                    {
                        user.setId(Long.parseLong(id));

                        userBo.updateUser(user);
                        
                        LogRegister.singleton().toLog("User", action, "Usuário ["+ user.getName()+"] atualizado.", _user.getId());
                        
                        message.addMessage("Usuário atualizado com sucesso!");
                    }

                    request.setAttribute("users", userBo.getAllUsers());

                    forward = LIST;

                    break;
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("message", message);
        
        request.setAttribute("pageContent", forward);

        RequestDispatcher view = request.getRequestDispatcher("/view/index/index.jsp");

        view.forward(request, response);
    }
}