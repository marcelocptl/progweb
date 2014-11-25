package br.com.Dao;

import br.com.model.User;
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
            String sql = "INSERT INTO user_films(userdb_id, imdb_id)VALUES (" + uf.getUser_id() + ", '" + uf.getImde_id() + ")";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(UserFilm uf) {

        try {
            String sql = "UPDATE userdb SET favotiro=" + uf.isFavorito() + ", pretende_ver=" + uf.isPretende_ver() + ", assistiu=" + uf.isAssistiu() + ", nota=" + uf.getNota() + " WHERE userdb_id = " + uf.getUser_id() + " AND imdb_id='" + uf.getImde_id() + "'";

            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserFilm> getById(int user_id) {
        try {
            String sql = "SELECT * FROM user_films WHERE id = '" + user_id + "'";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<UserFilm> list = new ArrayList<>();
            while (rs.next()) {
                list.add(setUserFilme(rs));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserFilm> getFavoritos(int user_id) {
        try {
            String sql = "SELECT * FROM user_films WHERE id = '" + user_id + "' AND favorito=true";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<UserFilm> list = new ArrayList<>();
            while (rs.next()) {
                list.add(setUserFilme(rs));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserFilm> getPretendidos(int user_id) {
        try {
            String sql = "SELECT * FROM user_films WHERE id = '" + user_id + "' AND assistiu=false AND pretende_ver=true";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            ArrayList<UserFilm> list = new ArrayList<>();
            while (rs.next()) {
                list.add(setUserFilme(rs));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<UserFilm> melhorarFilmes() {
        try {
            String sql = "SELECT imdb_id, SUM(nota) as nota_final FROM user_films WHERE NOTA != NULL GROUP BY imdb_id ORDER BY nota_final";

            Statement stmt = this.connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<UserFilm> list = new ArrayList<>();
            while (rs.next()) {
                UserFilm f = new UserFilm();
                f.setImde_id(rs.getString("imdb_id"));
                f.setNota(rs.getObject("nota", Integer.class));
                list.add(f);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserFilm setUserFilme(ResultSet rs) throws SQLException {
        UserFilm uf = new UserFilm();
        uf.setUser_id(rs.getInt("userdb_id"));
        uf.setImde_id(rs.getString("imdb_id"));
        uf.setFavorito(rs.getBoolean("favorito"));
        uf.setPretende_ver(rs.getBoolean("pretende_ver"));
        uf.setAssistiu(rs.getBoolean("assistiu"));
        uf.setNota(rs.getObject("nota", Integer.class));
        return uf;
    }
}