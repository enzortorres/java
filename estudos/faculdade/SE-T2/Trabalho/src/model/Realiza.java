package model;

public class Realiza {
    private Aluno aluno;
    private Prova prova;
    private float nota;

    public Realiza(Aluno aluno, Prova prova, float nota) {
        this.aluno = aluno;
        this.prova = prova;
        this.nota = nota;
    }

    
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Prova getProva() { return prova; }
    public void setProva(Prova prova) { this.prova = prova; }

    public float getNota() { return nota; }
    public void setNota(float nota) { this.nota = nota; }

    @Override
    public String toString() {
        return "Realiza{aluno='" + aluno.getNome() + "', prova=" + prova.getCodigo() +
               ", nota=" + nota + "}";
    }
}
