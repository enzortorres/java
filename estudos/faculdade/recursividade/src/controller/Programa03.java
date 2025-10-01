package controller;

import java.util.Scanner;

public class Programa03 {
	public static String inverterRecursivoString(String palavra) {
		if (palavra.length() <= 1) {
			return palavra;
		}
		return palavra.charAt(palavra.length() - 1);
	}
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Digite uma palavra: ");
		String palavra = scanner.nextLine();
		
		System.out.println(inverterRecursivoString(palavra));
	}

}
