package model;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String idTurma;
    private String turno;
    private String data;
    private Professor professor;
    private Disciplina disciplina;
    private List<Aluno> alunos;
    private boolean arquivada;

    public Turma(String idTurma, String turno, String data, Professor professor, Disciplina disciplina) {
        this.idTurma = idTurma;
        this.turno = turno;
        this.data = data;
        this.professor = professor;
        this.disciplina = disciplina;
        this.alunos = new ArrayList<>();
        this.arquivada = false;
    }

    
    public String getIdTurma() { return idTurma; }
    public void setIdTurma(String idTurma) { this.idTurma = idTurma; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public List<Aluno> getAlunos() { return alunos; }

    public boolean isArquivada() { return arquivada; }

    public void adicionarAluno(Aluno aluno) {
        if (!alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }

    public void concluir() {
        System.out.println("Turma " + idTurma + " concluída.");
    }

    public void arquivar() {
        this.arquivada = true;
        System.out.println("Turma " + idTurma + " arquivada.");
    }

    @Override
    public String toString() {
        String profNome = professor != null ? professor.getNome() : "N/A";
        String discNome = disciplina != null ? disciplina.getNome() : "N/A";
        return "ID Turma:   " + idTurma +
               "\nTurno:      " + turno +
               "\nData:       " + data +
               "\nProfessor:  " + profNome +
               "\nDisciplina: " + discNome +
               "\nAlunos:     " + alunos.size() +
               "\nArquivada:  " + (arquivada ? "Sim" : "Não");
    }
}
