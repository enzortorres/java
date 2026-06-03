package controller;

import model.Aluno;
import model.Disciplina;
import model.Professor;
import model.Prova;
import model.Realiza;
import java.util.ArrayList;
import java.util.List;

public class ProvaController {
    private List<Prova> provas = new ArrayList<>();
    private List<Realiza> realizacoes = new ArrayList<>();

    public void cadastrarProva(int codigo, String data, int peso,
                               Disciplina disciplina, Professor professor) {
        if (buscarPorCodigo(codigo) != null) {
            System.out.println("Erro: Prova com código " + codigo + " já existe.");
            return;
        }
        Prova prova = new Prova(codigo, data, peso, disciplina, professor);
        provas.add(prova);
        System.out.println("Prova cadastrada com sucesso: " + prova);
    }

    public void disponibilizarProva(int codigo) {
        Prova prova = buscarPorCodigo(codigo);
        if (prova != null) {
            prova.disponibilizar();
        } else {
            System.out.println("Erro: Prova não encontrada.");
        }
    }

    public void aplicarProva(int codigo) {
        Prova prova = buscarPorCodigo(codigo);
        if (prova != null) {
            prova.aplicar();
        } else {
            System.out.println("Erro: Prova não encontrada.");
        }
    }

    public void corrigirProva(int codigo) {
        Prova prova = buscarPorCodigo(codigo);
        if (prova != null) {
            prova.corrigir();
        } else {
            System.out.println("Erro: Prova não encontrada.");
        }
    }

    public void registrarRealizacao(Aluno aluno, int codigoProva, float nota) {
        Prova prova = buscarPorCodigo(codigoProva);
        if (aluno == null || prova == null) {
            System.out.println("Erro: Aluno ou Prova inválidos.");
            return;
        }
        Realiza realiza = new Realiza(aluno, prova, nota);
        realizacoes.add(realiza);
        System.out.println("Registrado: " + realiza);
    }

    public List<Realiza> listarRealizacoesPorAluno(String matriculaAluno) {
        List<Realiza> resultado = new ArrayList<>();
        for (Realiza r : realizacoes) {
            if (r.getAluno().getMatricula().equals(matriculaAluno)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public Prova buscarPorCodigo(int codigo) {
        for (Prova p : provas) {
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    public List<Prova> listarTodas() {
        return provas;
    }
}
