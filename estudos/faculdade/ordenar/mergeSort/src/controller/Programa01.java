package controller;

public class Programa01 {
    public static void intercalar(int[] numeros, int ini, int meio, int fim) {
        int n1 = meio - ini + 1;
        int n2 = fim - meio;

        int[] esquerda = new int[n1];
        int[] direita = new int[n2];

        for(int i = 0; i < n1; i++)
            esquerda[i] = numeros[ini + i];
        for(int j = 0; j < n2; j++)
            direita[j] = numeros[meio + j];

        int i = 0, j = 0, k = ini;

        while(i < n1 && j < n2) {
            if (esquerda[i] <= direita[j])
                numeros[k++] = esquerda[i++];
            else
                numeros[k++] = direita[j++];
        }
        while(i < n1) numeros[k++] = esquerda[i++];
        while(i < n2) numeros[k++] = esquerda[i++];
    }

    public static int[] ordenar(int[] numeros, int ini, int fim) {
        if (ini < fim) {
            int meio = (ini + fim) / 2;

            ordenar(numeros, ini, meio);
            ordenar(numeros, meio + 1, fim);

            intercalar(numeros, ini, meio, fim);
        }

        return numeros;
    }

    public static void main(String[] args) {
        int[] numeros = {3, 17, 26, 42, 70, 83, 1, 5, 23, 38, 68, 79, 94};
        System.out.print("Antes da ordenação: ");
        for (int i = 0; i < numeros.length-1; i++) {
            System.out.print(numeros[i] + " | ");
        }

        numeros = ordenar(numeros, );

        System.out.print("\nDepois da ordenação: ");
        for (int i = 0; i < numeros.length-1; i++) {
            System.out.print(numeros[i] + " | ");
        }
    }
}
