package controller;

import java.util.TreeSet;

public class Programa04 {

	public static void main(String[] args) {
		TreeSet<String> nomes = new TreeSet<String>();
		
		nomes.add("Enzo");
		nomes.add("Jose");
		nomes.add("Maria");
		nomes.add("Dalira");
		nomes.add("Gerson");
		nomes.add("Bianca");
		
		System.out.println(nomes);
		
		nomes.add("João");
		nomes.add("Marcelo");
		nomes.add("Thainá");
		nomes.add("Guilherme");
		nomes.add("Adriane");
		nomes.add("Pamela");
		
		System.out.println(nomes);
	}
}
