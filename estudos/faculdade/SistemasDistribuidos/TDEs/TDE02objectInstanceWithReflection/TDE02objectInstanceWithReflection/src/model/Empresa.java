package model;

public class Empresa {
	private String nome;
	private String cnpj;
	private int qtdFunc;
	
	public Empresa() {
		super();
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getQtdFunc() {
		return qtdFunc;
	}

	public void setQtdFunc(int qtdFunc) {
		this.qtdFunc = qtdFunc;
	}
	
	public Empresa(String nome, String cnpj, int qtdFunc) {
		super();
		this.setNome(nome);
		this.setCnpj(cnpj);
		this.setQtdFunc(qtdFunc);
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "\nCNPJ: " + cnpj + "\nQuantidade de funcionarios: " + qtdFunc;
	}
	
}	
