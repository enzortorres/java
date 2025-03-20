package controller;

import dominio.Aluno;
import java.util.Scanner;

public class GerenciadorAluno {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            Aluno aluno = new Aluno();

            System.out.print("Digite o nome do aluno: ");
            aluno.setNome(scanner.nextLine());

            while (true) {
                System.out.print("Digite a nota do aluno: ");
                aluno.setNota(scanner.nextDouble());

                if (aluno.validarNota()) {
                    aluno.classificarAluno();
                    break;
                } else {
                    System.out.println("Nota inválida!");
                }
            }
        } catch (Exception e) {
            System.out.println("Digite um valor válido!");
        }
    }
}
