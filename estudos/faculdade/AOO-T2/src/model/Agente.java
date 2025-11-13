package model;

public class Agente {
    private int idAgente;
    private String nome, situacao;
    private Empresa empresaResponsavel;

    public Agente(int idAgente, String nome, String situacao, Empresa empresaResponsavel) {
        this.idAgente = idAgente;
        this.nome = nome;
        this.situacao = situacao;
        this.empresaResponsavel = empresaResponsavel;
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

    public Empresa getEmpresaResponsavel() {
        return empresaResponsavel;
    }

    public void setEmpresaResponsavel(Empresa empresaResponsavel) {
        this.empresaResponsavel = empresaResponsavel;
    }
}
