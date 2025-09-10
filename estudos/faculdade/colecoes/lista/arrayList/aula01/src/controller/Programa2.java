package controller;

import java.util.ArrayList;

public class Programa2 {
    public static void main(String[] args) {
        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("Pedro");
        nomes.add("Maria");
        nomes.add("Enzo");
        nomes.add("Bianca");
        nomes.add("Antonio");
        System.out.println(nomes);
        nomes.add(2, "Mariana");
        System.out.println(nomes);
        nomes.set(3, "Gabriel");
        System.out.println(nomes);

        System.out.println("Primeiro nome: " + nomes.get(0));
        System.out.println("Último nome: " + nomes.get(nomes.size() - 1));

        if (nomes.contains("Mariana")) {
            System.out.println("Mariana existe na lista.");
        } else {
            System.out.println("Mariana não existe na lista.");
        }

        nomes.remove("Mariana");

        if (nomes.contains("Mariana")) {
            System.out.println("Mariana existe na lista.");
        } else {
            System.out.println("Mariana não existe na lista.");
        }
    }
}
