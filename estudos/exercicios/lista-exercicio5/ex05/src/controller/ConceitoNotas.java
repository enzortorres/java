package controller;
import java.util.Scanner;

public class ConceitoNotas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String conceito = "";

        try {
            System.out.print("Digite a nota do aluno: ");
            int nota = sc.nextInt();

            switch (nota) {
                case 9, 10:
                    conceito = "A";
                    break;
                case 7, 8:
                    conceito = "B";
                    break;
                case 5, 6:
                    conceito = "C";
                    break;
                case 3, 4:
                    conceito = "D";
                    break;
                case 0, 1, 2:
                    conceito = "E";
                    break;
                default:
                    throw new Exception("Nota inválida.");
            }
            System.out.printf("Nota %d, conceito %s.", nota, conceito);
        } catch (Exception ex) {
                System.out.println("Digite um valor válido.");
        } finally {
            sc.close();
        }

    }
}
