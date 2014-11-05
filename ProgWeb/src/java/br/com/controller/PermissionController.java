package br.com.controller;

import br.com.business.ModuleBO;
import br.com.business.PermissionBO;
import br.com.business.ProfileBO;
import br.com.model.Permission;
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        Message message = Message.singleton();

        String forward = PERMISSION;

        try 
        {
            ProfileBO profileBo = new ProfileBO();

            ModuleBO moduleBo = new ModuleBO();
            
            PermissionBO permissionBo = new PermissionBO();

            request.setAttribute("permissions", permissionBo.getAllPermissions());
            
            request.setAttribute("profiles", profileBo.getAllProfiles());

            request.setAttribute("modules", moduleBo.getAllModules());

        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PermissionController.class.getName()).log(Level.SEVERE, null, ex);
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

        ProfileBO profileBo = new ProfileBO();

        String forward = LIST;

        try 
        {
            String[] profileModuleAction = request.getParameterValues("permissions");
            
            ArrayList<Permission> permissionsCollection = new ArrayList<>();
            
            for (String permissions : profileModuleAction)
            {
                String[] aux = permissions.split(";");
                
                Permission permission = new Permission();
                
                permission.setProfile(Integer.parseInt(aux[0]));
                
                permission.setModule(Integer.parseInt(aux[1]));
                
                permission.setAction(Integer.parseInt(aux[2]));
                
                permissionsCollection.add(permission);
            }

            if (permissionsCollection != null) 
            {
                PermissionBO permissionBo = new PermissionBO();
                
                permissionBo.save(permissionsCollection);
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("message", message);

        request.setAttribute("pageContent", forward);
        
        request.setAttribute("profiles", profileBo.getAllProfiles());

        RequestDispatcher view = request.getRequestDispatcher("/view/index/index.jsp");

        view.forward(request, response);
    }
}