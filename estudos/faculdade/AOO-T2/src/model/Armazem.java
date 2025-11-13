package model;

public class Armazem {
    private int idArmazem;
    private String nome;

    public Armazem(int idArmazem, String nome) {
        this.idArmazem = idArmazem;
        this.nome = nome;
    }

    public int getIdArmazem() {
        return idArmazem;
    }

    public void setIdArmazem(int idArmazem) {
        this.idArmazem = idArmazem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
