package controller;

import dominio.Elevador;
import java.util.Scanner;
import java.util.concurrent.Delayed;

public class SimuladorElevador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Elevador elevador = new Elevador(0, 5);

        int opcao = 0;
        System.out.println("Bem vindo ao elevador!");
        do {
            System.out.print("Oque deseja realizar?\n1 - Subir um andar\n2 - Descer um andar\n3 - Exibir o andar atual\n4 - Sair do elevador\n>>> ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    elevador.subir();
                    break;
                case 2:
                    elevador.descer();
                    break;
                case 3:
                    elevador.exibirAndar();
                    break;
                case 4:
                    System.out.println("Saindo do elavador...");
                    break;
                default:
                    System.out.println("Digite um valor válido!");
            }
        } while (opcao != 4);
    }
}
