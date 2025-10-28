package controller;

import java.util.Scanner;

public class Programa01 {
    // Pesquisa Binária (mais perfomática)
    // Pesquisa mais eficiente do que a sequencial, mas só funciona em arrays ordenados.
    // O seu algoritmo funciona da seguinte forma:

    // * Passos:
    // 1. Divide o array ao meio.
    // 2.Compara o elemento central com a chave
    // 3. Se for igual, elemento encontrado!
    // 4. Se a chave for menor -> pesquisar na esquerda.
    // 5. Se a chave for maior -> pesquisar na direita.
    // 6. O processo se repete até encontrar ou esgotar as possibilidades.

    // -> Vantagem: muito mais rápida que a sequêncial em arrays grandes.
    // -> Desvantagens: exige que o array esteja ordenado.

    public static int pesquisar(int[] numeros, int num) {
        int ini = 0;
        int fim = numeros.length - 1;

        while (ini <= fim) {
            int meio = (ini + fim) / 2;

            if (numeros[meio] == num)
                return meio;
            else if (numeros[meio] < num)
                ini = meio + 1;
            else if(numeros[meio] > num)
                fim = meio - 1;
        }

        return -1;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int[] numeros = {3, 17, 23, 26, 38, 42, 68, 70, 79, 83};

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
