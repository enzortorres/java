package view;

import controller.GameController;
import model.Carta;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {

    private static final String BASE_IMG_URL = "https://flagcdn.com/w320/";

    private GameController ctrl;
    private Carta cartaSelecionada;
    private Carta cartaMaquina;

    private JPanel painelCartas;
    private JScrollPane scrollCartas;
    private JPanel painelJogador;
    private JPanel painelMaquina;
    private JLabel lblResultadoRodada;
    private JLabel lblPlacar;
    private JButton btnJogar;
    private JButton btnNovaPartida;

    // paisCodigo -> imagem da bandeira (compartilhada entre botões e painéis)
    private final Map<String, BufferedImage> imageCache = new HashMap<>();
    // paisCodigo -> botão na barra superior
    private final Map<String, JButton> botoesPorPais = new HashMap<>();

    public MainFrame() {
        ctrl = new GameController();
        setTitle("Super Trunfo Copa do Mundo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 760);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(0, 60, 20));

        construirUI();
        atualizarCartas();
        precarregarImagens();
        setVisible(true);
    }

    // ── Construção da UI ────────────────────────────────────────────────────────

    private void construirUI() {
        // Topo — barra de seleção de cartas
        painelCartas = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));
        painelCartas.setBackground(new Color(0, 45, 15));
        scrollCartas = new JScrollPane(painelCartas,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollCartas.setPreferredSize(new Dimension(1100, 185));
        scrollCartas.setBorder(tituloBorda("Escolha sua seleção"));
        add(scrollCartas, BorderLayout.NORTH);

        // Centro — painéis Você / Máquina
        JPanel centro = new JPanel(new GridLayout(1, 2, 20, 0));
        centro.setBackground(new Color(0, 60, 20));
        centro.setBorder(new EmptyBorder(10, 20, 10, 20));
        painelJogador = criarPainelInfo("Você");
        painelMaquina = criarPainelInfo("Máquina");
        centro.add(painelJogador);
        centro.add(painelMaquina);
        add(centro, BorderLayout.CENTER);

        // Rodapé — resultado, placar e botões
        JPanel rodape = new JPanel(new BorderLayout(10, 5));
        rodape.setBackground(new Color(0, 60, 20));
        rodape.setBorder(new EmptyBorder(5, 20, 15, 20));

        lblResultadoRodada = new JLabel("Selecione uma seleção e clique em JOGAR", SwingConstants.CENTER);
        lblResultadoRodada.setFont(new Font("Arial", Font.BOLD, 16));
        lblResultadoRodada.setForeground(Color.WHITE);

        lblPlacar = new JLabel("Você: 0  |  Máquina: 0  |  Rodada: 0", SwingConstants.CENTER);
        lblPlacar.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPlacar.setForeground(new Color(220, 210, 100));

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botoes.setBackground(new Color(0, 60, 20));

        btnJogar = new JButton("▶ JOGAR");
        btnJogar.setFont(new Font("Arial", Font.BOLD, 15));
        btnJogar.setBackground(new Color(220, 180, 0));
        btnJogar.setForeground(new Color(0, 50, 0));
        btnJogar.setFocusPainted(false);
        btnJogar.addActionListener(e -> jogar());

        btnNovaPartida = new JButton("Nova Partida");
        btnNovaPartida.setFont(new Font("Arial", Font.BOLD, 15));
        btnNovaPartida.setBackground(new Color(180, 80, 0));
        btnNovaPartida.setForeground(Color.WHITE);
        btnNovaPartida.setFocusPainted(false);
        btnNovaPartida.addActionListener(e -> novaPartida());

        botoes.add(btnJogar);
        botoes.add(btnNovaPartida);

        rodape.add(lblResultadoRodada, BorderLayout.NORTH);
        rodape.add(lblPlacar, BorderLayout.CENTER);
        rodape.add(botoes, BorderLayout.SOUTH);
        add(rodape, BorderLayout.SOUTH);
    }

    // ── Barra de cartas ─────────────────────────────────────────────────────────

    private void atualizarCartas() {
        painelCartas.removeAll();
        botoesPorPais.clear();
        List<Carta> disponiveis = ctrl.getCartasDisponiveis();

        for (Carta c : ctrl.getTodasCartas()) {
            boolean disponivel = disponiveis.contains(c);
            Color bgColor = !disponivel
                    ? new Color(30, 30, 30)
                    : (c.isSuperTrunfo() ? new Color(160, 120, 0) : new Color(0, 80, 30));

            JButton btn = new JButton(
                    "<html><center><small>" + c.getCodigo() + "</small><br><b>"
                    + c.getNome() + "</b>"
                    + (c.isSuperTrunfo() ? "<br><font color='gold'>★</font>" : "")
                    + "</center></html>");
            btn.setPreferredSize(new Dimension(90, 145));
            btn.setFont(new Font("Arial", Font.PLAIN, 10));
            btn.setBackground(bgColor);
            btn.setForeground(disponivel ? Color.WHITE : new Color(80, 80, 80));
            btn.setFocusPainted(false);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setEnabled(disponivel);

            if (imageCache.containsKey(c.getPaisCodigo())) {
                Image ico = imageCache.get(c.getPaisCodigo()).getScaledInstance(70, 47, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(ico));
            }

            if (disponivel) {
                btn.addActionListener(e -> {
                    cartaSelecionada = c;
                    preencherPainel(painelJogador, c, "Você", null);
                    limparPainel(painelMaquina, "Máquina");
                    lblResultadoRodada.setText("Seleção escolhida: " + c.getNome() + " — clique em JOGAR");
                    lblResultadoRodada.setForeground(Color.WHITE);
                });
            }

            botoesPorPais.put(c.getPaisCodigo(), btn);
            painelCartas.add(btn);
        }
        painelCartas.revalidate();
        painelCartas.repaint();
    }

    // ── Pré-carregamento de imagens ──────────────────────────────────────────────

    private void precarregarImagens() {
        for (Carta c : ctrl.getTodasCartas()) {
            String pais = c.getPaisCodigo();
            if (imageCache.containsKey(pais)) continue;
            new SwingWorker<BufferedImage, Void>() {
                @Override
                protected BufferedImage doInBackground() throws Exception {
                    return ImageIO.read(new URL(BASE_IMG_URL + pais + ".png"));
                }
                @Override
                protected void done() {
                    try {
                        BufferedImage img = get();
                        if (img == null) return;
                        imageCache.put(pais, img);
                        JButton btn = botoesPorPais.get(pais);
                        if (btn != null && btn.isEnabled()) {
                            btn.setIcon(new ImageIcon(img.getScaledInstance(70, 47, Image.SCALE_SMOOTH)));
                            btn.revalidate();
                            btn.repaint();
                        }
                    } catch (Exception ignored) {}
                }
            }.execute();
        }
    }

    // ── Lógica da rodada ────────────────────────────────────────────────────────

    private void jogar() {
        if (cartaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma seleção primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!ctrl.getCartasDisponiveis().contains(cartaSelecionada)) {
            JOptionPane.showMessageDialog(this, "Esta seleção já foi usada!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        cartaMaquina = ctrl.sortearCartaMaquina(cartaSelecionada);
        if (cartaMaquina == null) {
            JOptionPane.showMessageDialog(this, "Sem seleções disponíveis para a máquina!", "Fim", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int[] comp = compararAtributos(cartaSelecionada, cartaMaquina);
        int resultado = ctrl.jogarRodada(cartaSelecionada, cartaMaquina);

        preencherPainel(painelJogador, cartaSelecionada, "Você", comp);
        preencherPainel(painelMaquina, cartaMaquina, "Máquina", invertido(comp));
        colorirVencedor(resultado);

        lblPlacar.setText("Você: " + ctrl.getPontosJogador()
                + "  |  Máquina: " + ctrl.getPontosMaquina()
                + "  |  Rodada: " + ctrl.getRodada());

        cartaSelecionada = null;
        atualizarCartas();

        if (ctrl.partidaEncerrada()) anunciarVencedor();
    }

    private int[] compararAtributos(Carta j, Carta m) {
        int[] vJ = {j.getTitulos(), j.getGols(), j.getParticipacoes(), j.getPontosFifa(), j.getJogadoresEstrela()};
        int[] vM = {m.getTitulos(), m.getGols(), m.getParticipacoes(), m.getPontosFifa(), m.getJogadoresEstrela()};
        int[] res = new int[5];
        for (int i = 0; i < 5; i++) res[i] = Integer.compare(vJ[i], vM[i]);
        return res;
    }

    private int[] invertido(int[] arr) {
        int[] inv = new int[arr.length];
        for (int i = 0; i < arr.length; i++) inv[i] = -arr[i];
        return inv;
    }

    private void colorirVencedor(int resultado) {
        if (resultado > 0) {
            lblResultadoRodada.setText("Você VENCEU a rodada!");
            lblResultadoRodada.setForeground(new Color(50, 220, 50));
            painelJogador.setBorder(tituloBordaColorida("Você", new Color(50, 220, 50)));
            painelMaquina.setBorder(tituloBordaColorida("Máquina", new Color(220, 50, 50)));
        } else if (resultado < 0) {
            lblResultadoRodada.setText("Máquina VENCEU a rodada!");
            lblResultadoRodada.setForeground(new Color(220, 50, 50));
            painelJogador.setBorder(tituloBordaColorida("Você", new Color(220, 50, 50)));
            painelMaquina.setBorder(tituloBordaColorida("Máquina", new Color(50, 220, 50)));
        } else {
            lblResultadoRodada.setText("EMPATE na rodada!");
            lblResultadoRodada.setForeground(Color.YELLOW);
            painelJogador.setBorder(tituloBordaColorida("Você", Color.YELLOW));
            painelMaquina.setBorder(tituloBordaColorida("Máquina", Color.YELLOW));
        }
    }

    private void anunciarVencedor() {
        int pJ = ctrl.getPontosJogador();
        int pM = ctrl.getPontosMaquina();

        boolean jogadorVenceu = pJ > pM;
        boolean empate        = pJ == pM;

        Color corFundo, corTitulo, corBorda;
        String tituloTexto, subtituloTexto;

        if (empate) {
            corFundo       = new Color(60, 60, 20);
            corTitulo      = new Color(255, 230, 50);
            corBorda       = new Color(255, 220, 0);
            tituloTexto    = "EMPATE!";
            subtituloTexto = "A Copa terminou empatada!";
        } else if (jogadorVenceu) {
            corFundo       = new Color(0, 70, 20);
            corTitulo      = new Color(80, 255, 80);
            corBorda       = new Color(50, 220, 50);
            tituloTexto    = "VOCÊ VENCEU!";
            subtituloTexto = "Parabéns! Você é o campeão da Copa!";
        } else {
            corFundo       = new Color(70, 15, 15);
            corTitulo      = new Color(255, 80, 80);
            corBorda       = new Color(220, 50, 50);
            tituloTexto    = "MÁQUINA VENCEU!";
            subtituloTexto = "Que pena! A máquina levantou a taça desta vez.";
        }

        JDialog dialog = new JDialog(this, "Fim de Partida", true);
        dialog.setSize(420, 340);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(corFundo);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(corBorda, 3),
                new EmptyBorder(20, 30, 20, 30)));

        JLabel lblTitulo = new JLabel(tituloTexto, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(corTitulo);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        painel.add(lblTitulo);
        painel.add(Box.createVerticalStrut(6));

        JLabel lblSub = new JLabel(subtituloTexto, SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.ITALIC, 13));
        lblSub.setForeground(new Color(200, 200, 200));
        lblSub.setAlignmentX(CENTER_ALIGNMENT);
        painel.add(lblSub);
        painel.add(Box.createVerticalStrut(18));

        JLabel lblPlacarFinal = new JLabel(
                "Você: " + pJ + " pts   ×   Máquina: " + pM + " pts", SwingConstants.CENTER);
        lblPlacarFinal.setFont(new Font("Arial", Font.BOLD, 17));
        lblPlacarFinal.setForeground(Color.WHITE);
        lblPlacarFinal.setAlignmentX(CENTER_ALIGNMENT);
        painel.add(lblPlacarFinal);
        painel.add(Box.createVerticalStrut(24));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(corFundo);
        btnPanel.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnNova = new JButton("▶ Nova Partida");
        btnNova.setFont(new Font("Arial", Font.BOLD, 14));
        btnNova.setBackground(new Color(220, 180, 0));
        btnNova.setForeground(new Color(0, 50, 0));
        btnNova.setFocusPainted(false);
        btnNova.addActionListener(e -> {
            dialog.dispose();
            novaPartida();
        });

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFechar.setBackground(new Color(80, 80, 100));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setFocusPainted(false);
        btnFechar.addActionListener(e -> dialog.dispose());

        btnPanel.add(btnNova);
        btnPanel.add(btnFechar);
        painel.add(btnPanel);

        dialog.add(painel, BorderLayout.CENTER);
        btnJogar.setEnabled(false);
        dialog.setVisible(true);
    }

    private void novaPartida() {
        ctrl.iniciarPartida();
        cartaSelecionada = null;
        cartaMaquina = null;
        limparPainel(painelJogador, "Você");
        limparPainel(painelMaquina, "Máquina");
        lblResultadoRodada.setText("Selecione uma seleção e clique em JOGAR");
        lblResultadoRodada.setForeground(Color.WHITE);
        lblPlacar.setText("Você: 0  |  Máquina: 0  |  Rodada: 0");
        btnJogar.setEnabled(true);
        atualizarCartas();
    }

    // ── Painéis de detalhe ──────────────────────────────────────────────────────

    private JPanel criarPainelInfo(String titulo) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(0, 70, 25));
        p.setBorder(tituloBordaColorida(titulo, new Color(100, 180, 100)));
        return p;
    }

    private void limparPainel(JPanel p, String titulo) {
        p.removeAll();
        p.setBorder(tituloBordaColorida(titulo, new Color(100, 180, 100)));
        p.revalidate();
        p.repaint();
    }

    private void preencherPainel(JPanel p, Carta c, String titulo, int[] comparacao) {
        p.removeAll();
        p.setBorder(tituloBordaColorida(titulo, new Color(100, 180, 100)));

        // --- Bandeira do país ---
        JLabel imgLabel = new JLabel("", SwingConstants.CENTER);
        imgLabel.setPreferredSize(new Dimension(200, 133));

        if (imageCache.containsKey(c.getPaisCodigo())) {
            Image scaled = imageCache.get(c.getPaisCodigo()).getScaledInstance(200, 133, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaled));
        } else {
            imgLabel.setText("...");
            imgLabel.setForeground(new Color(150, 200, 150));
            String pais = c.getPaisCodigo();
            new SwingWorker<BufferedImage, Void>() {
                @Override
                protected BufferedImage doInBackground() throws Exception {
                    return ImageIO.read(new URL(BASE_IMG_URL + pais + ".png"));
                }
                @Override
                protected void done() {
                    try {
                        BufferedImage img = get();
                        if (img == null) return;
                        imageCache.put(pais, img);
                        imgLabel.setIcon(new ImageIcon(img.getScaledInstance(200, 133, Image.SCALE_SMOOTH)));
                        imgLabel.setText("");
                        imgLabel.revalidate();
                        imgLabel.repaint();
                        JButton btn = botoesPorPais.get(pais);
                        if (btn != null && btn.isEnabled()) {
                            btn.setIcon(new ImageIcon(img.getScaledInstance(70, 47, Image.SCALE_SMOOTH)));
                            btn.revalidate();
                            btn.repaint();
                        }
                    } catch (Exception ignored) {}
                }
            }.execute();
        }

        JPanel imgContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 4));
        imgContainer.setBackground(new Color(0, 70, 25));
        imgContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, 145));
        imgContainer.add(imgLabel);
        p.add(imgContainer);

        // --- Nome ---
        JLabel nome = new JLabel(c.getNome() + (c.isSuperTrunfo() ? " ★" : ""), SwingConstants.CENTER);
        nome.setFont(new Font("Arial", Font.BOLD, 18));
        nome.setForeground(c.isSuperTrunfo() ? new Color(255, 215, 0) : Color.WHITE);
        nome.setAlignmentX(CENTER_ALIGNMENT);
        p.add(nome);

        // --- Continente ---
        JLabel continente = new JLabel(c.getContinente(), SwingConstants.CENTER);
        continente.setFont(new Font("Arial", Font.ITALIC, 13));
        continente.setForeground(new Color(180, 230, 180));
        continente.setAlignmentX(CENTER_ALIGNMENT);
        p.add(continente);
        p.add(Box.createVerticalStrut(8));

        // --- Atributos com destaque de vitória/derrota ---
        String[] labels = {"Títulos Mundiais", "Gols na Copa", "Participações", "Pontos FIFA", "Jogadores Estrela"};
        int[] valores = {c.getTitulos(), c.getGols(), c.getParticipacoes(), c.getPontosFifa(), c.getJogadoresEstrela()};

        for (int i = 0; i < labels.length; i++) {
            Color bgLinha  = new Color(0, 70, 25);
            Color corLabel = new Color(180, 230, 180);
            Color corValor = Color.WHITE;

            if (comparacao != null) {
                if (comparacao[i] > 0) {
                    bgLinha  = new Color(0, 70, 0);
                    corLabel = new Color(80, 230, 80);
                    corValor = new Color(80, 230, 80);
                } else if (comparacao[i] < 0) {
                    bgLinha  = new Color(70, 0, 0);
                    corLabel = new Color(230, 80, 80);
                    corValor = new Color(230, 80, 80);
                } else {
                    bgLinha  = new Color(50, 50, 0);
                    corLabel = new Color(230, 230, 60);
                    corValor = new Color(230, 230, 60);
                }
            }

            JPanel linha = new JPanel(new BorderLayout());
            linha.setBackground(bgLinha);
            linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

            JLabel lbl = new JLabel("  " + labels[i] + ":");
            lbl.setFont(new Font("Arial", Font.PLAIN, 14));
            lbl.setForeground(corLabel);

            JLabel val = new JLabel(String.valueOf(valores[i]) + "  ", SwingConstants.RIGHT);
            val.setFont(new Font("Arial", Font.BOLD, 14));
            val.setForeground(corValor);

            linha.add(lbl, BorderLayout.WEST);
            linha.add(val, BorderLayout.EAST);
            p.add(linha);
        }

        p.revalidate();
        p.repaint();
    }

    // ── Helpers de borda ────────────────────────────────────────────────────────

    private Border tituloBorda(String titulo) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 180, 100), 1),
                titulo, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12), new Color(180, 230, 180));
    }

    private Border tituloBordaColorida(String titulo, Color cor) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(cor, 2),
                titulo, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 13), cor);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
