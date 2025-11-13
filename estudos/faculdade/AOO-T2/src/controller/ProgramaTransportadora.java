package controller;

import java.util.Scanner;

public class ProgramaTransportadora {

    public static void main(String[] args) {
        MenuAtor();
    }

    public static void MenuAtor(){
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
            
            switch(opcao){
                case 1:
                    System.out.println("Chamar função dep operacional");
                    DepOperacional();
                    break;
                case 2:
                    System.out.println("Chamar função dep material");
                    break;
                case 3:
                    System.out.println("Chamar função fep logística");
                    break;
                case 4:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            
        } while (opcao != 4);

        sc.close();
    }

    public static void DepOperacional(){
        System.out.println("Menu para mostrar o que o dep operacional..");
    }

}
