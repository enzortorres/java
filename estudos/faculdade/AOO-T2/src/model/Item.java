package model;

public class Item {
    private int idItem;
    private String nome;

    public Item(int idItem, String nome) {
        this.idItem = idItem;
        this.nome = nome;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
