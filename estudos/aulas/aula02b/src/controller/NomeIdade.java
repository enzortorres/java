package controller;

import java.util.Scanner;

public class NomeIdade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite sua idade: ");
        int idade = sc.nextInt();

        if (idade >= 18) {
            System.out.printf("%s com %d anos, maior de Idade", nome ,idade);
        } else if (idade >= 0) {
            System.out.printf("%s com %d anos, menor de idade", nome ,idade);
        } else {
            System.out.println("Idade inválida");
        }
    }
}
