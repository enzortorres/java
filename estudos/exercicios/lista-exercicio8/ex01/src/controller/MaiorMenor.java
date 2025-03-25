package controller;

import java.util.Scanner;

public class MaiorMenor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[5];

        for (int i = 0; i < 5; i++) {
            System.out.printf("Digite o %d número: ", i+1);
            numbers[i] = sc.nextInt();
        }

        int maior = numbers[0], menor = numbers[0];

        for (int number : numbers) {
            if (number > maior) {
                maior = number;
            }
            if (number < menor || menor == 0) {
                menor = number;
            }
        }
        System.out.printf("Maior número: %d\nMenor número: %d", maior, menor);
    }
}
