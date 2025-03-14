package dominio;

public class Produto {
    public int codigo; // ? Atributo da classe
    public String descricao; // ? Atributo da classe
    public double valor; // ? Atributo da classe

    public Produto(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void listarProduto() {
        System.out.println(codigo);
        System.out.println(descricao);
        System.out.println(valor);
    }
}
