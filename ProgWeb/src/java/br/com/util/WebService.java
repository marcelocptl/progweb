package br.com.util;

import br.com.business.FilmesBO;
import br.com.model.Filme;
import br.com.model.UserFilm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebService {

    private WebService() {
    }

    private static String openURL(String url) throws IOException {      
        
        URL end = new URL(url);
        
        URLConnection conn = end.openConnection();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        String content = br.readLine();

        br.close();
        
        return content;
    }

    public static ArrayList<Filme> allFilms(){
        String url = "http://filmssensors.esy.es/webservice/filmes.php?title=%";
        
        try {
            return gerarLista(new JSONObject(openURL(url)));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }    

    public static ArrayList<Filme> getFilmGender(String id){
        String url = "http://filmssensors.esy.es/webservice/filmes.php?genero="+id;
        
        try {                       
            return gerarLista(new JSONObject(openURL(url)));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }    
    
    public static ArrayList getAllGender(){
        String url = "http://filmssensors.esy.es/webservice/genero.php";
        
        try {
            String json = openURL(url);
            
            JSONObject jsonObj = new JSONObject(json);
            
            Iterator<String> itr = jsonObj.keys();

            ArrayList list = new ArrayList();        
            
            while (itr.hasNext()) {
                
                String key = itr.next();
                
                Map item = new HashMap();
                item.put("id", key);
                item.put("name", jsonObj.getString(key));
                
                list.add(item);                    
                
            }
            
            return list;
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }    
    
    public static Filme getFilm(String imdb){
        String url = "http://filmssensors.esy.es/webservice/filmes.php?id="+imdb;
        
        try {
            ArrayList<Filme> list = gerarLista(new JSONObject(openURL(url)));
            
            return list.get(0);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }    
    
    public static ArrayList<Filme> searchFilms(String title){
        
        String url = "http://filmssensors.esy.es/webservice/filmes.php?title="+title;
        
        try {
            
            return gerarLista(new JSONObject(openURL(url)));
            
        } catch (Exception ex) {
            
            System.err.println(ex.getMessage());
            
        }
        
        return null;
    }
    
    public static ArrayList<Filme> recents(){
        
        String url = "http://filmssensors.esy.es/webservice/filmes.php?lancamento=2014";
        
        try {
            
            return gerarLista(new JSONObject(openURL(url)));
            
        } catch (Exception ex) {
            
            System.err.println(ex.getMessage());
            
        }
        
        return null;
    }    
    
    public static ArrayList<Filme> findFilms(ArrayList<UserFilm> list){
        ArrayList<Filme> filmes = new ArrayList();
        for(UserFilm uf : list){
            filmes.add(getFilm(uf.getImde_id()));
        }
        return filmes;
    }
    
    private static ArrayList<Filme> gerarLista(JSONObject json){

        ArrayList<Filme> filmes = new ArrayList();
                
        Iterator<String> itr = json.keys();

        while(itr.hasNext()) {
            
            String aux = itr.next();
            
            JSONObject filmesJSON = json.getJSONObject(aux);
            
            Filme filme = new Filme();
            
            try {
                filme.setId(aux);
            } catch (JSONException e) {}
            
            try {
                filme.setNome(filmesJSON.getString("Nome"));
            } catch (JSONException e) {}
            
            try {
                filme.setSinopse(filmesJSON.getString("Sinopse"));
            } catch (JSONException e) {}
            
            try {
                filme.setLancamento(filmesJSON.getString("Lancamento"));
            } catch (JSONException e) {}
            
            try {
                filme.setImagem(filmesJSON.getString("Imagem"));
            } catch (JSONException e) {}
                
            try {
                
                JSONArray jsonArray = filmesJSON.getJSONArray("Atores");
                
                ArrayList<String> stringList = new ArrayList();

                for (int j=0; j < jsonArray.length(); j++) {
                    
                    JSONObject jsonObject2 = jsonArray.getJSONObject(j);
                    
                    String nome = jsonObject2.getString("Ator");
                    String personagem = jsonObject2.getString("Personagem");
                    
                    if ( (nome == null && nome.isEmpty()) && (personagem != null && !personagem.isEmpty()) ) 
                        nome = personagem;
                    else
                        if (personagem != null && !personagem.isEmpty()) nome += " ("+ personagem +")";
                    
                    stringList.add(nome);                    
                    
                }                
                
                filme.setAtores(stringList);
                                
            } catch (JSONException e) {}
            
            try {
                
                JSONObject jsonObject = filmesJSON.getJSONObject("Diretores");
                
                ArrayList<String> stringList = new ArrayList();
                
                Iterator<String> itr2 = jsonObject.keys();
                
                while(itr2.hasNext()) {

                    stringList.add( jsonObject.getString(itr2.next()) );
                    
                }                
                
                filme.setDiretores(stringList);
                
            } catch (JSONException e) {}                     
            
            try {
                
                JSONObject jsonObject = filmesJSON.getJSONObject("Generos");
                
                ArrayList<String> stringList = new ArrayList();
                
                Iterator<String> itr2 = jsonObject.keys();
                
                while(itr2.hasNext()) {

                    stringList.add( jsonObject.getString(itr2.next()) );
                    
                }                              
                
                filme.setGeneros(stringList);
                
            } catch (JSONException e) {}            
            
            filmes.add(FilmesBO.getQtds(filme));         
            
        }        
        
        return filmes;

    }    
    /*
    private static ArrayList<Filme> allFilms(JSONArray json){

        ArrayList<Filme> filmes = new ArrayList();
        
        for (int i=0; i<json.length(); i++) {
            
            JSONObject filmesJSON = json.getJSONObject(i);
            
            Filme filme = new Filme();
            try {
                filme.setId(filmesJSON.getString("ID"));
            } catch (JSONException e) {}
            
            try {
                filme.setNome(filmesJSON.getString("Nome"));
            } catch (JSONException e) {}
            
            try {
                filme.setSinopse(filmesJSON.getString("Sinopse"));
            } catch (JSONException e) {}
            
            try {
                filme.setLancamento(filmesJSON.getString("Lancamento"));
            } catch (JSONException e) {}
            
            try {
                filme.setImagem(filmesJSON.getString("Imagem"));
            } catch (JSONException e) {}
            
            try {
                JSONArray jsonArray = filmesJSON.getJSONArray("Atores");
                
                ArrayList<String> stringList = new ArrayList();
                
                for (int j=0; j < jsonArray.length(); j++) {
                    
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    
                    String string = jsonObject.getString("Ator");
                    String personagem = jsonObject.getString("Personagem");
                    
                    if (string == null && personagem != null) string = personagem;
                    else
                        if (personagem != null && personagem.isEmpty()) string += " ("+ personagem +")";
                    
                    stringList.add(string);
                    
                }
                
                filme.setAtores(stringList);
            } catch (JSONException e) {}
            
            try {
                JSONObject jsonObject = filmesJSON.getJSONObject("Diretores");
                
                ArrayList<String> stringList = new ArrayList();
                
                Iterator<String> itr = jsonObject.keys();
                
                while(itr.hasNext()) {
                    stringList.add( jsonObject.getString(itr.next()) );
                }                
                
                filme.setDiretores(stringList);
            } catch (JSONException e) {}
            
            try {
                
                JSONArray jsonArray = filmesJSON.getJSONArray("Generos");
                
                ArrayList<String> stringList = new ArrayList();
                
                for (int j=0; j < jsonArray.length(); j++) {
                    
                    stringList.add(jsonArray.getString(j));
                }                               
                
                filme.setGeneros(stringList);
                
            } catch (JSONException e) {}
            
            filmes.add(filme);
            
        }
        
        return filmes;

    }    
    */
}