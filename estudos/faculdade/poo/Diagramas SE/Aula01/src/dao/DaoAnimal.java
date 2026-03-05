package dao;

import model.Animal;

public class DaoAnimal {
	private static final String url = "jdbc:mysql://localhost/veterinaria";
	
	public void inserir(Animal animal) {
		String sql = "INSERT INTO animal(nome, especie, raca) VALUES ('" + animal.getName() + "', '" + animal.getEspecie() + "', '" + animal.getRaca()  + "')";
		
		System.out.println(sql);
	}
	
}
