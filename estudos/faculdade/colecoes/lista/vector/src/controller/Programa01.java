package controller;

import java.util.Vector;

public class Programa01 {
    public static void main(String[] args) {
        // lista encadeada
        Vector colecao = new Vector();

        Integer idade = 23;
        String nome = "Enzo";
        Float altura = 1.78f;

        colecao.add(nome);
        colecao.add(altura);
        colecao.add(idade);

        System.out.println(colecao);

        // declarando um vector com generics
        // permite a coleção armazenar apenas um tipo de objeto

        // sintax
//        Vector<Classe> nomeColecao = new Vector<Classe>;

        // exemplo:
        Vector<String> pessoas = new Vector<String>();

    }
}
