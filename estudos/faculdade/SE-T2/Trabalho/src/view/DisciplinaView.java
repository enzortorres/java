package view;

import controller.DisciplinaController;
import model.Disciplina;
import java.util.List;
import java.util.Scanner;

public class DisciplinaView {
    private DisciplinaController disciplinaController;
    private Scanner scanner;

    public DisciplinaView(DisciplinaController disciplinaController, Scanner scanner) {
        this.disciplinaController = disciplinaController;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU DISCIPLINA =====");
            System.out.println("1. Cadastrar Disciplina");
            System.out.println("2. Listar Disciplinas");
            System.out.println("3. Selecionar Disciplina");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarDisciplina(); break;
                case 2: listarDisciplinas(); break;
                case 3: selecionarDisciplina(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarDisciplina() {
        System.out.print("Código: ");
        int codigo;
        try {
            codigo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Código inválido.");
            return;
        }
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Créditos: ");
        int credito;
        try {
            credito = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Créditos inválidos.");
            return;
        }
        System.out.print("Carga Horária: ");
        int cargaHoraria;
        try {
            cargaHoraria = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Carga horária inválida.");
            return;
        }
        disciplinaController.cadastrarDisciplina(codigo, nome, credito, cargaHoraria);
    }

    private void listarDisciplinas() {
        List<Disciplina> lista = disciplinaController.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\n--- Disciplinas ---");
            for (Disciplina d : lista) System.out.println(d);
        }
    }

    private void selecionarDisciplina() {
        System.out.print("Código da Disciplina: ");
        try {
            int codigo = Integer.parseInt(scanner.nextLine());
            disciplinaController.selecionarDisciplina(codigo);
        } catch (NumberFormatException e) {
            System.out.println("Código inválido.");
        }
    }
}
