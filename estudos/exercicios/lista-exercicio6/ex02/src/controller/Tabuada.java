package controller;
import java.util.Scanner;

public class Tabuada {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Digite um número: ");
            int number = sc.nextInt();
            int contador = 1;

            while (contador <= 10) {
                System.out.printf("%d x %d = %d\n", number, contador, number * contador);
                contador++;
            }
        } catch (Exception e) {
            System.out.println("Digite somente números.");
        }
    }
}
