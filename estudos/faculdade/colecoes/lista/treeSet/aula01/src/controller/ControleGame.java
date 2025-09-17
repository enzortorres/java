package controller;

import java.util.HashSet;
import java.util.Iterator;

import model.Game;

public class ControleGame {

    public static void main(String[] args) {
        HashSet<Game> games = new HashSet<Game>();

        games.add(new Game("Enduro","Atari"));
        games.add(new Game("Ninja Gaiden","NES"));
        games.add(new Game("Double Dragon","MS"));
        games.add(new Game("Toy Story","MD"));
        games.add(new Game("Enduro","Atari"));

        Iterator it = games.iterator();
        while(it.hasNext()) {
            Game game = (Game)it.next();
            System.out.println(game.getTitulo()+"|"+ game.getConsole());
        }

        System.out.println("**************************************************");

        games.add(new Game("Formula 1","Odyssey"));
        for(Game game: games) {
            System.out.println(game.getTitulo()+"|"+ game.getConsole());
        }
    }

}