package controller;

import java.util.Iterator;
import java.util.Scanner;

import model.Animal;
import model.Cliente;

public class Programa {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("CADASTRO DE CLIENTE");
        System.out.print("CPF: ");
        String cpf = input.nextLine();
        System.out.print("Nome: ");
        String nome = input.nextLine();
        char resposta;

        Cliente cliente = new Cliente(cpf, nome);

        System.out.println("CADASTRO DE ANIMAIS");
        do {
            System.out.print("Nome: ");
            nome = input.nextLine();
            System.out.print("Espécie: ");
            String especie = input.nextLine();
            System.out.print("Raça: ");
            String raca = input.nextLine();

            Animal animal = new Animal(nome, especie, raca);
            cliente.addAnimal(animal);

            System.out.print("Deseja continuar a cadastrar (S/N)? ");
            resposta = input.next().charAt(0);
            input.nextLine();
        } while (resposta == 'S' || resposta == 's');

        System.out.println("ANIMAIS DO CLIENTE " + cliente.getNome());
        //System.out.println(cliente.getAnimais());
        Iterator iterator = cliente.getAnimais().iterator();

        while(iterator.hasNext()) {
            Animal animal = (Animal)iterator.next();
            System.out.print("Nome: " + animal.getNome());
            System.out.print(" | Espécie: " + animal.getEspecie());
            System.out.println(" | Raça: " + animal.getRaca());
        }
    }
}