package controller;

import java.util.Scanner;

public class ClassificacaoIdade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite a sua idade: ");
        int idade = sc.nextInt();

        if (idade >= 0 && idade < 18) {
            System.out.println("Menor de idade.");
        } else if (idade > 18 && idade < 60) {
            System.out.println("Adulto.");
        } else if (idade > 60) {
            System.out.println("Idoso.");
        } else {
            System.out.println("Digite uma idade válida");
        }
    }
}
