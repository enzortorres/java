package model;

public class Transporte {
    private int idTransporte;
    private String tipo;
    private String situacao;
    private Empresa empresaResponsavel;

    public Transporte(int idTransporte, String tipo, String situacao) {
        this.idTransporte = idTransporte;
        this.tipo = tipo;
        this.situacao = situacao;
    }

    public int getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(int idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public void analisar() {
        setSituacao("Em análise");
    }

    public void reprovar() {
        setSituacao("Reprovado");
    }

    public void aprovar() {
        setSituacao("Aprovado");
    }

    public void desbloquear() {
        setSituacao("Em análise");
    }

    public void movimentar() {
        setSituacao("Em movimento");
    }

    public void finalizar() {
        setSituacao("Finalizado");
    }
}
