package br.com.Dao;

import br.com.business.UserBO;
import br.com.model.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommentDao {
    
    private Connection connection;

    public CommentDao(Connection connection) {
        this.connection = connection;
    }

    public int save(Comment comment) {
        
        try {
            String sql = "INSERT INTO comentarios (imdb_id,userdb_id,comentario) VALUES ('" + comment.getImdb()+ "','" + comment.getUser().getId() + "','" + comment.getComment() + "')";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    public Boolean delete(String imdb, String user, String date, String time) {
        try
        {
           String sql = "DELETE FROM comentarios WHERE imdb_id = '" + imdb + "' AND userdb_id = '"+ user +"' AND _create = '"+ date +"' AND time >='"+ time +".0' AND time <='"+ time +".999999'";
           
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();
           
           return true;
          
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        } 
        return false;
    }

    public ArrayList<Comment> list(String imdbId) {
        try {
            UserBO userBo = new UserBO();
            
            String sql = "SELECT * FROM comentarios WHERE imdb_id = '"+ imdbId +"' ORDER BY _create DESC";

            Statement stmt = this.connection.createStatement();

            ArrayList<Comment> comments = new ArrayList<>();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Comment comment = new Comment();
                
                comment.setImdb(rs.getString("imdb_id"));
                comment.setUser( userBo.getUser( rs.getInt("userdb_id") ) );
                comment.setComment(rs.getString("comentario"));
                comment.setData(rs.getDate("_create"));
                comment.setTime(rs.getTime("time"));
                
                comments.add(comment);

            }

            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
}
