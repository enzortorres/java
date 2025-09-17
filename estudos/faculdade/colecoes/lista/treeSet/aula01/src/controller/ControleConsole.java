package controller;

import java.util.TreeSet;

import model.Console;

public class ControleConsole {

	public static void main(String[] args) {
		TreeSet<Console> consoles = new TreeSet<Console>();

		consoles.add(new Console("Magnavox Odyssey", 1972));
		consoles.add(new Console("Atari 2600", 1976));
		consoles.add(new Console("Intellivision", 1980));
		consoles.add(new Console("Nintendo", 1983));
		consoles.add(new Console("Master System", 1986));
		consoles.add(new Console("Mega Drive", 1988));
		consoles.add(new Console("Super Nintendo", 1990));
		
		for (Console console : consoles) {
			System.out.println(console.getAno() + ": " + console.getDescricao());
		}
	}

}
