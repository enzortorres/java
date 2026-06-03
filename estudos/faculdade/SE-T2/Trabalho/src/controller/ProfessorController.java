package controller;

import model.Professor;
import model.Prova;
import java.util.ArrayList;
import java.util.List;

public class ProfessorController {
    private List<Professor> professores = new ArrayList<>();

    public void cadastrarProfessor(String matricula, int registro, String nome, String especialidade) {
        if (buscarPorMatricula(matricula) != null) {
            System.out.println("Erro: Professor com matrícula '" + matricula + "' já existe.");
            return;
        }
        Professor prof = new Professor(matricula, registro, nome, especialidade);
        professores.add(prof);
        System.out.println("Professor cadastrado com sucesso: " + prof);
    }

    public void alocarProfessor(String matricula) {
        Professor prof = buscarPorMatricula(matricula);
        if (prof != null) {
            System.out.println("Professor " + prof.getNome() + " alocado.");
        } else {
            System.out.println("Erro: Professor não encontrado.");
        }
    }

    public void lancarNota(Professor prof, Prova prova, String alunoNome, float nota) {
        if (prof != null && prova != null) {
            System.out.println("Professor " + prof.getNome() + " lançou nota " + nota +
                               " para o aluno " + alunoNome + " na prova " + prova.getCodigo() + ".");
        } else {
            System.out.println("Erro: Dados inválidos para lançamento de nota.");
        }
    }

    public void lancarPresenca(String matriculaProf, String turmaId) {
        Professor prof = buscarPorMatricula(matriculaProf);
        if (prof != null) {
            System.out.println("Presença lançada pelo professor " + prof.getNome() +
                               " para a turma " + turmaId + ".");
        } else {
            System.out.println("Erro: Professor não encontrado.");
        }
    }

    public Professor buscarPorMatricula(String matricula) {
        for (Professor p : professores) {
            if (p.getMatricula().equals(matricula)) return p;
        }
        return null;
    }

    public List<Professor> listarTodos() {
        return professores;
    }
}
