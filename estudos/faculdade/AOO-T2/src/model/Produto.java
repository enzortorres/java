package model;

import java.util.ArrayList;

public class Produto {
    private int idProduto;
    private String nome, situacao;
    private ArrayList<Item> itens;

    public Produto(int idProduto, String nome, String situacao) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.situacao = situacao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void appendItem(Item item) {
        this.itens.add(item);
    }

    public void removeItem(String nomeItem) {
        if (itens.indexOf(nomeItem) != 1) {
            itens.remove(itens.indexOf(nomeItem));
            System.out.println("Item removido com sucesso.");
            return;
        } 
        System.out.println("Item não encontrado.");
    }
}
