package controller;
import java.util.Scanner;

public class ContagemRegressiva {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número para começar a contagem: ");
        int contagem = sc.nextInt();

        do {
            System.out.println(contagem);
            if (contagem == 0) break;
            Thread.sleep(1000);
            contagem--;
        } while (contagem >= 0);
        System.out.println("Acabou!");
    }
}
