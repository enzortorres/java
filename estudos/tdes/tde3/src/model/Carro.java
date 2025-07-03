package model;

public class Carro extends Veiculo {
    private int portas;
    private double IMPOSTO_IPVA = 0.04;

    public Carro(String placa, String marca, String modelo, double valor, int portas) {
        super(placa, marca, modelo, valor);
        this.portas = portas;
    }

    @Override
    public double calcularIPVA() {
        return valor * IMPOSTO_IPVA;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Portas: " + portas);
    }
}
