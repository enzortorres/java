import java.util.ArrayList;
import java.util.Scanner;

public class AverageNumbers {
    public static void main(String[] args) {
        ArrayList<Double> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        double number = 0, soma = 0, media = 0;

        while (true) {
            System.out.print("Digite um número: ");
            number = scanner.nextDouble();

            if (number == -1) {
                break;
            }
            numbers.add(number);
        }


        for (double i : numbers) {
            soma += i;
        }
        media = soma / numbers.size();

        System.out.printf("Média: %.2f", media);

        scanner.close();
    }
}
