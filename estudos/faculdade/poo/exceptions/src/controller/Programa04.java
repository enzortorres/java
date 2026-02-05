package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa04 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String names[] = {
				"Maria",
				"Fernanda",
				"Gabriel",
				"Tainá",
				"Mariana",
				"Ana"
		};
		
		System.out.print("Digite um número de 0 a 5: ");
		int num = input.nextInt();
		
		try {
			System.out.print("Nome consultado: " + names[num]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("O número deve ser entre 0 e 5!");
			System.out.println("Erro: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println("O número deve ser inteiro!");
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
