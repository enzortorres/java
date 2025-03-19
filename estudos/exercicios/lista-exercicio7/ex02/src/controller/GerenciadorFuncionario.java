package controller;

import dominio.Funcionario;
import java.util.Scanner;

public class GerenciadorFuncionario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Funcionario func = new Funcionario();

        System.out.print("Digite o nome do funcionário: ");
        func.setNome(scanner.nextLine());
        System.out.print("Digite o sálario base do funcionário: ");
        func.setSalarioBase(scanner.nextDouble());
        System.out.print("Digite a categoria do funcionário: ");
        func.setCategoria(scanner.next());

        System.out.printf("Nome: %s\nSalário base: R$%.2f\nSalário com bônus da categoria %s: R$%.2f", func.getNome(), func.getSalarioBase(), func.getCategoria(),func.calcularSalarioFinal());
    }
}
