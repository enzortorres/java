import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] notas = new double[3];
        double soma = 0;

        for (int i = 0; i < notas.length; i++) {
            System.out.printf("Digite a %d nota: ", i+1);
            notas[i] = sc.nextDouble();
            soma += notas[i];
        }

        for(Double nota : notas) {
            System.out.print(nota);
        }

        double media = soma / 3;
        System.out.printf("A media das notas digitadas é: %.2f", media);
    }
}