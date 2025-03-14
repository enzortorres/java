package controller;
import dominio.Produto;

public class ControleProduto {
    public static void main(String[] args) {
        Produto prod = new Produto(123, "Vaca", 100);

        System.out.println(prod.getCodigo()); // ? Metodo para puxar o valor do codigo

        prod.setCodigo(55); // ? Metodo para setar um novo valor do codigo

        System.out.println(prod.getCodigo()); // ? Puxar o valor novo do codigo

        System.out.println();
        prod.listarProduto();


    }
}
