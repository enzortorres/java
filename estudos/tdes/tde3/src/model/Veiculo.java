package model;

public abstract class Veiculo implements Exibivel {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected double valor;

    public Veiculo(String placa, String marca, String modelo, double valor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
    }

    public abstract double calcularIPVA();

    public String getPlaca() {
        return placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Placa: " + placa);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.printf("Valor: R$ %.2f%n", valor);
        System.out.printf("IPVA: R$ %.2f%n", calcularIPVA());
    }
}
