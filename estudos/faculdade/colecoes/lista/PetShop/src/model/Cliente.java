package model;

import java.util.Vector;

public class Cliente {
    private String cpf, nome;
    private Vector<Animal> animais;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        animais = new Vector<>();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addAnimal(Animal novoAnimal) {
        if(animais.contains(novoAnimal)) return;
        animais.add(novoAnimal);
        novoAnimal.setCliente(this);
    }

    public void removeAnimal(Animal exAnimal) {
        if(!animais.contains(exAnimal)) return;
        animais.remove(exAnimal);
        exAnimal.setCliente(null);
    }

    public Vector<Animal> getAnimais() {
        return animais;
    }
}