package model;

public class Professor {
    private String matricula;
    private int registro;
    private String nome;
    private String especialidade;

    public Professor(String matricula, int registro, String nome, String especialidade) {
        this.matricula = matricula;
        this.registro = registro;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public int getRegistro() { return registro; }
    public void setRegistro(int registro) { this.registro = registro; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    @Override
    public String toString() {
        return "Matrícula:     " + matricula +
               "\nRegistro:      " + registro +
               "\nNome:          " + nome +
               "\nEspecialidade: " + especialidade;
    }
}
