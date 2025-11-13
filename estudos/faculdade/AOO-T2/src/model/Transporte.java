package model;

public class Transporte {
    private int idTransporte;
    private String tipo;
    private String situacao;

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
}
