package controller;
import java.util.Scanner;

public class ContarPares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = new int[8];
        int qtdPares = 0;

        for (int i = 0; i < 8; i++) {
            System.out.printf("Digite o %d número: ", i+1);
            numbers[i] = sc.nextInt();
            if (numbers[i] % 2 == 0) {
                qtdPares++;
            }
        }

        System.out.printf("Foram encontrados %d números pares!", qtdPares);
    }
}
