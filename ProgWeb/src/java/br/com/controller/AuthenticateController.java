package br.com.controller;

import br.com.business.AuthenticateBO;
import br.com.business.PermissionBO;
import br.com.business.UserBO;
import br.com.model.User;
import br.com.util.LogRegister;
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
public class AuthenticateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Message message = Message.singleton();

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        
        if (request.getSession(true).getAttribute("_user") != null && !action.equals("logoff")) {
            message.addWarning("Você já está logado. Faça logoff!");

            request.setAttribute("message", message);

            view.forward(request, response);

            return;
        }

        PermissionBO permissionBo = new PermissionBO();

        LoginFacebook loginFacebook = new LoginFacebook();

        AuthenticateBO authenticateBo = new AuthenticateBO();
        
        UserBO userBO = new UserBO();
        
        String code = request.getParameter("code");

        if (code == null || code.equals("")) {

            switch (action) {
                
                case "logon":
                
                    request.setAttribute("pageContent", "/login.jsp");
                    
                    break;
                    
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

                User authenticateUser = authenticateBo.logon( user.getEmail() , user.getPassword() );
                
                if (authenticateUser == null ) {
                    
                    if (userBO.authenticate( user.getEmail() ) == null) {
                        
                        user.setId( userBO.insertUser(user) );
                        
                        LogRegister.singleton().toLog("User", "add", "Usuário do Facebook [" + user.getName() + "] inserido.", user.getId());
                        
                    } else {
                        
                        message.addWarning("Seu e-mail usado no facebook já existe no nosso sistema.");

                        response.sendRedirect("UserController?action=add");

                        return;
                        
                    }
                        
                    
                } else {
                    
                    user = authenticateUser;
                    
                }
                
                HttpSession session = request.getSession(true);

                session.setAttribute("_user", user);

                session.setAttribute("_permissions", permissionBo.getProfilePermissions(user.getProfile()));

                message.addMessage("Facebook autenticado com sucesso!");
            
            } catch (Exception e) {
                message.addWarning("Não foi possível conectar com o facebook!");
            }
        }

        request.setAttribute("message", message);

        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;

        String action = request.getParameter("action");

        AuthenticateBO authenticateBo = new AuthenticateBO();

        PermissionBO permissionBo = new PermissionBO();

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");

        Message message = Message.singleton();

        try {
            switch (action) {
                case "logon":

                    String email = request.getParameter("email");
                    String password = request.getParameter("password");                    
                    
                    user = authenticateBo.logon(email, password);

                    if (user == null || password == null || password.isEmpty()) {

                        message.addWarning("Usuário ou senha incorreto!");
                        
                        request.setAttribute("pageContent", "/login.jsp");
                        
                    } else {
                        HttpSession session = request.getSession(true);

                        session.setAttribute("_user", user);

                        session.setAttribute("_permissions", permissionBo.getProfilePermissions(user.getProfile()));

                        message.addMessage("Usuário autenticado com sucesso!");

                    }
                    break;

            }
        } catch (Exception ex) {
            Logger.getLogger(AuthenticateController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("message", message);

        view.forward(request, response);
    }
}
