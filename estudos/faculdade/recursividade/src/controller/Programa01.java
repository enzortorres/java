package controller;

import java.util.Scanner;

public class Programa01 {
 	public static int somarIterativo(int num) {
 		int soma = 0;
 		
 		for (int i = 1; i <= num; i++) {
 			soma += i;
 		}
 		
 		return soma;
 	}
 	
 	public static int somarRecursivo(int num) {
 		if (num < 0) {
 			return 0;
 		}
 		
 		return num + somarRecursivo(num - 1);
 	}

 	public static void main(String args[]) {
 		Scanner scanner = new Scanner(System.in);
 		
		System.out.print("Digite um número: ");
		int num = scanner.nextInt();

		System.out.println(somarIterativo(num));
		System.out.println(somarRecursivo(num));
	}
}
