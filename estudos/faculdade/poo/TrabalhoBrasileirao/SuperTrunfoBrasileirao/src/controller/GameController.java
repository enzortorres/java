package controller;

import dao.CartaDAO;
import model.Carta;

import java.util.*;

public class GameController {

    private List<Carta> todasCartas;
    private List<Carta> cartasDisponiveis;
    private int pontosJogador;
    private int pontosMaquina;
    private int rodada;

    public GameController() {
        todasCartas = new CartaDAO().listarTodas();
        iniciarPartida();
    }

    public void iniciarPartida() {
        cartasDisponiveis = new ArrayList<>(todasCartas);
        pontosJogador = 0;
        pontosMaquina = 0;
        rodada = 0;
    }

    public List<Carta> getTodasCartas() {
        return Collections.unmodifiableList(todasCartas);
    }

    public List<Carta> getCartasDisponiveis() {
        return Collections.unmodifiableList(cartasDisponiveis);
    }

    /** Sorteia um jogador para a máquina, excluindo o do jogador humano. */
    public Carta sortearCartaMaquina(Carta cartaJogador) {
        List<Carta> opcoes = new ArrayList<>(cartasDisponiveis);
        opcoes.remove(cartaJogador);
        if (opcoes.isEmpty()) return null;
        return opcoes.get(new Random().nextInt(opcoes.size()));
    }

    /**
     * Executa a rodada e retorna o resultado.
     * +1 jogador ganhou, -1 máquina ganhou, 0 empate.
     */
    public int jogarRodada(Carta jogador, Carta maquina) {
        rodada++;
        cartasDisponiveis.remove(jogador);
        cartasDisponiveis.remove(maquina);

        int[] valJ = atributos(jogador);
        int[] valM = atributos(maquina);

        int vitorias = 0;
        for (int i = 0; i < valJ.length; i++) {
            if (valJ[i] > valM[i]) vitorias++;
            else if (valM[i] > valJ[i]) vitorias--;
        }

        if (vitorias > 0)      { pontosJogador++; return 1; }
        else if (vitorias < 0) { pontosMaquina++;  return -1; }
        else                   { return 0; }
    }

    private int[] atributos(Carta c) {
        return new int[]{ c.getGols(), c.getTitulos(), c.getGolsSelecao(),
                          c.getValorPico(), c.getPremios() };
    }

    public int getPontosJogador() { return pontosJogador; }
    public int getPontosMaquina() { return pontosMaquina; }
    public int getRodada()        { return rodada; }
    public boolean partidaEncerrada() { return cartasDisponiveis.size() <= 1; }
}
