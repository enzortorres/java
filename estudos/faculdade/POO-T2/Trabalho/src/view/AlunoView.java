package view;

import controller.AlunoController;
import controller.TurmaController;
import model.Aluno;
import model.Cursa;
import model.Turma;
import java.util.List;
import java.util.Scanner;

public class AlunoView {
    private AlunoController alunoController;
    private TurmaController turmaController;
    private Scanner scanner;

    public AlunoView(AlunoController alunoController, TurmaController turmaController, Scanner scanner) {
        this.alunoController = alunoController;
        this.turmaController = turmaController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU ALUNO =====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("3. Alocar Aluno");
            System.out.println("4. Matricular Aluno em Turma");
            System.out.println("5. Trancar Matrícula");
            System.out.println("6. Destrancar Matrícula");
            System.out.println("7. Formar Aluno");
            System.out.println("8. Expulsar Aluno");
            System.out.println("9. Listar Turmas de um Aluno");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarAluno(); break;
                case 2: listarAlunos(); break;
                case 3: alocarAluno(); break;
                case 4: matricularEmTurma(); break;
                case 5: trancarMatricula(); break;
                case 6: destrancarMatricula(); break;
                case 7: formarAluno(); break;
                case 8: expulsarAluno(); break;
                case 9: listarTurmasDoAluno(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarAluno() {
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        alunoController.cadastrarAluno(matricula, nome, endereco, telefone);
    }

    private void listarAlunos() {
        List<Aluno> lista = alunoController.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("\n--- Alunos ---");
            for (Aluno a : lista) System.out.println(a);
        }
    }

    private void alocarAluno() {
        System.out.print("Matrícula do Aluno: ");
        alunoController.alocarAluno(scanner.nextLine());
    }

    private void matricularEmTurma() {
        System.out.print("Matrícula do Aluno: ");
        String matAluno = scanner.nextLine();
        Aluno aluno = alunoController.buscarPorMatricula(matAluno);
        if (aluno == null) { System.out.println("Aluno não encontrado."); return; }

        System.out.print("ID da Turma: ");
        String idTurma = scanner.nextLine();
        Turma turma = turmaController.buscarPorId(idTurma);
        if (turma == null) { System.out.println("Turma não encontrada."); return; }

        System.out.print("Data Início (ex: 01/03/2025): ");
        String dataInicio = scanner.nextLine();
        System.out.print("Data Fim (ex: 30/06/2025): ");
        String dataFim = scanner.nextLine();

        turmaController.matricularAlunoNaTurma(aluno, turma, dataInicio, dataFim);
    }

    private void trancarMatricula() {
        System.out.print("Matrícula do Aluno: ");
        alunoController.trancar(scanner.nextLine());
    }

    private void destrancarMatricula() {
        System.out.print("Matrícula do Aluno: ");
        alunoController.destrancar(scanner.nextLine());
    }

    private void formarAluno() {
        System.out.print("Matrícula do Aluno: ");
        alunoController.formar(scanner.nextLine());
    }

    private void expulsarAluno() {
        System.out.print("Matrícula do Aluno: ");
        alunoController.expulsar(scanner.nextLine());
    }

    private void listarTurmasDoAluno() {
        System.out.print("Matrícula do Aluno: ");
        String mat = scanner.nextLine();
        List<Cursa> cursas = turmaController.listarCursasPorAluno(mat);
        if (cursas.isEmpty()) {
            System.out.println("Nenhuma turma encontrada para este aluno.");
        } else {
            System.out.println("\n--- Turmas do Aluno ---");
            for (Cursa c : cursas) System.out.println(c);
        }
    }
}
