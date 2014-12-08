
package br.com.business;

import br.com.Dao.UserFilmsDao;
import br.com.jdbc.factory.JDBCDaoFactory;
import br.com.model.Filme;
import br.com.model.UserFilm;
import java.util.ArrayList;

/**
 * @author brucce
 */
public class FilmesBO {
    
    private static final UserFilmsDao userFilms = JDBCDaoFactory.getDaoFactory().getUserFilmsDao();
    
    private FilmesBO(){}
    
    public static void checkMyFilmes(UserFilm userFilm){
        userFilms.save(userFilm);
    }  
    public static void updateMyFilmes(UserFilm userFilm){
        userFilms.update(userFilm);
    }   
    public static UserFilm verifyFilmes(int user_id, String imdb){
        return userFilms.getByFilme(user_id, imdb);
    }    
    public static ArrayList<UserFilm> meusFilmes(int user_id){
        return userFilms.getById(user_id);
    }
    public static ArrayList<UserFilm> meusFavoritos(int user_id){
        return userFilms.getFavoritos(user_id);
    }
    public static ArrayList<UserFilm> meusFilmesPretendidos(int user_id){
        return userFilms.getPretendidos(user_id);
    }
    public static ArrayList<UserFilm> FilmesAssisti(int user_id){
        return userFilms.getAssistidos(user_id);
    }
    public static ArrayList<UserFilm> melhorClassificado(){
        return userFilms.melhoresFilmes("");
    }
    public static ArrayList<UserFilm> maisAssistidos(){
        return userFilms.getMaisVisto();
    }
    public static ArrayList<UserFilm> maisPretendidos(){
        return userFilms.getMaispretendidos();
    }
    public static ArrayList<UserFilm> maisFavoritados(){
        return userFilms.getMaisFavoritados();
    }
    public static Filme getQtds(Filme filme){
        return userFilms.getQtds(filme);
    }    
}
