package controller;

import model.Computador;
import model.Tomate;

public class CalculadoraImposto {
    public void calcularImpostoComputador(Computador computador) {
        System.out.println("Relatório do imposto do computador");
        double imposto = computador.calcularImposto();
        System.out.println("Computador " + computador.getNome());
        System.out.println("Valor " + computador.getValor());
        System.out.println("Imposto a ser pago " + imposto);
    }

    public void calcularImpostoTomate(Tomate tomate) {
        System.out.println("Relatório do imposto do tomate");
        double imposto = tomate.calcularImposto();
        System.out.println("Tomate " + tomate.getNome());
        System.out.println("Valor " + tomate.getValor());
        System.out.println("Imposto a ser pago " + imposto);
    }
}
