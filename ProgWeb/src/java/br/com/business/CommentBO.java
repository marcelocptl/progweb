package br.com.business;

import br.com.Dao.CommentDao;
import br.com.Dao.DaoFactory;
import br.com.model.Comment;
import java.util.ArrayList;

public class CommentBO {

    public void save(Comment comment) {
        CommentDao commentDao = DaoFactory.getDaoFactory().getCommentDao();

        commentDao.save(comment);
    }

    public void delete(String imdb, String user, String date, String time) {
        CommentDao commentDao = DaoFactory.getDaoFactory().getCommentDao();

        commentDao.delete(imdb, user, date, time);
    }

    public ArrayList<Comment> list(String imdbId) {
        CommentDao commentDao = DaoFactory.getDaoFactory().getCommentDao();
        
        return commentDao.list(imdbId);
    }

}
