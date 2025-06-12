package models;

public class Automovel {
    public String placa, modelo, marca;
    public int ano;
    public double valor;

    public Automovel(String placa, String modelo, String marca, int ano, double valor) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.valor = valor;
    }

    public String toCSV() {
        return placa + "," + modelo + "," + marca + "," + ano + "," + valor;
    }

    public void imprimir() {
        System.out.println("Placa: " + placa + ", Modelo: " + modelo + ", Marca: " + marca + ", Ano: " + ano + ", Valor: " + valor);
    }
}
