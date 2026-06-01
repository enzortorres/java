package model;

public class Cursa {
    private Aluno aluno;
    private Turma turma;
    private String dataInicio;
    private String dataFim;

    public Cursa(Aluno aluno, Turma turma, String dataInicio, String dataFim) {
        this.aluno = aluno;
        this.turma = turma;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    @Override
    public String toString() {
        return "Cursa{aluno='" + aluno.getNome() + "', turma='" + turma.getIdTurma() +
               "', dataInicio='" + dataInicio + "', dataFim='" + dataFim + "'}";
    }
}
