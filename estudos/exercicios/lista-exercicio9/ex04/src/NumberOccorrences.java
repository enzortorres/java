import java.util.ArrayList;
import java.util.Scanner;

public class NumberOccorrences {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            System.out.print("Type a number: ");
            numbers.add(scanner.nextInt());
        }

        System.out.print("Type a number to check the occurrences: ");
        int numberToCheck = scanner.nextInt();

        int qtd_occorrences = 0;
        for (int number : numbers) {
            if (number == numberToCheck) {
                qtd_occorrences++;
            }
        }

        System.out.printf("Qtd: %d", qtd_occorrences);

        scanner.close();
    }
}
