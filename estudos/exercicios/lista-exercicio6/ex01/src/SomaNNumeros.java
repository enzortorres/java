import java.util.Scanner;

public class SomaNNumeros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int number = sc.nextInt();
        int soma = 0;

        System.out.print("Somando os valores: ");
        for (int i = 1; i <= number; i++) {
            System.out.print(i + (i < number ? " + " : " = "));
            soma += i;
        }

        System.out.print(soma);


    }
}