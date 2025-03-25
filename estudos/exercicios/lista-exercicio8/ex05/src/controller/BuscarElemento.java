package controller;
import java.util.Scanner;

public class BuscarElemento {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = new int[10];

        for (int i = 0; i < 10; i++) {
            System.out.printf("Digite o %d número: ", i+1);
            numbers[i] = sc.nextInt();
        }

        System.out.print("Qual número você deseja buscar? ");
        int busca = sc.nextInt();


        int index = -1;
        for (int i = 0; i < 10; i++) {
            if (busca == numbers[i]) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            System.out.printf("O número %d foi encontrado na posição %d do array!", busca, index);
        }

        sc.close();
    }
}
