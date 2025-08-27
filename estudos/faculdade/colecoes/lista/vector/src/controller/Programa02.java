package controller;

import java.util.Vector;

public class Programa02 {
    public static void main(String[] args) {
        Vector<String> pessoas = new Vector<String>();

        pessoas.add("José");
        pessoas.add("Maria");
        pessoas.add("Fernanda");
        pessoas.add("Mariana");
        pessoas.add("Gabriel");
        pessoas.add("Taína");
        pessoas.add("Mariana");

        System.out.println(pessoas);
        System.out.println("Tamanho da coleção: " + pessoas.size());

        System.out.println("Primeiro elemento: " + pessoas.firstElement());
        System.out.println("Último elemento: " + pessoas.lastElement());
        System.out.println("Elemento da posição 2: " + pessoas.get(2));
        System.out.println("Posição do elemento 'Mariana': " + pessoas.indexOf("Mariana"));
        System.out.println("Última posição do elemento 'Mariana': " + pessoas.lastIndexOf("Mariana"));
        System.out.println("A pessoa 'Enzo' está dentro da coleção? " + (pessoas.contains("Enzo") ? "Sim" : "Não"));
        // pessoas.setElementeAt(Objeto, Posição); ele substitui o objeto da posição
        // pessoas.add(Posição, Objeto); ele adiciona um objeto na posição, reorganizando os elementos seguintes
        pessoas.remove("Taína");
    }
}
