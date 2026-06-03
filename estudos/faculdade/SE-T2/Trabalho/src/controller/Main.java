package controller;
import controller.*;
import view.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AlunoController alunoController           = new AlunoController();
        ProfessorController professorController   = new ProfessorController();
        DisciplinaController disciplinaController = new DisciplinaController();
        TurmaController turmaController           = new TurmaController();
        ProvaController provaController           = new ProvaController();

        AlunoView alunoView         = new AlunoView(alunoController, turmaController, scanner);
        ProfessorView professorView = new ProfessorView(professorController, provaController, scanner);
        DisciplinaView disciplinaView = new DisciplinaView(disciplinaController, scanner);
        TurmaView turmaView         = new TurmaView(turmaController, professorController, disciplinaController, scanner);
        ProvaView provaView         = new ProvaView(provaController, professorController, disciplinaController, alunoController, scanner);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n");
            System.out.println("SISTEMA GHFLUSAO");
            System.out.println("1. Gerenciar Alunos      ");
            System.out.println("2. Gerenciar Professores ");
            System.out.println("3. Gerenciar Disciplinas ");
            System.out.println("4. Gerenciar Turmas      ");
            System.out.println("5. Gerenciar Provas      ");
            System.out.println("0. Sair                  ");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: alunoView.exibirMenu(); break;
                case 2: professorView.exibirMenu(); break;
                case 3: disciplinaView.exibirMenu(); break;
                case 4: turmaView.exibirMenu(); break;
                case 5: provaView.exibirMenu(); break;
                case 0: System.out.println("Encerrando o sistema. Até logo!"); break;
                default: System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}
