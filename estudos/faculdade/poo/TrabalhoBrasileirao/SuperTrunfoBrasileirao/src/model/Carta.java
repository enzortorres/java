package model;

public class Carta {
    private int id;
    private String codigo;
    private String nome;
    private String posicao;
    private int gols;
    private int titulos;
    private int golsSelecao;
    private int valorPico;
    private int premios;
    private boolean superTrunfo;
    private String wikiPage;

    public Carta(int id, String codigo, String nome, String posicao,
                 int gols, int titulos, int golsSelecao, int valorPico,
                 int premios, boolean superTrunfo, String wikiPage) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.posicao = posicao;
        this.gols = gols;
        this.titulos = titulos;
        this.golsSelecao = golsSelecao;
        this.valorPico = valorPico;
        this.premios = premios;
        this.superTrunfo = superTrunfo;
        this.wikiPage = wikiPage;
    }

    public int getId()             { return id; }
    public String getCodigo()      { return codigo; }
    public String getNome()        { return nome; }
    public String getPosicao()     { return posicao; }
    public int getGols()           { return gols; }
    public int getTitulos()        { return titulos; }
    public int getGolsSelecao()    { return golsSelecao; }
    public int getValorPico()      { return valorPico; }
    public int getPremios()        { return premios; }
    public boolean isSuperTrunfo() { return superTrunfo; }
    public String getWikiPage()    { return wikiPage; }

    @Override
    public String toString() {
        return codigo + " - " + nome + (superTrunfo ? " ★" : "");
    }
}
