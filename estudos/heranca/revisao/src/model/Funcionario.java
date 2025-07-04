package model;

import com.sun.security.jgss.GSSUtil;

public class Funcionario extends Pessoa {
    private double salario;

    public Funcionario(String nome) {
        super(nome);
    }

    public void imprime() {
        super.imprime();
        System.out.printf("R$%.2f",this.salario);
    }

    public void relatorioPagamento() {
        System.out.println("Eu " + this.nome + "recebi o salário de " + this.salario);
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
