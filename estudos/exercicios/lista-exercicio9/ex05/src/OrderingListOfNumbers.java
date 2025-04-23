import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class OrderingListOfNumbers {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int qtd_numbers = 0;
        System.out.print("How many values do you want to add? ");
        qtd_numbers = scanner.nextInt();

        for (int i = 0; i < qtd_numbers; i++) {
            System.out.print("Type a number: ");
            numbers.add(scanner.nextInt());
        }

        System.out.println("\nUnordered list: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }

        Collections.sort(numbers);

        System.out.println("\nOrdered list: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }

        scanner.close();
    }
}
