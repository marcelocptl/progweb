/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.util.ArrayList;

/**
 *
 * @author marcelo
 */
public class Module {
    
    private int id;

    private String name;
    
    private String description;
    
    private boolean active;
    
    private ArrayList<Action> actions;

    public Module(String name, String description, boolean active) {
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    } 

    public ArrayList<Action> getActions() {
        return this.actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }
    public boolean actionExists(int key) 
    {
        for(Action action : this.getActions())
        {
            if(action.getId() == key)
                return true;
        }
        return false;
    }
}
