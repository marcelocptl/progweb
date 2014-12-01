package br.com.model;

import java.util.ArrayList;

public class Filme {
    
    private String imdb_id;
    private String nome;
    private ArrayList<String> atores;
    private ArrayList<String> diretores;
    private ArrayList<String> generos;
    private String sinopse;
    private String lancamento;
    private String imagem;

    public Filme(String imdb_id, String nome, ArrayList<String> atores, ArrayList<String> diretores, ArrayList<String> generos, String sinopse, String lancamento, String imagem) {
        this.imdb_id = imdb_id;
        this.nome = nome;
        this.atores = atores;
        this.diretores = diretores;
        this.generos = generos;
        this.sinopse = sinopse;
        this.lancamento = lancamento;
        this.imagem = imagem;
    }

    public Filme() {
        this.imdb_id = null;
        this.nome = null;
        this.atores = null;
        this.diretores = null;
        this.generos = null;
        this.sinopse = null;
        this.lancamento = null;
        this.imagem = null;        
    }           

    @Override
    public String toString() {
        return "Filme{" + "imdb_id=" + imdb_id + ", nome=" + nome + ", atores=" + atores + ", diretores=" + diretores + ", generos=" + generos + ", sinopse=" + sinopse + ", lancamento=" + lancamento + ", imagem=" + imagem + '}';
    }        

    public String getId() {
        return imdb_id;
    }

    public void setId(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getAtores() {
        return atores;
    }

    public void setAtores(ArrayList<String> atores) {
        this.atores = atores;
    }

    public ArrayList<String> getDiretores() {
        return diretores;
    }

    public void setDiretores(ArrayList<String> diretores) {
        this.diretores = diretores;
    }

    public ArrayList<String> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<String> generos) {
        this.generos = generos;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }        
    
}
