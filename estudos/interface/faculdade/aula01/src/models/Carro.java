package models;

import view.Veiculo;

public class Carro extends Veiculo {
    protected int portas;

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public Carro(String placa, String modelo, String cor, int portas) {
        super(placa, modelo, cor);
        this.portas = portas;
    }
}
