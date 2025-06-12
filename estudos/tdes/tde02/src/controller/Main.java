package controller;

import java.util.Scanner;
import java.util.ArrayList;

import models.Veiculo;
import models.Carro;
import models.Moto;
import models.Caminhao;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Veiculo> veiculos = new ArrayList<>();

        int op;
        do {
            System.out.println("\n1 - Cadastrar novo veículo");
            System.out.println("2 - Listar veículos");
            System.out.println("3 - Sair");
            System.out.print(">> ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Tipo (1-Carro, 2-Moto, 3-Caminhão): ");
                    int tipo = sc.nextInt();

                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();

                    switch (tipo) {
                        case 1:
                            System.out.print("Qtd de portas: ");
                            int portas = sc.nextInt();
                            veiculos.add(new Carro(placa, modelo, ano, portas));
                            break;
                        case 2:
                            System.out.print("Cilindrada: ");
                            int cilindrada = sc.nextInt();
                            veiculos.add(new Moto(placa, modelo, ano, cilindrada));
                            break;
                        case 3:
                            System.out.print("Capacidade de carga (em toneladas): ");
                            double carga = sc.nextDouble();
                            veiculos.add(new Caminhao(placa, modelo, ano, carga));
                            break;
                        default:
                            System.out.println("Tipo inválido.");
                    }
                    break;
                case 2:
                    if (veiculos.isEmpty()) {
                        System.out.println("Nenhum veículo cadastrado.");
                    } else {
                        for (Veiculo v : veiculos) {
                            v.exibirDados();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (op != 3);

        sc.close();
    }
}
