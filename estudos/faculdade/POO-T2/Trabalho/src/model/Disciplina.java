package model;

public class Disciplina {
    private int codigo;
    private String nome;
    private int credito;
    private int cargaHoraria;

    public Disciplina(int codigo, String nome, int credito, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.credito = credito;
        this.cargaHoraria = cargaHoraria;
    }

    
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCredito() { return credito; }
    public void setCredito(int credito) { this.credito = credito; }

    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    @Override
    public String toString() {
        return "Código:        " + codigo +
               "\nNome:          " + nome +
               "\nCréditos:      " + credito +
               "\nCarga Horária: " + cargaHoraria + "h";
    }
}
