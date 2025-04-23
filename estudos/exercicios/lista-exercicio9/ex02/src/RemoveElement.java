import java.util.ArrayList;
import java.util.Scanner;

public class RemoveElement {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.print("Digite um nome: ");
            names.add(scanner.nextLine());
        }

        System.out.println("Nomes: ");
        for (String name : names) {
            System.out.println(name);
        }

        System.out.print("Digite um nome para remover: ");
        names.remove(scanner.nextLine());

        System.out.println("Nomes após alteração: ");
        for (String name : names) {
            System.out.println(name);
        }

        scanner.close();
    }
}
