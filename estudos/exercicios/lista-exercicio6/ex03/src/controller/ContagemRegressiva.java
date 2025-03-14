package controller;
import java.util.Scanner;

public class ContagemRegressiva {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número para começar a contagem: ");
        int contagemInicial = sc.nextInt();

        for (int i = contagemInicial; i >= 0; i--)  {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("Acabou!");
    }
}
