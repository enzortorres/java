package controller;
import java.util.Scanner;

public class NumerosPrimos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Digite o primeiro número: ");
            int number1 = sc.nextInt();
            System.out.print("Digite o segundo número: ");
            int number2 = sc.nextInt();

            if (number1 > number2) {
                throw new IllegalArgumentException("O primeiro número não pode ser maior do que o segundo número.");
            } else if (number1 < 0) {
                throw new IllegalArgumentException("Somente números positivos.");
            }


            System.out.printf("Números primos entre %d e %d:\n", number1, number2);
            for (int i = number1; i <= number2; i++) {
                boolean isPrimo = true;

                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        isPrimo = false;
                        break;
                    }
                }
                if (isPrimo) {
                    System.out.println(i);
                }
            }

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        } catch (Exception error) {
            System.out.println("Digite somente números");
        }


    }
}
