package controller;

import model.Cliente;

public class Programa01 {
	public static void main(String[] args) {
		Cliente cliente;
		cliente = new Cliente();
//		cliente = null; throws NullPointerException
		
		try {			
			cliente.setCpf("12345678910");
			cliente.setNome("Fernanda");
			System.out.println("CPF: " + cliente.getCpf());
			System.out.print("Nome: " + cliente.getNome());
		} catch (NullPointerException e) {
			System.out.println("Objeto de cliente não foi criado");
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace(); // printa o erro 
		}
	}
}