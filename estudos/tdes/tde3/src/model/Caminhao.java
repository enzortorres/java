package model;

public class Caminhao extends Veiculo {
    private int eixos;
    private double IMPOSTO_IPVA = 0.02;

    public Caminhao(String placa, String marca, String modelo, double valor, int eixos) {
        super(placa, marca, modelo, valor);
        this.eixos = eixos;
    }

    @Override
    public double calcularIPVA() {
        return valor * IMPOSTO_IPVA;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Eixos: " + eixos);
    }
}
