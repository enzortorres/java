package controller;
import java.util.Scanner;

public class MediaNotas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Quantos alunos tem na turma? ");
            int qtdAlunos = sc.nextInt();

            if (qtdAlunos < 0) {
                throw new IllegalArgumentException("Somente números positivos.");
            }
            double somaNotas = 0;

            for (int i = 0; i < qtdAlunos; i++) {
                System.out.printf("Digite a nota do %d aluno: ", i + 1);
                somaNotas += sc.nextInt();
            }

            double media = somaNotas / qtdAlunos;
            System.out.printf("Media da turma: %.1f", media);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        } catch (Exception error) {
            System.out.println("Digite somente números.");
        }
    }
}
