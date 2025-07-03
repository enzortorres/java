package view;

import model.*;
import controller.*;
import model.excecao.*;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VeiculoController controller = new VeiculoController();
        int opcao;

        do {
            System.out.println("\n=== Sistema de Cadastro de Veículos ===");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Listar Veículos");
            System.out.println("3. Atualizar Veículo");
            System.out.println("4. Remover Veículo");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Tipo (1-Carro, 2-Moto, 3-Caminhão): ");
                        int tipo = sc.nextInt(); sc.nextLine();
                        System.out.print("Placa: ");
                        String placa = sc.nextLine();
                        System.out.print("Marca: ");
                        String marca = sc.nextLine();
                        System.out.print("Modelo: ");
                        String modelo = sc.nextLine();
                        System.out.print("Valor do veículo: ");
                        double valor = sc.nextDouble(); sc.nextLine();

                        switch (tipo) {
                            case 1 -> {
                                System.out.print("Portas: ");
                                int portas = sc.nextInt(); sc.nextLine();
                                controller.adicionarVeiculo(new Carro(placa, marca, modelo, valor, portas));
                            }
                            case 2 -> {
                                System.out.print("Cilindradas: ");
                                int cilindradas = sc.nextInt(); sc.nextLine();
                                controller.adicionarVeiculo(new Moto(placa, marca, modelo, valor, cilindradas));
                            }
                            case 3 -> {
                                System.out.print("Eixos: ");
                                int eixos = sc.nextInt(); sc.nextLine();
                                controller.adicionarVeiculo(new Caminhao(placa, marca, modelo, valor, eixos));
                            }
                            default -> throw new EntradaInvalidaException("Tipo inválido!");
                        }
                    }
                    case 2 -> {
                        for (Veiculo v : controller.listarVeiculos()) {
                            v.exibirDetalhes();
                            System.out.println("-----------------------");
                        }
                    }
                    case 3 -> {
                        System.out.print("Placa do veículo: ");
                        String placa = sc.nextLine();
                        System.out.print("Nova Marca: ");
                        String novaMarca = sc.nextLine();
                        System.out.print("Novo Modelo: ");
                        String novoModelo = sc.nextLine();
                        controller.atualizarVeiculo(placa, novaMarca, novoModelo);
                    }
                    case 4 -> {
                        System.out.print("Placa do veículo: ");
                        String placa = sc.nextLine();
                        controller.removerVeiculo(placa);
                        System.out.println("Veículo removido.");
                    }
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (EntradaInvalidaException | VeiculoNaoEncontradoException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 0);

        sc.close();
    }
}
