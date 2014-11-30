package br.com.controller;

import br.com.business.ActionBO;
import br.com.business.ModuleBO;
import br.com.model.Action;
import br.com.model.Module;
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
public class ModuleController extends HttpServlet {

    private static String LIST = "/view/module/list.jsp";

    private static String ADD = "/view/module/add.jsp";

    private static String EDIT = "/view/module/edit.jsp";

    private static String MODULE = "Module";

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

        String action = request.getParameter("action");

        String id = request.getParameter("id");

        ModuleBO moduleBo = new ModuleBO();

        ActionBO actionBo = new ActionBO();

        switch (action) {
            case "add":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    request.setAttribute("actions", actionBo.getAllActions());

                    forward = ADD;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                }

                break;

            case "edit":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    Module module = moduleBo.getModule(Integer.parseInt(id));

                    request.setAttribute("module", module);

                    request.setAttribute("actions", actionBo.getAllActions());

                    forward = EDIT;

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                }

                break;

            case "delete":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    moduleBo.deleteModule(Integer.parseInt(id));

                    message.addMessage("Perfil apagado com sucesso!");

                    request.setAttribute("modules", moduleBo.getAllModules());

                } else {
                    message.addWarning("Você não tem permissão de acessar a ação [" + action + "] no modulo [" + MODULE + "].");
                }

                break;

            case "list":

                if (_permissions.check(_user.getProfile(), MODULE, action)) {

                    request.setAttribute("modules", moduleBo.getAllModules());
                    
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = Message.singleton();

        Module module;

        String forward = "";

        String action = request.getParameter("action");

        String id = request.getParameter("id");

        ModuleBO moduleBo = new ModuleBO();

        ActionBO actionBo = new ActionBO();

        try {
            switch (action) {
                case "add":
                case "edit":

                    String name = request.getParameter("name");

                    String description = request.getParameter("description");

                    ArrayList<Action> moduleActions = new ArrayList<>();

                    String[] actionsSelected;

                    actionsSelected = request.getParameterValues("actions");

                    boolean active = request.getParameter("active") != null ? true : false;

                    module = new Module(name, description, active);

                    for (String actionId : actionsSelected) {
                        moduleActions.add(actionBo.getAction(Integer.parseInt(actionId)));
                    }

                    module.setActions(moduleActions);

                    if (id == null || id.isEmpty()) {
                        moduleBo.insertModule(module);

                        message.addMessage("Perfil adicionado com sucesso!");
                    } else {
                        module.setId(Integer.parseInt(id));

                        moduleBo.updateModule(module);

                        message.addMessage("Perfil atualizado com sucesso!");
                    }
                    request.setAttribute("modules", moduleBo.getAllModules());

                    forward = LIST;

                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(ModuleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("message", message);

        request.setAttribute("pageContent", forward);

        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");

        view.forward(request, response);
    }
}
