package controller;

import java.util.HashSet;

public class Programa02 {

    public static void main(String[] args) {

        HashSet<String> games = new HashSet<String>();

        games.add("Seaquest");
        games.add("Super Mario Bros");
        games.add("Alex kid");
        games.add("Sonic");
        games.add("Donkey Kong");

        System.out.println(games);

        games.add("Megaman");
        System.out.println(games);

        games.add("Sonic");
        System.out.println(games);

        games.add("Love Nikki");
        System.out.println(games);

    }

}