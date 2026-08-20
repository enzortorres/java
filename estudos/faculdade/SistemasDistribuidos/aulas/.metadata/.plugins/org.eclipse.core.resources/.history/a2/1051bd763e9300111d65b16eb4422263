// Enzo Ribas Torres
package controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import model.Empresa;

public class CtrlPrograma {
	public static void main(String[] args) {
		// Instanciando objetos Empresa
		Empresa e1 = new Empresa("12.345.678/0001-99", "Alpha Tecnologia", 50);
		Empresa e2 = new Empresa("98.765.432/0001-11", "Beta Sistemas", 200);
		Empresa e3 = new Empresa("11.222.333/0001-44", "Delta Consultoria", 10);
		Empresa e4 = new Empresa("55.666.777/0001-88", "Omega Indústria", 120);

		// Colocando objetos Empresa em um ArrayList
		ArrayList<Empresa> listaEmpresas = new ArrayList<>();
		listaEmpresas.add(e1);
		listaEmpresas.add(e2);
		listaEmpresas.add(e3);
		listaEmpresas.add(e4);

		// Ordenando pelo nome
		Collections.sort(listaEmpresas, (a, b) -> a.getNome().compareTo(b.getNome()));
		System.out.println("Apresentando as empresas ordenadas pelo nome:");
		for(Empresa e : listaEmpresas)
			System.out.println(e);
		System.out.println();

		// Ordenando pelo número de empregados
		Collections.sort(listaEmpresas, (a, b) -> Integer.compare(a.getNumEmpregados(), b.getNumEmpregados()));
		System.out.println("Apresentando as empresas ordenadas pelo número de empregados:");
		for(Empresa e : listaEmpresas)
			System.out.println(e);
		System.out.println();
		System.out.println();

		// Obtendo o objeto Class que descreve a classe Empresa através do envio
		// da mensagem 'getClass()' para o objeto apontado por e1
		Class objClasseEmpresa = e1.getClass();
		System.out.println("Classe do objeto apontado por e1: " + objClasseEmpresa);

		System.out.println("Listando os atributos de " + objClasseEmpresa);
		for(Field f : objClasseEmpresa.getDeclaredFields())
			System.out.println("Atributo: " + f);

		System.out.println("\nListando os métodos de " + objClasseEmpresa);
		for(Method m : objClasseEmpresa.getDeclaredMethods())
			System.out.println("Método: " + m);
	}
}
