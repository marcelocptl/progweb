package br.com.controller;

import br.com.business.ProfileBO;
import br.com.business.UserBO;
import br.com.model.User;
import br.com.util.LogRegister;
import br.com.util.Md5;
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
 * @author vitor
 */
public class UserController extends HttpServlet {

    private static String LIST = "/view/user/list.jsp";
    private static String ADD = "/view/user/add.jsp";
    private static String EDIT = "/view/user/edit.jsp";
    private static String DELETE = "/view/user/delete.jsp";
    private static String PASSWORD = "/view/user/password.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Message message = Message.singleton();
        
        UserBO userBo = new UserBO();

        ProfileBO profileBo = new ProfileBO();

        User user;
        
        String forward = LIST;

        String action = request.getParameter("action");

        String id = request.getParameter("id");

        switch (action) 
        {
            case "add":

                request.setAttribute("profiles", profileBo.getAllProfiles());

                forward = ADD;

                break;

            case "edit":

                user = userBo.getUser(Integer.parseInt(id));

                request.setAttribute("user", user);

                request.setAttribute("profiles", profileBo.getAllProfiles());

                forward = EDIT;

                break;

            case "list":

                request.setAttribute("users", userBo.getAllUsers());

                break;

            case "delete":

                userBo.deleteUser(Integer.parseInt(id));
                
                message.addMessage("Usuário apagado com sucesso!");
                
                request.setAttribute("users", userBo.getAllUsers());

                break;

            case "password":

                user = userBo.getUser(Integer.parseInt(id));

                request.setAttribute("user", user);

                forward = PASSWORD;

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

                case "password":
                    
                    String confirm = request.getParameter("confirm");
                    
                    forward = PASSWORD;
                    
                    if (password.equals(confirm)) 
                    {
                        user = new User(Long.parseLong(id), password);
                        
                        userBo.updatePass(user);
                        
                        message.addMessage("Senha alterada com sucesso!");
                        
                        request.setAttribute("users", userBo.getAllUsers());
                        
                        forward = LIST;
                    }
                    
                    break;
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("message", message);
        
        request.setAttribute("pageContent", forward);

        RequestDispatcher view = request.getRequestDispatcher("/view/index/index.jsp");

        view.forward(request, response);
    }
}