package model;

public class Carta {
    private int id;
    private String codigo;
    private String nome;
    private String continente;
    private int biodiversidade;
    private int economia;
    private int territorio;
    private int populacao;
    private int esportes;
    private boolean superTrunfo;
    private String paisCodigo;

    public Carta(int id, String codigo, String nome, String continente,
                 int biodiversidade, int economia, int territorio, int populacao,
                 int esportes, boolean superTrunfo, String paisCodigo) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.continente = continente;
        this.biodiversidade = biodiversidade;
        this.economia = economia;
        this.territorio = territorio;
        this.populacao = populacao;
        this.esportes = esportes;
        this.superTrunfo = superTrunfo;
        this.paisCodigo = paisCodigo;
    }

    public int getId()               { return id; }
    public String getCodigo()        { return codigo; }
    public String getNome()          { return nome; }
    public String getContinente()    { return continente; }
    public int getBiodiversidade()   { return biodiversidade; }
    public int getEconomia()         { return economia; }
    public int getTerritorio()       { return territorio; }
    public int getPopulacao()        { return populacao; }
    public int getEsportes()         { return esportes; }
    public boolean isSuperTrunfo()   { return superTrunfo; }
    public String getPaisCodigo()    { return paisCodigo; }

    @Override
    public String toString() {
        return codigo + " - " + nome + (superTrunfo ? " ★" : "");
    }
}
