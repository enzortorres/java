package model;

public class Aluno {
    private String matricula;
    private String nome;
    private String endereco;
    private String telefone;

    public Aluno(String matricula, String nome, String endereco, String telefone) {
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Matrícula: " + matricula +
               "\nNome:      " + nome +
               "\nEndereço:  " + endereco +
               "\nTelefone:  " + telefone;
    }
}
