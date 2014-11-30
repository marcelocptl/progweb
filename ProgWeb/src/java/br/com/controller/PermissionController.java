package br.com.controller;

import br.com.business.ModuleBO;
import br.com.business.PermissionBO;
import br.com.business.ProfileBO;
import br.com.model.Permission;
import br.com.model.PermissionCollection;
import br.com.model.User;
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

/**
 *
 * @author vitor
 */
public class PermissionController extends HttpServlet {

    private static String PERMISSION = "/view/permission/permission.jsp";

    private static String LIST = "/view/profile/list.jsp";

    private static String MODULE = "Permission";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = Message.singleton();

        PermissionCollection<Permission> _permissions = (PermissionCollection<Permission>) request.getSession(true).getAttribute("_permissions");
        User _user = (User) request.getSession(true).getAttribute("_user");

        if (_user == null) {
            message.addWarning("É necessário estar logado em um usuário.");

            response.sendRedirect("AuthenticateController?action=logon");

            return;
        }

        String forward = null;

        if (_permissions.check(_user.getProfile(), MODULE, "add")) {

            forward = PERMISSION;

            try {
                String id = request.getParameter("id");

                ProfileBO profileBo = new ProfileBO();

                ModuleBO moduleBo = new ModuleBO();

                PermissionBO permissionBo = new PermissionBO();

                request.setAttribute("permissions", permissionBo.getProfilePermissions(Integer.parseInt(id)));

                request.setAttribute("profile", profileBo.getProfile(Integer.parseInt(id)));

                request.setAttribute("modules", moduleBo.getAllModules());

            } catch (Exception ex) {
                Logger.getLogger(PermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            message.addWarning("Você não tem permissão de acessar o modulo [" + MODULE + "].");

        }

        request.setAttribute("message", message);

        request.setAttribute("pageContent", forward);

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");

        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = Message.singleton();

        PermissionCollection<Permission> _permissions = (PermissionCollection<Permission>) request.getSession(true).getAttribute("_permissions");
        User _user = (User) request.getSession(true).getAttribute("_user");

        if (_user == null) {

            message.addWarning("É necessário estar logado em um usuário.");

            response.sendRedirect("AuthenticateController?action=logon");

            return;

        }

        String forward = null;

        if (_permissions.check(_user.getProfile(), MODULE, "add")) {

            try {
                ProfileBO profileBo = new ProfileBO();

                PermissionBO permissionBo = new PermissionBO();

                String id = request.getParameter("id");

                String[] profileModuleAction = request.getParameterValues("permissions");

                ArrayList<Permission> permissionsCollection = new ArrayList<>();

                if (profileModuleAction == null) {

                    permissionBo.delete(Integer.parseInt(id));

                } else {

                    for (String permissions : profileModuleAction) {
                        String[] aux = permissions.split(";");

                        Permission permission = new Permission();

                        permission.setProfile(Integer.parseInt(aux[0]));

                        permission.setModule(Integer.parseInt(aux[1]));

                        permission.setAction(Integer.parseInt(aux[2]));

                        permissionsCollection.add(permission);
                    }

                    if (permissionsCollection != null) {

                        permissionBo.save(permissionsCollection, Integer.parseInt(id));

                    }
                }

                request.setAttribute("profiles", profileBo.getAllProfiles());
                forward = LIST;

            } catch (Exception ex) {
                Logger.getLogger(PermissionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            message.addWarning("Você não tem permissão de acessar o modulo [" + MODULE + "].");

        }

        request.setAttribute("message", message);

        request.setAttribute("pageContent", forward);

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");

        view.forward(request, response);
    }
}
