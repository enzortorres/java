package controller;

import java.util.Scanner;

public class Programa01 {
    // pesquisa sequêncial (não perfomático)
    // Percorre todos os elementos de um array do inicio até o fim até encontrar o valor desejado.

    // -> Vantagem: funciona em arrays não ordenados.
    // -> Desvantagem: é lenta em arrays muito grandes.
    public static int pesquisar(int[] numeros, int num) {
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == num)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numeros = {17, 26, 42, 70, 83, 3, 23, 38, 68, 79};

        System.out.print("Digite um número para pesquisa: ");
        int num = input.nextInt();

        int posicao = pesquisar(numeros, num);

        if (posicao != -1)
            System.out.print("Número encontrado na posição: " + posicao);
        else
            System.out.print("Número não encontrado!");

        input.close();
    }
}
