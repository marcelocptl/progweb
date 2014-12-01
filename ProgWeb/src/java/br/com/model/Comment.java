package br.com.model;

import java.sql.Date;
import java.sql.Time;

public class Comment {

    private String imdb_id;
    private User user;
    private Date data;
    private String comment;
    private Time time;

    public Comment(String imdb_id, User user, String comment) {
        this.imdb_id = imdb_id;
        this.user = user;
        this.comment = comment;
    }

    public Comment() {
    }

    public String getImdb() {
        return imdb_id;
    }

    public void setImdb(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getData() {       
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    

}
