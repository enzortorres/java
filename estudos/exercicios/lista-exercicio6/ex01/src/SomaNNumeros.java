import java.util.Scanner;

public class SomaNNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int number = sc.nextInt();
        int soma = 0;

        System.out.print("Somando os valores: ");
        for (int i = 1; i <= number; i++) {
            System.out.printf("%d ", i);
            soma += i;
        }

        System.out.printf("\nResulta em: %d", soma);


    }
}