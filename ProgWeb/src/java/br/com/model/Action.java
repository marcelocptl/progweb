/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author ricardo.marcacini
 */
public class Action {

    private int id;
    private String name;
    private String description;
    private boolean active;

    public Action(String name, String description, boolean active) {
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
}
