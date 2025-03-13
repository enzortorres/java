package dominio;

public class Produto {
    public int codigo;
    public String descricao;
    public double valor;

    public void listarProduto() {
        System.out.println(codigo);
        System.out.println(descricao);
        System.out.println(valor);
    }
}
