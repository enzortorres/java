package controller;

import java.util.Scanner;
import java.util.ArrayList;
import model.*;

public class ProgramaTransportadora {

    public static void main(String[] args) {
        MenuAtor();
    }

    public static void MenuAtor() {
        try {
            Scanner sc = new Scanner(System.in);
            int opcao = 0;
            do { 
                System.out.println("=== MENU ATOR ===");
                System.out.println("1- Departamento Operacional");
                System.out.println("2- Departamento Material");
                System.out.println("3- Departamento Logística");
                System.out.println("4- Sair");
                System.out.print("Selecione a opção: ");
                
                opcao = sc.nextInt();
                
                switch (opcao) {
                    case 1 -> DepOperacional();
                    case 2 -> DepMaterial();
                    case 3 -> DepLogistica();
                    case 4 -> System.out.println("Encerrando o programa...");
                    default -> System.out.println("Opção inválida.");
                }
                
            } while (opcao != 4);
            sc.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void DepOperacional() {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        try {
            do {
                System.out.print(
                    "=== DEPARTAMENTO OPERACIONAL ===" + 
                    "Qual ação deseja realizar" + 
                    "1 - Cadastrar País" + 
                    "2 - Cadastrar Cidade" + 
                    "3 - Cadastrar Armazém" + 
                    ">>> ");
                opcao = sc.nextInt();

                switch(opcao) {
                    case 1 -> cadastrarPais();
                    case 2 -> cadastrarCidade();
                    case 3 -> cadastrarArmazem();
                    case 4 ->  {
                        System.out.println("Encerrando o programa..."); 
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } while(opcao != 4);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void DepMaterial() {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        try {
            do {
                System.out.print(
                    "=== DEPARTAMENTO DE MATERIAL ===" + 
                    "Qual ação deseja realizar" + 
                    "1 - Cadastrar Produto" + 
                    "2 - Cadastrar Item" + 
                    ">>> ");
                opcao = sc.nextInt();
                
                switch(opcao) {
                    case 1 -> cadastrarProduto();
                    case 2 -> cadastrarItem();
                    case 3 -> {
                        System.out.println("Encerrando o programa..."); 
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }while (opcao != 3);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void DepLogistica() {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        try {
            do { 
                System.out.print(
                    "=== DEPARTAMENTO DE LOGÍSITICA ===" + 
                    "Qual ação deseja realizar" + 
                    "1 - Cadastrar Empresa" + 
                    "2 - Cadastrar Agente" + 
                    "3 - Cadastrar Transporte" + 
                    ">>> ");
                opcao = sc.nextInt();
                    
                switch(opcao) {
                    case 1 -> {
                        int novoAgente = -1;
                        do { 
                            System.out.print(
                                "Deseja cadastrar um agente novo? " +
                                "1 - Sim " +
                                "2 - Não" +
                                ">>> "
                                );
                            novoAgente = sc.nextInt();
                            
                            if (novoAgente != 1 && novoAgente != 2)
                                System.out.println("Opção inválida.");
                        } while (novoAgente != 1 && novoAgente != 2);
                            
                        if (novoAgente == 1) {
                            cadastrarAgente();
                        }
                        cadastrarPais();
                        cadastrarEmpresa();
                    }
                    case 2 -> cadastrarAgente();
                    case 3 -> cadastrarTransporte();
                    case 4 -> {
                        System.out.println("Encerrando o programa..."); 
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }  
            } while (opcao != 4);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static Pais cadastrarPais() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o id do País: ");
        int idPais = sc.nextInt();
        System.out.print("Digite o nome do País:");
        String nomePais = sc.next();

        Pais pais = new Pais(idPais, nomePais);
        return pais;
    }       

    public static Cidade cadastrarCidade() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o id da cidade: ");
        int idCidade = sc.nextInt();
        System.out.print("Digite o nome da cidade:");
        String nomeCidade = sc.nextLine();
        Cidade Cidade = new Cidade(idCidade, nomeCidade);
        return Cidade;
    }       

    public static Armazem cadastrarArmazem() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o id do Armazem: ");
        int idArmazem = sc.nextInt();
        System.out.print("Digite o nome do Armazem:");
        String nomeArmazem = sc.nextLine();

        Armazem armazem = new Armazem(idArmazem, nomeArmazem);
        return armazem;
    }
    
    public static Produto cadastrarProduto() {
        Scanner sc = new Scanner(System.in);

        ArrayList<Item> itensProduto = new ArrayList<>();
        int novoItem = -1;
        do { 
            System.out.print(
                "Deseja cadastrar um item novo? " +
                "1 - Sim " +
                "2 - Não" +
                ">>> "   
            );
            novoItem = sc.nextInt();

            if (novoItem != 1 && novoItem != 2)
                System.out.println("Opção inválida.");
        } while (novoItem != 1 && novoItem != 2);
        
        if (novoItem == 1) {
            itensProduto.add(cadastrarItem());
        }
        
        System.out.print("Digite o id do produto:");
        int idProduto = sc.nextInt();
        System.out.print("Digite o nome do Produto:");
        String nomeProduto = sc.nextLine();
        System.out.print("Digite a situação do Produto:");
        String situacao = sc.nextLine();

        Produto produto = new Produto(idProduto, nomeProduto, situacao);
        for (Item item : itensProduto)
            produto.appendItem(item);
        return produto;
    }

    public static Item cadastrarItem() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o id do Agente: ");
        int idItem = sc.nextInt();
        System.out.print("Digite o nome do Agente:");
        String nomeItem = sc.nextLine();
        Item item = new Item(idItem, nomeItem);
        return item;
    }

    public static Empresa cadastrarEmpresa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o id da Empresa:");
        int idEmpresa = sc.nextInt();
        System.out.print("Digite o nome da Empresa:");
        String nomeEmpresa = sc.nextLine();
        Pais paisOrigem = cadastrarPais();

        Empresa empresa = new Empresa(idEmpresa, nomeEmpresa, paisOrigem);
        return empresa;
    }

    public static Agente cadastrarAgente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o id do Agente: ");
        int idAgente = sc.nextInt();
        System.out.print("Digite o nome do Agente:");
        String nomeAgente = sc.nextLine();
        System.out.print("Digite o situação do Agente:");
        String situacaoAgente = sc.nextLine();
        Agente agente = new Agente(idAgente, nomeAgente, situacaoAgente);
        return agente;
    }

    public static Transporte cadastrarTransporte() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o id do Transporte: ");
        int idTransporte = sc.nextInt();
        System.out.print("Digite o tipo do Transporte");
        String nomeTransporte = sc.nextLine();
        System.out.print("Digite a situação do Transporte:");
        String situacaoTransporte = sc.nextLine();
        Transporte transporte = new Transporte(idTransporte, nomeTransporte, situacaoTransporte);
        transporte.setEmpresaResponsavel(cadastrarEmpresa());

        return transporte;
    }       
}
