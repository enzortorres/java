package dominio;

public class Aluno {
    private String nome;
    private double nota;

    public Aluno() {}

    public boolean validarNota() {
        if (nota >= 0 && nota <= 10) {
            return true;
        }
        return false;
    }

    public void classificarAluno() {
        if (nota < 5) {
            System.out.println("Reprovado!");
        } else if (nota < 7) {
            System.out.println("Recuperação!");
        } else {
            System.out.println("Aprovado!");
        }
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
