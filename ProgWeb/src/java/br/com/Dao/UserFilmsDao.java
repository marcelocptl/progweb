package br.com.Dao;

import br.com.model.Filme;
import br.com.model.UserFilm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vitor Mesaque
 */
public class UserFilmsDao {

    private final Connection connection;

    public UserFilmsDao(Connection connection) {
        this.connection = connection;
    }

    public void save(UserFilm uf) {

        try {
            String sql = "INSERT INTO user_films(userdb_id, imdb_id, favorito, pretende_ver, assistiu, nota) VALUES (" + uf.getUser_id() + ", '" + uf.getImde_id() + "', " + uf.isFavorito()+ ", " + uf.isPretende_ver()+ ", " + uf.isAssistiu()+ ", " + uf.getNota()+ ")";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void update(UserFilm uf) {
        try {
            String sql = "UPDATE user_films SET favorito=" + uf.isFavorito() + ", pretende_ver=" + uf.isPretende_ver() + ", assistiu=" + uf.isAssistiu() + ", nota=" + uf.getNota() + " WHERE userdb_id = " + uf.getUser_id() + " AND imdb_id='" + uf.getImde_id() + "'";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserFilm> getById(int user_id) {
        return findUserFilme("SELECT * FROM user_films WHERE userdb_id = " + user_id);
    }
    
    public UserFilm getByFilme(int user_id, String imdb) {
        ArrayList<UserFilm> list = findUserFilme("SELECT * FROM user_films WHERE userdb_id = " + user_id + " AND imdb_id = '" + imdb +"';");
        
        if (list != null && !list.isEmpty()) return list.get(0);
        else return null;
    }    

    public ArrayList<UserFilm> getFavoritos(int user_id) {
        return findUserFilme("SELECT * FROM user_films WHERE userdb_id = " + user_id + " AND favorito=true");
    }

    public ArrayList<UserFilm> getPretendidos(int user_id) {
        return findUserFilme("SELECT * FROM user_films WHERE userdb_id = " + user_id + " AND assistiu=false AND pretende_ver=true");
    }

    public ArrayList<UserFilm> getAssistidos(int user_id) {
        return findUserFilme("SELECT * FROM user_films WHERE userdb_id = " + user_id + " AND assistiu=true");
    }

    public ArrayList<UserFilm> getMaisFavoritados() {
        return findMaisFilmes("favorito", "");
    }

    public ArrayList<UserFilm> getMaisVisto() {
        return findMaisFilmes("assistiu", "");
    }

    public ArrayList<UserFilm> getMaispretendidos() {
        return findMaisFilmes("pretende_ver", "");
    }
    
    public Filme getQtds(Filme filme) {
        
        ArrayList<UserFilm> ufl = new ArrayList();
        
        ufl = findMaisFilmes("favorito", filme.getId());
        
        if (ufl != null && !ufl.isEmpty())
            filme.setQtdFav(ufl.get(0).getNota());
        
        ufl = findMaisFilmes("assistiu", filme.getId());
        
        if (ufl != null && !ufl.isEmpty())
            filme.setQtdAss(ufl.get(0).getNota());
        
        ufl = findMaisFilmes("pretende_ver", filme.getId());
        
        if (ufl != null && !ufl.isEmpty())
            filme.setQtdPret(ufl.get(0).getNota());
        
        ufl = melhoresFilmes(filme.getId());
        
        if (ufl != null && !ufl.isEmpty())
            filme.setQtdNota(ufl.get(0).getMedia());
        
        return filme;
    }
    
    public ArrayList<UserFilm> melhoresFilmes(String imdb) {
        
        if (!imdb.isEmpty()) imdb = " AND imdb_id = '"+ imdb +"'";
        
        try {
            String sql = "SELECT imdb_id, SUM(nota)/COUNT(nota) as nota_final FROM user_films WHERE NOT(nota IS NULL)"+ imdb +" GROUP BY imdb_id ORDER BY nota_final DESC";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<UserFilm> list = new ArrayList<>();
            while (rs.next()) {
                UserFilm f = new UserFilm();
                f.setImde_id(rs.getString("imdb_id"));
                f.setMedia(rs.getFloat("nota_final"));
                list.add(f);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<UserFilm> findUserFilme(String sql) {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<UserFilm> list = new ArrayList<>();
            while (rs.next()) {
                UserFilm uf = new UserFilm();
                uf.setUser_id(rs.getInt("userdb_id"));
                uf.setImde_id(rs.getString("imdb_id"));
                uf.setFavorito(rs.getBoolean("favorito"));
                uf.setPretende_ver(rs.getBoolean("pretende_ver"));
                uf.setAssistiu(rs.getBoolean("assistiu"));
                uf.setNota(rs.getInt("nota"));
                list.add(uf);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<UserFilm> findMaisFilmes(String coluna, String imdb) {
        
        if (!imdb.isEmpty()) imdb = " AND imdb_id = '"+ imdb +"'";
        
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT imdb_id, COUNT(" + coluna + ") as contagem  FROM user_films WHERE " + coluna + " = true"+ imdb +" GROUP BY imdb_id ORDER BY contagem DESC;");
            ArrayList<UserFilm> list = new ArrayList<>();
            while (rs.next()) {
                UserFilm uf = new UserFilm();
                uf.setImde_id(rs.getString("imdb_id"));
                uf.setNota(rs.getInt("contagem"));
                list.add(uf);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}