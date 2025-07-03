package model;

public class Moto extends Veiculo {
    private int cilindradas;
    private double IMPOSTO_IPVA = 0.02;

    public Moto(String placa, String marca, String modelo, double valor, int cilindradas) {
        super(placa, marca, modelo, valor);
        this.cilindradas = cilindradas;
    }

    @Override
    public double calcularIPVA() {
        return valor * IMPOSTO_IPVA;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Cilindradas: " + cilindradas);
    }
}
