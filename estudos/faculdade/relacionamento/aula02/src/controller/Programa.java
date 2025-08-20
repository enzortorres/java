package controller;

import model.Animal;
import model.Cliente;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("CADASTRO DE CLIENTE");
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        Cliente cliente = new Cliente(cpf, nome);

        System.out.println("CADASTRO DE ANIMAIS");
        for (int i = 0; i < 3; i++) {
            System.out.print("Nome: ");
            String nomeAnimal = sc.nextLine();
            System.out.print("Espécie: ");
            String especieAnimal = sc.nextLine();
            System.out.print("Raça: ");
            String racaAnimal = sc.nextLine();

            Animal animal = new Animal(nomeAnimal, especieAnimal, racaAnimal);
            cliente.addAnimal(animal);
        }
        System.out.println("Animais do Cliente " + cliente.getNome());

        for (Animal animalCliente : cliente.getAnimais()) {
            System.out.println("Nome: " + animalCliente.getNome());
            System.out.println("Espécie: " + animalCliente.getEspecie());
            System.out.println("Raça: " + animalCliente.getRaca());
        }

    }
}
