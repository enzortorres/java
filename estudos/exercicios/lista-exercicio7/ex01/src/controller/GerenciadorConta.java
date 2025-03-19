package controller;

import dominio.ContaBancaria;
import java.util.Scanner;

public class GerenciadorConta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nome = "";

        System.out.println("Cadastre sua conta já!");
        System.out.print("Digite seu nome completo: ");
        nome = scanner.nextLine();

        ContaBancaria conta = new ContaBancaria(nome, 0);

        int opc = 0;

        do {
            System.out.print("Quão operação deseja realizar?\n1 - Depositar\n2 - Sacar\n3 - Exibir saldo\n4 - Sair\n>>> ");
            opc = scanner.nextInt();

            switch (opc) {
                case 1:
                    System.out.print("Quanto deseja depositar? ");
                    conta.depositar(scanner.nextDouble());
                    break;
                case 2:
                    System.out.print("Quando deseja sacar? ");
                    conta.sacar(scanner.nextDouble());
                    break;
                case 3:
                    conta.exibirSaldo();
                    break;
                default:
                    System.out.println("Saindo do sistema...");
            }

        } while (opc != 4);

    }
}
