package controller;
import dominio.Produto;

public class ControleProduto {
    public static void main(String[] args) {
        Produto prod = new Produto();

        prod.codigo = 1234;
        prod.descricao = "Peixe";
        prod.valor = 150;
        prod.listarProduto();
    }
}
