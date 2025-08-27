package controller;

import model.Animal;

import java.util.Vector;

public class Programa03 {
    public static void main(String[] args) {
        Vector<Animal> animais = new Vector<Animal>();

        animais.add(new Animal("Betinha", "Canina", "Street dog"));
        animais.add(new Animal("Pachola", "Canina", "Street dog"));
        animais.add(new Animal("Mel", "Canina", "Poodle"));
        animais.add(new Animal("Luana", "Canina", "Street dog"));
        animais.add(new Animal("Solange\", "Canina", "Street dog"));
    }
}
