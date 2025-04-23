package controller;
import java.util.ArrayList;
import java.util.Scanner;

public class AddNumbers {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int number = 0;

        while (true) {
            System.out.print("Digite um número: (0 para sair) ");
            number = scanner.nextInt();

            if (number == 0) {
                break;
            }
            numbers.add(number);
        }

        System.out.print("Números armazenados na lista: ");
        for (int i : numbers) {
            System.out.print(i + " ");
        }

        scanner.close();
    }
}
