package model;

public class Cliente {
    private String cpf, nome;
    private Animal[] animais;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        animais = new Animal[3];
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
        int i = animais.length;
        if(i > 2) {
            System.out.println("Lista de animais cheia!");
            return;
        }
        animais[i] = novoAnimal;
        novoAnimal.setCliente(this);
    }

    public void removeAnimal(Animal exAnimal) {
        for (int i = 0; i < 3; i++) {
            if(animais[i].equals(exAnimal)) {
                animais[i] = null;
                exAnimal.setCliente(null);
            }
        }
    }

    public Animal[] getAnimais() {
        return animais;
    }
}
