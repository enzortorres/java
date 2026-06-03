package controller;

import model.Aluno;
import java.util.ArrayList;
import java.util.List;

public class AlunoController {
    private List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(String matricula, String nome, String endereco, String telefone) {
        if (buscarPorMatricula(matricula) != null) {
            System.out.println("Erro: Aluno com matrícula '" + matricula + "' já existe.");
            return;
        }
        Aluno aluno = new Aluno(matricula, nome, endereco, telefone);
        alunos.add(aluno);
        System.out.println("Aluno cadastrado com sucesso: " + aluno);
    }

    public void alocarAluno(String matricula) {
        Aluno aluno = buscarPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Aluno " + aluno.getNome() + " alocado.");
        } else {
            System.out.println("Erro: Aluno não encontrado.");
        }
    }

    public void matricularAluno(String matricula) {
        Aluno aluno = buscarPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Aluno " + aluno.getNome() + " matriculado.");
        } else {
            System.out.println("Erro: Aluno não encontrado.");
        }
    }

    public void trancar(String matricula) {
        Aluno aluno = buscarPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Matrícula do aluno " + aluno.getNome() + " trancada.");
        } else {
            System.out.println("Erro: Aluno não encontrado.");
        }
    }

    public void destrancar(String matricula) {
        Aluno aluno = buscarPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Matrícula do aluno " + aluno.getNome() + " destrancada.");
        } else {
            System.out.println("Erro: Aluno não encontrado.");
        }
    }

    public void formar(String matricula) {
        Aluno aluno = buscarPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Aluno " + aluno.getNome() + " formado.");
        } else {
            System.out.println("Erro: Aluno não encontrado.");
        }
    }

    public void expulsar(String matricula) {
        Aluno aluno = buscarPorMatricula(matricula);
        if (aluno != null) {
            alunos.remove(aluno);
            System.out.println("Aluno " + aluno.getNome() + " expulso e removido do sistema.");
        } else {
            System.out.println("Erro: Aluno não encontrado.");
        }
    }

    public Aluno buscarPorMatricula(String matricula) {
        for (Aluno a : alunos) {
            if (a.getMatricula().equals(matricula)) return a;
        }
        return null;
    }

    public List<Aluno> listarTodos() {
        return alunos;
    }
}
