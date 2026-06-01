package controller;

import model.Aluno;
import model.Cursa;
import model.Disciplina;
import model.Professor;
import model.Turma;
import java.util.ArrayList;
import java.util.List;

public class TurmaController {
    private List<Turma> turmas = new ArrayList<>();
    private List<Cursa> cursas = new ArrayList<>();

    public void cadastrarTurma(String idTurma, String turno, String data,
                               Professor professor, Disciplina disciplina) {
        if (buscarPorId(idTurma) != null) {
            System.out.println("Erro: Turma '" + idTurma + "' já existe.");
            return;
        }
        Turma turma = new Turma(idTurma, turno, data, professor, disciplina);
        turmas.add(turma);
        System.out.println("Turma cadastrada com sucesso: " + turma);
    }

    public Turma selecionarTurma(String idTurma) {
        Turma turma = buscarPorId(idTurma);
        if (turma != null) {
            System.out.println("Turma selecionada: " + turma);
        } else {
            System.out.println("Erro: Turma não encontrada.");
        }
        return turma;
    }

    public void concluirTurma(String idTurma) {
        Turma turma = buscarPorId(idTurma);
        if (turma != null) {
            turma.concluir();
        } else {
            System.out.println("Erro: Turma não encontrada.");
        }
    }

    public void arquivarTurma(String idTurma) {
        Turma turma = buscarPorId(idTurma);
        if (turma != null) {
            turma.arquivar();
        } else {
            System.out.println("Erro: Turma não encontrada.");
        }
    }

    public void matricularAlunoNaTurma(Aluno aluno, Turma turma, String dataInicio, String dataFim) {
        if (aluno == null || turma == null) {
            System.out.println("Erro: Aluno ou Turma inválidos.");
            return;
        }
        turma.adicionarAluno(aluno);
        Cursa cursa = new Cursa(aluno, turma, dataInicio, dataFim);
        cursas.add(cursa);
        System.out.println("Aluno " + aluno.getNome() + " matriculado na turma " + turma.getIdTurma() + ".");
    }

    public List<Cursa> listarCursasPorAluno(String matriculaAluno) {
        List<Cursa> resultado = new ArrayList<>();
        for (Cursa c : cursas) {
            if (c.getAluno().getMatricula().equals(matriculaAluno)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public Turma buscarPorId(String idTurma) {
        for (Turma t : turmas) {
            if (t.getIdTurma().equals(idTurma)) return t;
        }
        return null;
    }

    public List<Turma> listarTodas() {
        return turmas;
    }
}
