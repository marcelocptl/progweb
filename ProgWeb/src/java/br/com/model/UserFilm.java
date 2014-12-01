/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author brucce
 */
public class UserFilm {
    
    private int user_id;
    private String imdb_id;
    private boolean favorito;
    private boolean pretende_ver;
    private boolean assistiu;
    private Integer nota;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImde_id() {
        return imdb_id;
    }

    public void setImde_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public boolean isPretende_ver() {
        return pretende_ver;
    }

    public void setPretende_ver(boolean pretende_ver) {
        this.pretende_ver = pretende_ver;
    }

    public boolean isAssistiu() {
        return assistiu;
    }

    public void setAssistiu(boolean assistiu) {
        this.assistiu = assistiu;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
