package br.com.controller;

import br.com.business.ProfileBO;
import br.com.business.UserBO;
import br.com.model.Permission;
import br.com.model.PermissionCollection;
import br.com.model.User;
import br.com.util.LogRegister;
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
public class UserController extends HttpServlet {

    private static String LIST = "/view/user/list.jsp";
    private static String ADD = "/view/user/add.jsp";
    private static String EDIT = "/view/user/edit.jsp";
    private static String DELETE = "/view/helper/delete.jsp";
    private static String PASSWORD = "/view/user/password.jsp";

    private static String MODULE = "User";

    private static int PROFILE_USER = 9;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = Message.singleton();

        String action = request.getParameter("action");

        PermissionCollection<Permission> _permissions = (PermissionCollection<Permission>) request.getSession(true).getAttribute("_permissions");
        User _user = (User) request.getSession(true).getAttribute("_user");

        if (_user == null && !action.equals("add")) {
            message.addWarning("É necessário estar logado em um usuário.");

            response.sendRedirect("AuthenticateController?action=logon");

            return;
        }

        UserBO userBo = new UserBO();

        ProfileBO profileBo = new ProfileBO();

        User user;

        String forward = null;

        String id = request.getParameter("id");

        switch (action) {
            case "add":

                forward = ADD;

                if (_permissions != null && _permissions.check(_user.getProfile(), MODULE, action)) {
                    request.setAttribute("profiles", profileBo.getAllProfiles());
                }

                break;

            case "edit":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {
                    user = userBo.getUser(Integer.parseInt(id));

                    request.setAttribute("user", user);

                    request.setAttribute("profiles", profileBo.getAllProfiles());

                    forward = EDIT;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                }

                break;

            case "list":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    request.setAttribute("users", userBo.getAllUsers());
                    forward = LIST;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                }

                break;

            case "delete":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    user = userBo.getUser(Integer.parseInt(id));

                    request.setAttribute("objDeleted", user);
                    
                    request.setAttribute("controller", this.getClass().getSimpleName());
                    
                    forward = DELETE;                    

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                }

                break;

            case "password":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    user = userBo.getUser(Integer.parseInt(id));

                    request.setAttribute("user", user);

                    forward = PASSWORD;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = Message.singleton();

        String action = request.getParameter("action");

        PermissionCollection<Permission> _permissions = (PermissionCollection<Permission>) request.getSession(true).getAttribute("_permissions");            
        User _user = (User) request.getSession(true).getAttribute("_user");

        if (_user == null && !action.equals("add")) {

            message.addWarning("É necessário estar logado em um usuário.");

            response.sendRedirect("AuthenticateController?action=logon");

            return;
        }

        User user;

        String forward = ADD;

        String id = request.getParameter("id");

        UserBO userBo = new UserBO();

        String password = request.getParameter("password");

        String confirm = request.getParameter("confirm");

        try {
            switch (action) {
                case "add":

                case "edit":

                    String name = request.getParameter("name");

                    String email = request.getParameter("email");

                    boolean active;

                    int profileId;

                    if (_user == null) {

                        active = true;
                        profileId = PROFILE_USER;

                    } else {

                        active = request.getParameter("active") != null;
                        profileId = Integer.parseInt(request.getParameter("profile"));

                    }

                    user = new User(name, email, active, password, profileId);

                    if (userBo.authenticate(email) != null) {
                    
                        message.addWarning("O E-MAIL informado já está em uso!");

                    } else if (id == null || id.isEmpty()) {

                        if (password == null || confirm == null || 
                                password.isEmpty() || confirm.isEmpty() || !password.equals(confirm)) {
                            
                            message.addWarning("SENHA não confirmada!");
                            
                        } else {
                            
                            int insertId = userBo.insertUser(user);

                            if (_user != null) {
                                insertId = _user.getId();
                            }

                            LogRegister.singleton().toLog(MODULE, action, "Usuário [" + user.getName() + "] inserido.", insertId);

                            message.addMessage("Cadastro realizado com sucesso!");
                        }
                        
                    } else {
                        user.setId(Integer.parseInt(id));

                        userBo.updateUser(user);

                        LogRegister.singleton().toLog(MODULE, action, "Usuário [" + user.getName() + "] atualizado.", _user.getId());

                        message.addMessage("Usuário atualizado com sucesso!");
                    }
                    
                    if (_user != null) {
                        
                        request.setAttribute("users", userBo.getAllUsers());
                        forward = LIST;
                        
                    }

                    break;

                case "password":

                    if (_permissions.check(_user.getProfile(), MODULE, action)) {

                        forward = PASSWORD;

                        if (password.equals(confirm)) {
                            user = new User(Integer.parseInt(id), password);

                            userBo.updatePass(user);

                            message.addMessage("Senha alterada com sucesso!");

                            request.setAttribute("users", userBo.getAllUsers());

                            forward = LIST;
                        }

                    } else {
                        message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                    }

                    break;
                    
                case "delete":

                    if (_permissions.check(_user.getProfile(), MODULE, action)) {
                    
                        user = userBo.getUser(Integer.parseInt(id));
                        
                        userBo.deleteUser(user.getId());

                        LogRegister.singleton().toLog(MODULE, action, "Usuário [" + user.getName() + "] deletado.", _user.getId());
                        
                        message.addMessage("Usuário apagado com sucesso!");

                        request.setAttribute("users", userBo.getAllUsers());

                        forward = LIST;

                    } else {
                        message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                    }

                    break;                    
            }
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("message", message);

        request.setAttribute("pageContent", forward);

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");

        view.forward(request, response);
    }
}
