package model;

public class Console implements Comparable<Console>{
	private String descricao;
	private int ano;
	
	public Console(String descricao, int ano) {
		super();
		this.descricao = descricao;
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public int compareTo(Console console) {
		if (this.descricao.charAt(0) > console.getDescricao().charAt(0)) {
			return 1;			
		}
		else if (this.descricao.charAt(0) < console.getDescricao().charAt(0)) {
			return -1;
		}
		return this.descricao.compareTo(console.getDescricao());
	}
}
