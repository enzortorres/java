package model;

public class Agente {
    private int idAgente;
    private String nome, situacao, funcao;

    public Agente(int idAgente, String nome, String situacao) {
        this.idAgente = idAgente;
        this.nome = nome;
        this.situacao = situacao;
    }

    public int getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void analisar() {
        setSituacao("Em análise");
    }

    public void reprovar() {
        setSituacao("Reprovado");
    }

    public void aprovar() {
        setSituacao("Aprovado");
    }

    public void atribuir(String funcao) {
        setFuncao(funcao);
    }

    public void transportar() {
        setSituacao("Em transporte");
    }

    public void receber() {
        setSituacao("Recebido");
    }
}
