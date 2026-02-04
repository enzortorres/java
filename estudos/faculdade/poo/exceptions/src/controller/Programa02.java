package controller;

import java.util.Scanner;

public class Programa02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Digite um número: ");
		int num1 = input.nextInt();
		
		System.out.print("Digite outro número: ");
		int num2 = input.nextInt();
		
		try {
			System.out.println("A divisão é " + (num1/num2));
		} catch(ArithmeticException e){
			System.out.println("\033[1;31mErro: " + e.getMessage() + "\033[0m");
		} finally {
			System.out.println("===== FIM DO PROGRAMA =====");			
		}
		
	}

}
