package model;

public class Jogador {
    private int numero;
    private String nome, posicao;

    public Jogador(int numero, String nome, String posicao) {
        this.numero = numero;
        this.nome = nome;
        this.posicao = posicao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
}
