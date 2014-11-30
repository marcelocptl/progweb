/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author vitor
 */
public class User {

    private int id;
    private String name;
    private String email;
    private boolean active;
    private String password;
    private int profile;

    public User() {}

    public User(String name, String email, boolean active, String password, int id_profile) {
        this.name = name;
        this.email = email;
        this.active = active;
        this.password = password;
        this.profile = id_profile;
    }

    @Override
    public String toString() {
        return "Usuário: "+ name + " ["+ email +"]";
    }

    public User(int id, String password) {
        this.id = id;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }
}
