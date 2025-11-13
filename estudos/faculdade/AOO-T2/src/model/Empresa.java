package model;

public class Empresa {
    private int idEmpresa;
    private String nome;
    private Pais paisOrigem;

    public Empresa(int idEmpresa, String nome, Pais paisOrigem) {
        this.idEmpresa = idEmpresa;
        this.nome = nome;
        this.paisOrigem = paisOrigem;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(Pais paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
}
