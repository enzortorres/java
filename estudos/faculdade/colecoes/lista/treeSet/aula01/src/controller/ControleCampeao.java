package controller;

import java.util.TreeSet;

import model.Campeao;

public class ControleCampeao {
	public static void main(String[] args) {
		TreeSet<Campeao> campeoes = new TreeSet<Campeao>();
		
		campeoes.add(new Campeao(2002, "Brasil"));
		campeoes.add(new Campeao(2014, "Alemanha"));
		campeoes.add(new Campeao(2006, "Itália"));
		campeoes.add(new Campeao(2022, "Argentina"));
		campeoes.add(new Campeao(2018, "França"));
		
		for (Campeao campeao : campeoes) {
			System.out.println(campeao.getAno() + ": " + campeao.getSelecao());
		}
	}
}
