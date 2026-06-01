package view;

import controller.ProvaController;
import controller.ProfessorController;
import model.Aluno;
import model.Professor;
import model.Prova;
import java.util.List;
import java.util.Scanner;

public class ProfessorView {
    private ProfessorController professorController;
    private ProvaController provaController;
    private Scanner scanner;

    public ProfessorView(ProfessorController professorController, ProvaController provaController, Scanner scanner) {
        this.professorController = professorController;
        this.provaController = provaController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU PROFESSOR =====");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Listar Professores");
            System.out.println("3. Alocar Professor");
            System.out.println("4. Lançar Presença");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarProfessor(); break;
                case 2: listarProfessores(); break;
                case 3: alocarProfessor(); break;
                case 4: lancarPresenca(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarProfessor() {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Registro: ");
        int registro;
        try {
            registro = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Registro inválido.");
            return;
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        professorController.cadastrarProfessor(matricula, registro, nome, especialidade);
    }

    private void listarProfessores() {
        List<Professor> lista = professorController.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            System.out.println("\n--- Professores ---");
            for (Professor p : lista) System.out.println(p);
        }
    }

    private void alocarProfessor() {
        System.out.print("Matrícula do Professor: ");
        professorController.alocarProfessor(scanner.nextLine());
    }

    private void lancarPresenca() {
        System.out.print("Matrícula do Professor: ");
        String mat = scanner.nextLine();
        System.out.print("ID da Turma: ");
        String turmaId = scanner.nextLine();
        professorController.lancarPresenca(mat, turmaId);
    }
}
