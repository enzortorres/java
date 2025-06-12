package models;

public class Caminhao extends Veiculo {
    private double capacidadeCarga;

    public Caminhao(String placa, String modelo, int ano, double capacidadeCarga) {
        super(placa, modelo, ano);
        this.capacidadeCarga = capacidadeCarga;
    }

    public void exibirDados() {
        System.out.println("Caminhão - Modelo: " + modelo + " | Placa: " + placa + " | Ano: " + ano + " | Capacidade de carga: " + capacidadeCarga + " toneladas");
    }
}
