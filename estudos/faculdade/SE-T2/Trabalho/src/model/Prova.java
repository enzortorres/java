package model;

public class Prova {
    private int codigo;
    private String data;
    private int peso;
    private Disciplina disciplina;
    private Professor professor;

    public Prova(int codigo, String data, int peso, Disciplina disciplina, Professor professor) {
        this.codigo = codigo;
        this.data = data;
        this.peso = peso;
        this.disciplina = disciplina;
        this.professor = professor;
    }

    
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }

    public void disponibilizar() {
        System.out.println("Prova " + codigo + " disponibilizada.");
    }

    public void aplicar() {
        System.out.println("Prova " + codigo + " aplicada.");
    }

    public void corrigir() {
        System.out.println("Prova " + codigo + " corrigida.");
    }

    @Override
    public String toString() {
        String discNome = disciplina != null ? disciplina.getNome() : "N/A";
        String profNome = professor != null ? professor.getNome() : "N/A";
        return "Código:     " + codigo +
               "\nData:       " + data +
               "\nPeso:       " + peso +
               "\nDisciplina: " + discNome +
               "\nProfessor:  " + profNome;
    }
}
