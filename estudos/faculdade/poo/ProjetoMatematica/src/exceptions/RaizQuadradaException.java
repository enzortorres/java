package exceptions;

//public class RaizQuadradaException extends RuntimeException {

public class RaizQuadradaException extends Exception {
	public RaizQuadradaException() {
		super("Não existe raiz quadrada de número negativo no conjunto de números naturais.");
	}
}
