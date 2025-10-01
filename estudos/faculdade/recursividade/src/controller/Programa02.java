package controller;

public class Programa02 {
	public static int fatorarIterativo(int num ) {
		int fatorial = 1;

		for (int i = num; i >= 1; i--) {
			fatorial *= i;
			
		}
		
		return fatorial;
	}

}