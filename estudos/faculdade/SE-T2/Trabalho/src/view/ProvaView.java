package view;

import controller.AlunoController;
import controller.DisciplinaController;
import controller.ProfessorController;
import controller.ProvaController;
import model.Aluno;
import model.Disciplina;
import model.Professor;
import model.Prova;
import model.Realiza;
import java.util.List;
import java.util.Scanner;

public class ProvaView {
    private ProvaController provaController;
    private ProfessorController professorController;
    private DisciplinaController disciplinaController;
    private AlunoController alunoController;
    private Scanner scanner;

    public ProvaView(ProvaController provaController, ProfessorController professorController,
                     DisciplinaController disciplinaController, AlunoController alunoController,
                     Scanner scanner) {
        this.provaController = provaController;
        this.professorController = professorController;
        this.disciplinaController = disciplinaController;
        this.alunoController = alunoController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU PROVA =====");
            System.out.println("1. Cadastrar Prova");
            System.out.println("2. Listar Provas");
            System.out.println("3. Disponibilizar Prova");
            System.out.println("4. Aplicar Prova");
            System.out.println("5. Corrigir Prova");
            System.out.println("6. Registrar Realização (Nota)");
            System.out.println("7. Listar Notas de um Aluno");
            System.out.println("8. Lançar Nota (Professor)");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarProva(); break;
                case 2: listarProvas(); break;
                case 3: disponibilizarProva(); break;
                case 4: aplicarProva(); break;
                case 5: corrigirProva(); break;
                case 6: registrarRealizacao(); break;
                case 7: listarNotasDoAluno(); break;
                case 8: lancarNota(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarProva() {
        System.out.print("Código da Prova: ");
        int codigo;
        try {
            codigo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Código inválido.");
            return;
        }
        System.out.print("Data (ex: 15/05/2025): ");
        String data = scanner.nextLine();
        System.out.print("Peso: ");
        int peso;
        try {
            peso = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Peso inválido.");
            return;
        }

        System.out.print("Código da Disciplina: ");
        Disciplina disc;
        try {
            disc = disciplinaController.buscarPorCodigo(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Código inválido."); return;
        }
        if (disc == null) { System.out.println("Disciplina não encontrada."); return; }

        System.out.print("Matrícula do Professor: ");
        Professor prof = professorController.buscarPorMatricula(scanner.nextLine());
        if (prof == null) { System.out.println("Professor não encontrado."); return; }

        provaController.cadastrarProva(codigo, data, peso, disc, prof);
    }

    private void listarProvas() {
        List<Prova> lista = provaController.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma prova cadastrada.");
        } else {
            System.out.println("\n--- Provas ---");
            for (Prova p : lista) System.out.println(p);
        }
    }

    private void disponibilizarProva() {
        System.out.print("Código da Prova: ");
        try { provaController.disponibilizarProva(Integer.parseInt(scanner.nextLine())); }
        catch (NumberFormatException e) { System.out.println("Código inválido."); }
    }

    private void aplicarProva() {
        System.out.print("Código da Prova: ");
        try { provaController.aplicarProva(Integer.parseInt(scanner.nextLine())); }
        catch (NumberFormatException e) { System.out.println("Código inválido."); }
    }

    private void corrigirProva() {
        System.out.print("Código da Prova: ");
        try { provaController.corrigirProva(Integer.parseInt(scanner.nextLine())); }
        catch (NumberFormatException e) { System.out.println("Código inválido."); }
    }

    private void registrarRealizacao() {
        System.out.print("Matrícula do Aluno: ");
        Aluno aluno = alunoController.buscarPorMatricula(scanner.nextLine());
        if (aluno == null) { System.out.println("Aluno não encontrado."); return; }

        System.out.print("Código da Prova: ");
        int codProva;
        try { codProva = Integer.parseInt(scanner.nextLine()); }
        catch (NumberFormatException e) { System.out.println("Código inválido."); return; }

        System.out.print("Nota (0.0 - 10.0): ");
        float nota;
        try { nota = Float.parseFloat(scanner.nextLine()); }
        catch (NumberFormatException e) { System.out.println("Nota inválida."); return; }

        provaController.registrarRealizacao(aluno, codProva, nota);
    }

    private void listarNotasDoAluno() {
        System.out.print("Matrícula do Aluno: ");
        String mat = scanner.nextLine();
        List<Realiza> realizacoes = provaController.listarRealizacoesPorAluno(mat);
        if (realizacoes.isEmpty()) {
            System.out.println("Nenhuma nota registrada para este aluno.");
        } else {
            System.out.println("\n--- Notas do Aluno ---");
            for (Realiza r : realizacoes) System.out.println(r);
        }
    }

    private void lancarNota() {
        System.out.print("Matrícula do Professor: ");
        Professor prof = professorController.buscarPorMatricula(scanner.nextLine());
        if (prof == null) { System.out.println("Professor não encontrado."); return; }

        System.out.print("Código da Prova: ");
        Prova prova;
        try { prova = provaController.buscarPorCodigo(Integer.parseInt(scanner.nextLine())); }
        catch (NumberFormatException e) { System.out.println("Código inválido."); return; }
        if (prova == null) { System.out.println("Prova não encontrada."); return; }

        System.out.print("Nome do Aluno: ");
        String nomeAluno = scanner.nextLine();
        System.out.print("Nota: ");
        float nota;
        try { nota = Float.parseFloat(scanner.nextLine()); }
        catch (NumberFormatException e) { System.out.println("Nota inválida."); return; }

        professorController.lancarNota(prof, prova, nomeAluno, nota);
    }
}
