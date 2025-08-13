package model;

public class Trapezio implements Poligono {
    private Double baseMenor;
    private Double baseMaior;
    private Double altura;

    public Trapezio(Double baseMenor, Double baseMaior, Double altura) {
        this.baseMenor = baseMenor;
        this.baseMaior = baseMaior;
        this.altura = altura;
    }

    public Double calcularArea() {
        return (baseMaior + baseMenor) * (altura / 2);
    }

    public Double getBaseMaior() {
        return baseMaior;
    }

    public void setBaseMaior(Double baseMaior) {
        this.baseMaior = baseMaior;
    }

    public Double getBaseMenor() {
        return baseMenor;
    }

    public void setBaseMenor(Double baseMenor) {
        this.baseMenor = baseMenor;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }
}
