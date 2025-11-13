package model;

public class Pais {
    private int idPais;
    private String nome;

    public Pais(int idPais, String nome) {
        this.idPais = idPais;
        this.nome = nome;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
