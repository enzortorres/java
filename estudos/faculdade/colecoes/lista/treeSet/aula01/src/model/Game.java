package model;

public class Game {
    private String titulo, console;

    public Game(String titulo, String console) {
        this.titulo = titulo;
        this.console = console;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }


}