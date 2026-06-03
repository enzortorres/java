package model;

public class Carta {
    private int id;
    private String codigo;
    private String nome;
    private String continente;
    private int titulos;
    private int gols;
    private int participacoes;
    private int pontosFifa;
    private int jogadoresEstrela;
    private boolean superTrunfo;
    private String paisCodigo;

    public Carta(int id, String codigo, String nome, String continente,
                 int titulos, int gols, int participacoes, int pontosFifa,
                 int jogadoresEstrela, boolean superTrunfo, String paisCodigo) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.continente = continente;
        this.titulos = titulos;
        this.gols = gols;
        this.participacoes = participacoes;
        this.pontosFifa = pontosFifa;
        this.jogadoresEstrela = jogadoresEstrela;
        this.superTrunfo = superTrunfo;
        this.paisCodigo = paisCodigo;
    }

    public int getId()                  { return id; }
    public String getCodigo()           { return codigo; }
    public String getNome()             { return nome; }
    public String getContinente()       { return continente; }
    public int getTitulos()             { return titulos; }
    public int getGols()                { return gols; }
    public int getParticipacoes()       { return participacoes; }
    public int getPontosFifa()          { return pontosFifa; }
    public int getJogadoresEstrela()    { return jogadoresEstrela; }
    public boolean isSuperTrunfo()      { return superTrunfo; }
    public String getPaisCodigo()       { return paisCodigo; }

    @Override
    public String toString() {
        return codigo + " - " + nome + (superTrunfo ? " ★" : "");
    }
}
