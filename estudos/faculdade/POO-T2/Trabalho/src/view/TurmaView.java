package view;

import controller.DisciplinaController;
import controller.ProfessorController;
import controller.TurmaController;
import model.Disciplina;
import model.Professor;
import model.Turma;
import java.util.List;
import java.util.Scanner;

public class TurmaView {
    private TurmaController turmaController;
    private ProfessorController professorController;
    private DisciplinaController disciplinaController;
    private Scanner scanner;

    public TurmaView(TurmaController turmaController, ProfessorController professorController,
                     DisciplinaController disciplinaController, Scanner scanner) {
        this.turmaController = turmaController;
        this.professorController = professorController;
        this.disciplinaController = disciplinaController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU TURMA =====");
            System.out.println("1. Cadastrar Turma");
            System.out.println("2. Listar Turmas");
            System.out.println("3. Selecionar Turma");
            System.out.println("4. Concluir Turma");
            System.out.println("5. Arquivar Turma");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarTurma(); break;
                case 2: listarTurmas(); break;
                case 3: selecionarTurma(); break;
                case 4: concluirTurma(); break;
                case 5: arquivarTurma(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarTurma() {
        System.out.print("ID da Turma: ");
        String idTurma = scanner.nextLine();
        System.out.print("Turno (Manhã/Tarde/Noite): ");
        String turno = scanner.nextLine();
        System.out.print("Data (ex: 01/03/2025): ");
        String data = scanner.nextLine();

        System.out.print("Matrícula do Professor: ");
        String matProf = scanner.nextLine();
        Professor prof = professorController.buscarPorMatricula(matProf);
        if (prof == null) { System.out.println("Professor não encontrado."); return; }

        System.out.print("Código da Disciplina: ");
        Disciplina disc;
        try {
            int codDisc = Integer.parseInt(scanner.nextLine());
            disc = disciplinaController.buscarPorCodigo(codDisc);
        } catch (NumberFormatException e) {
            System.out.println("Código inválido.");
            return;
        }
        if (disc == null) { System.out.println("Disciplina não encontrada."); return; }

        turmaController.cadastrarTurma(idTurma, turno, data, prof, disc);
    }

    private void listarTurmas() {
        List<Turma> lista = turmaController.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
        } else {
            System.out.println("\n--- Turmas ---");
            for (Turma t : lista) System.out.println(t);
        }
    }

    private void selecionarTurma() {
        System.out.print("ID da Turma: ");
        turmaController.selecionarTurma(scanner.nextLine());
    }

    private void concluirTurma() {
        System.out.print("ID da Turma: ");
        turmaController.concluirTurma(scanner.nextLine());
    }

    private void arquivarTurma() {
        System.out.print("ID da Turma: ");
        turmaController.arquivarTurma(scanner.nextLine());
    }
}
