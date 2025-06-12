package models;

public class Moto extends Veiculo {
    private int cilindrada;

    public Moto(String placa, String modelo, int ano, int cilindrada) {
        super(placa, modelo, ano);
        this.cilindrada = cilindrada;
    }

    public void exibirDados() {
        System.out.println("Moto - Modelo: " + modelo + " | Placa: " + placa + " | Ano: " + ano + " | Cilindrada: " + cilindrada + "cc");
    }
}
