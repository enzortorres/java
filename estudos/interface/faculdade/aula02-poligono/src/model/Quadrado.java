package model;

public class Quadrado implements Poligono {
    private Double lado;

    public Quadrado(Double lado) {
        this.lado = lado;
    }

    public Double calcularArea() {
        return lado * lado;
    }

    public Double getLado() {
        return lado;
    }

    public void setLado(Double lado) {
        this.lado = lado;
    }
}
