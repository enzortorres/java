package controller;

import model.Disciplina;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaController {
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void cadastrarDisciplina(int codigo, String nome, int credito, int cargaHoraria) {
        if (buscarPorCodigo(codigo) != null) {
            System.out.println("Erro: Disciplina com código " + codigo + " já existe.");
            return;
        }
        Disciplina disc = new Disciplina(codigo, nome, credito, cargaHoraria);
        disciplinas.add(disc);
        System.out.println("Disciplina cadastrada com sucesso: " + disc);
    }

    public Disciplina selecionarDisciplina(int codigo) {
        Disciplina disc = buscarPorCodigo(codigo);
        if (disc != null) {
            System.out.println("Disciplina selecionada: " + disc);
        } else {
            System.out.println("Erro: Disciplina não encontrada.");
        }
        return disc;
    }

    public Disciplina buscarPorCodigo(int codigo) {
        for (Disciplina d : disciplinas) {
            if (d.getCodigo() == codigo) return d;
        }
        return null;
    }

    public List<Disciplina> listarTodas() {
        return disciplinas;
    }
}
