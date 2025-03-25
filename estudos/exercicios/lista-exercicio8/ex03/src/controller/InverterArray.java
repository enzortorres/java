package controller;

import java.util.Scanner;

public class InverterArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[6];

        for (int i = 0; i < 6; i++) {
            System.out.printf("Digite o %d número: ", i+1);
            numbers[i] = sc.nextInt();
        }
        for (int i = 6; i > 0; i--) {
            System.out.println(numbers[i - 1]);
        }
    }
}
