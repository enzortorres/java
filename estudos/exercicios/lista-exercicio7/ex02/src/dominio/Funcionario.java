package dominio;

public class Funcionario {
    private String nome;
    private double salarioBase;
    private String categoria;

    public Funcionario() {

    }

    public double calcularSalarioFinal() {
        double bonus = 0;
        switch (categoria) {
            case "A", "a":
                bonus = salarioBase * 0.20;
                break;
            case "B", "b":
                bonus = salarioBase * 0.10;
                break;
            case "C", "c":
                bonus = 0;
                break;
            default:
                System.out.println("Categoria inválida!");
        }
        System.out.printf("Bonûs de R$%.2f adicionado ao sálario base!\n", bonus);
        return salarioBase += bonus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
