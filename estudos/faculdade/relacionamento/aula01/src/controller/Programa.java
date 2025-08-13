package controller;

import models.*;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("CADASTRO DO SETOR");
        System.out.print("Digite a sigla: ");
        String sigla = sc.nextLine();
        System.out.print("Digite a descrição: ");
        String descricao = sc.nextLine();

        System.out.println("CADASTRO DO GERENTE");
        System.out.print("Digite o cpf: ");
        String cpf = sc.nextLine();
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();

        Setor setor = new Setor(sigla, descricao);
        Gerente gerente = new Gerente(cpf, nome);

        setor.setGerente(gerente);
        gerente.setSetor(setor);

        System.out.printf("O funcionário %s é o gerente do setor %s", setor.getGerente().getNome(), gerente.getSetor().getDescricao());
    }
}
