package controller;

import dao.DaoAnimal;
import model.Animal;

public class ControleAnimal1 {
	private Animal animal = new Animal();
	private DaoAnimal daoAnimal = new DaoAnimal();
	
	public void cadastrarAnimal(String nome, String especie, String raca) {
		animal.setName(nome);
		animal.setEspecie(especie);
		animal.setRaca(raca);
		
		daoAnimal.inserir(animal);
	}
	
	public void consultarAnimal() {
		
	}
	
	public void alterarAnimal() {
		
	}
	
	public void excluirAnimal() {
		
	}
	
	public static void main(String[] args) {
		
	}
}
	