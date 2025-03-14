package controller;
import java.util.Scanner;

public class Tabuada {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Digite um número: ");
            int number = sc.nextInt();

            for (int i = 1; i <= 10; i++) {
                System.out.printf("%d x %d = %d\n", number, i, number * i);
            }
        } catch (Exception e) {
            System.out.println("Digite somente números.");
        }
    }
}
