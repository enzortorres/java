package controller;

import java.util.Scanner;

import exceptions.RaizQuadradaException;

public class Programa01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Digite um número: ");
		int n = input.nextInt();
		
		try {
			System.out.println("A Raiz Quadrada de " + n + " é " + Operacoes.calcularRaiz(n));			
		} catch (RaizQuadradaException e) {
			System.out.println(e.getMessage());
		}
	}

}
