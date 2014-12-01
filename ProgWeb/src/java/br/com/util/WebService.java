package br.com.util;

import br.com.model.Filme;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
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
        //while ((inputLine = br.readLine()) != null) {
        //    content += inputLine + "\n";
        //}
        br.close();
        return content;
    }

    public static ArrayList<Filme> allFilms(){
        String url = "http://filmssensors.esy.es/";
        
        try {
            return allFilms(new JSONArray(openURL(url)));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }    
    
    public static Filme getFilm(String imdb){
        String url = "http://filmssensors.esy.es/filmes.php?id="+imdb;
        
        try {
            ArrayList<Filme> list = searchFilms(new JSONObject(openURL(url)));
            
            return list.get(0);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }    
    
    public static ArrayList<Filme> searchFilms(String title){
        String url = "http://filmssensors.esy.es/filmes.php?title="+title;
        
        try {
            return searchFilms(new JSONObject(openURL(url)));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }
    
    public static ArrayList<Filme> recents(){
        String url = "http://filmssensors.esy.es/filmes.php?lancamento=2014";
        
        try {
            return searchFilms(new JSONObject(openURL(url)));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
        return null;
    }    
    
    private static ArrayList<Filme> searchFilms(JSONObject json){

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
                filme.setNome(filmesJSON.getString("nome"));
            } catch (JSONException e) {}
            
            try {
                filme.setSinopse(filmesJSON.getString("sinopse"));
            } catch (JSONException e) {}
            
            try {
                filme.setLancamento(filmesJSON.getString("lancamento"));
            } catch (JSONException e) {}
            
            try {
                filme.setImagem(filmesJSON.getString("imagem"));
            } catch (JSONException e) {}
                
            try {
                
                JSONObject jsonObject = filmesJSON.getJSONObject("atores");
                
                ArrayList<String> stringList = new ArrayList();
                
                Iterator<String> itr2 = jsonObject.keys();

                while(itr.hasNext()) {
                    
                    JSONObject jsonObject2 = jsonObject.getJSONObject(itr2.next());
                    
                    String nome = jsonObject2.getString("nome");
                    String personagem = jsonObject2.getString("personagem");
                    
                    if (nome == null && personagem != null) nome = personagem;
                    else
                        if (personagem != null && personagem.isEmpty()) nome += " ("+ personagem +")";
                    
                    stringList.add(nome);                    
                    
                }                
                
                filme.setAtores(stringList);
            } catch (JSONException e) {}
            
            try {
                
                JSONObject jsonObject = filmesJSON.getJSONObject("diretores");
                
                ArrayList<String> stringList = new ArrayList();
                
                Iterator<String> itr2 = jsonObject.keys();
                
                while(itr.hasNext()) {
                    
                    JSONObject jsonObject2 = jsonObject.getJSONObject(itr2.next());    
                    
                    stringList.add( jsonObject2.getString("nome") );
                    
                }                
                
                filme.setDiretores(stringList);
                
            } catch (JSONException e) {}                     
            
            filmes.add(filme);         
            
        }        
        
        return filmes;

    }    
    
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
}