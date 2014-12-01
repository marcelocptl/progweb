/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Vitor Mesaque
 */
public class Log {

    private int id;
    private String module;
    private String action;
    private User user;
    private Date date;
    private String message;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUser() {
        
        if (user == null) return "[Deletado]";
        
        return user.getName();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        
        String dataz = "dd/MM/yyyy - h:mm";
        SimpleDateFormat formatas = new SimpleDateFormat(dataz );
        return formatas.format(date);

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
