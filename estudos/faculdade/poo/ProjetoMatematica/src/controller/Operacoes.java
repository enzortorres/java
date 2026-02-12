package controller;

import exceptions.RaizQuadradaException;

public class Operacoes {
	public static float calcularRaiz(int n) {
		if (n < 0) {
			throw new RaizQuadradaException();
		}
		return (float)Math.sqrt(n);
			
	}
}
