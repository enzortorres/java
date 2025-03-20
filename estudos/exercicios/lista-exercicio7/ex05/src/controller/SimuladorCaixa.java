package controller;

import dominio.CaixaEletronico;
import java.util.Scanner;

public class SimuladorCaixa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CaixaEletronico conta = new CaixaEletronico(1000);

        System.out.println("Cadastre a sua conta aqui!\n");

        int opcao = 0;
        try {
            while(opcao != 3) {
                System.out.print("Oque deseja fazer?\n1 - Sacar\n2 - Exibir saldo\n3 - Sair\n>>> ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o quanto deseja sacar: ");
                        conta.sacar(scanner.nextDouble());
                        break;
                    case 2:
                        conta.exibirSaldo();
                        break;
                    case 3:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Digite uma opção válida!");
                }
            }
        } catch (Exception e) {
            System.out.println("Digite um valor válido!");
        }

    }
}
