package models;

import view.Veiculo;

public class Moto extends Veiculo {
    private Double cilindrada;

    public Double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(Double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Moto (String placa, String modelo, String cor, Double cilindrada) {
        super(placa, modelo, cor);
        this.cilindrada = cilindrada;
    }
}
