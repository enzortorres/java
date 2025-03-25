package controller;

import java.util.Scanner;

public class MediaNotas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[] notas = new double[4];
        double soma = 0;

        for (int i = 0; i < 4; i++) {
            System.out.printf("Digite a %d nota: ", i+1);
            notas[i] = sc.nextDouble();
            soma += notas[i];
        }
        double media = soma / notas.length;

        System.out.printf("Media: %.2f\n" + (media >= 7 ? "Aprovado" : "Reprovado"), media);
        sc.close();
    }
}
