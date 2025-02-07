import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Digite um nome: ");
            names.add(sc.nextLine());

            System.out.print("Deseja continuar? ");
            var resp = sc.nextLine();

            if (resp.equals("sim")) {
                continue;
            }
            break;
        }

        var count = 1;
        for(String nome : names) {
            System.out.println(count + " - " + nome);
            count++;
        }
    }
}
