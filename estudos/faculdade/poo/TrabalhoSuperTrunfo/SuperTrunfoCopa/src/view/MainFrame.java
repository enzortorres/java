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

    private static final Color BG          = new Color(245, 252, 245);
    private static final Color VERDE_ESC   = new Color(0,  90, 25);
    private static final Color VERDE_MED   = new Color(0, 140, 50);
    private static final Color VERDE_CARD  = new Color(0,  70, 20);
    private static final Color OURO        = new Color(215, 175, 0);
    private static final Color WIN         = new Color(20, 160, 20);
    private static final Color LOSE        = new Color(200, 20,  20);
    private static final Color TIE         = new Color(160, 130, 0);

    private static final String[] ATTRS_FULL  = {
        "Títulos Mundiais", "Gols na Copa", "Participações", "Pontos FIFA", "Jogadores Estrela"
    };
    private static final String[] ATTRS_SHORT = {
        "Títulos", "Gols Copa", "Participações", "Pts. FIFA", "Estrelas"
    };

    private final GameController ctrl;
    private Carta cartaSelecionada;

    private final Map<String, BufferedImage> imageCache = new HashMap<>();
    private final Map<String, JButton>       botoesMap  = new HashMap<>();

    // Barra de cartas (topo)
    private JPanel painelCartas;

    // Painel carta — Jogador (esq)
    private JLabel lblCodJog, lblContJog, imgJog, lblNomeJog;
    private final JLabel[] miniJog = new JLabel[5];

    // Painel carta — Máquina (dir)
    private JLabel lblCodMaq, lblContMaq, imgMaq, lblNomeMaq;
    private final JLabel[] miniMaq = new JLabel[5];

    // Tabela de comparação (centro)
    private final JLabel[] valJog  = new JLabel[5];
    private final JLabel[] valMaq  = new JLabel[5];
    private final JLabel[] icoJog  = new JLabel[5];
    private final JLabel[] icoMaq  = new JLabel[5];

    // Placar
    private JLabel lblRodJog, lblRodMaq, lblGerJog, lblGerMaq, lblVencedor;

    // Botões
    private JButton btnJogar, btnNova;

    // ── Construção ───────────────────────────────────────────────────────────────
    public MainFrame() {
        ctrl = new GameController();
        setTitle("Super Trunfo Copa do Mundo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 820);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(BG);

        add(buildNorth(),  BorderLayout.NORTH);
        add(buildCenter(), BorderLayout.CENTER);
        add(buildSouth(),  BorderLayout.SOUTH);

        atualizarCartas();
        precarregarImagens();
        setVisible(true);
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  NORTH — barra de cartas + cabeçalho
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildNorth() {
        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(VERDE_ESC);

        // Scroll de cartas
        painelCartas = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        painelCartas.setBackground(new Color(0, 55, 18));
        JScrollPane scroll = new JScrollPane(painelCartas,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(1200, 140));
        scroll.setBorder(null);

        // Barra de título + botões
        JPanel bar = new JPanel(new BorderLayout(0, 0));
        bar.setBackground(VERDE_ESC);
        bar.setBorder(new EmptyBorder(5, 16, 6, 16));

        JLabel title = new JLabel("  Super Trunfo  —  Copa do Mundo");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(OURO);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnRow.setBackground(VERDE_ESC);

        btnJogar = makeBtn("  JOGAR  ", OURO, VERDE_ESC, true);
        btnJogar.addActionListener(e -> jogar());

        btnNova = makeBtn("Nova Partida", new Color(160, 60, 0), Color.WHITE, false);
        btnNova.addActionListener(e -> novaPartida());

        btnRow.add(btnJogar);
        btnRow.add(btnNova);

        bar.add(title,  BorderLayout.WEST);
        bar.add(btnRow, BorderLayout.EAST);

        north.add(scroll, BorderLayout.CENTER);
        north.add(bar,    BorderLayout.SOUTH);
        return north;
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  CENTER — [carta jogador] | [tabela] | [carta máquina]
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildCenter() {
        JPanel center = new JPanel(new BorderLayout(6, 0));
        center.setBackground(BG);
        center.setBorder(new EmptyBorder(6, 8, 4, 8));

        center.add(buildCardPanel(true),   BorderLayout.WEST);
        center.add(buildCompTable(),       BorderLayout.CENTER);
        center.add(buildCardPanel(false),  BorderLayout.EAST);

        return center;
    }

    // ── Painel de carta (jogador ou máquina) ────────────────────────────────────
    private JPanel buildCardPanel(boolean isJog) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(VERDE_MED, 2));
        p.setPreferredSize(new Dimension(225, 0));

        // Cabeçalho: código + continente
        JPanel hdr = new JPanel(new BorderLayout(3, 0));
        hdr.setBackground(VERDE_CARD);
        hdr.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        hdr.setBorder(new EmptyBorder(3, 5, 3, 5));

        JLabel cod  = badge("--",  OURO,       VERDE_CARD);
        JLabel cont = badge("---", VERDE_MED,  Color.WHITE);

        if (isJog) { lblCodJog = cod; lblContJog = cont; }
        else       { lblCodMaq = cod; lblContMaq = cont; }

        hdr.add(cod,  BorderLayout.WEST);
        hdr.add(cont, BorderLayout.CENTER);
        p.add(hdr);

        // Imagem bandeira
        JLabel img = new JLabel("", SwingConstants.CENTER);
        img.setPreferredSize(new Dimension(221, 147));
        img.setMaximumSize(new Dimension(Integer.MAX_VALUE, 147));
        img.setOpaque(true);
        img.setBackground(new Color(220, 238, 220));

        if (isJog) imgJog = img; else imgMaq = img;
        p.add(img);

        // Nome do país
        JLabel nome = new JLabel("---", SwingConstants.CENTER);
        nome.setFont(new Font("Arial", Font.BOLD, 13));
        nome.setForeground(VERDE_ESC);
        nome.setAlignmentX(CENTER_ALIGNMENT);
        nome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        nome.setBorder(new EmptyBorder(3, 4, 3, 4));

        if (isJog) lblNomeJog = nome; else lblNomeMaq = nome;
        p.add(nome);

        // Mini-tabela de stats
        JPanel mini = new JPanel(new GridLayout(5, 2, 2, 1));
        mini.setBackground(new Color(235, 250, 235));
        mini.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, VERDE_MED),
                new EmptyBorder(3, 6, 4, 6)));

        for (int i = 0; i < 5; i++) {
            JLabel lAttr = new JLabel(ATTRS_FULL[i] + ":", SwingConstants.LEFT);
            lAttr.setFont(new Font("Arial", Font.PLAIN, 10));
            lAttr.setForeground(new Color(40, 80, 40));

            JLabel lVal = new JLabel("--", SwingConstants.RIGHT);
            lVal.setFont(new Font("Arial", Font.BOLD, 10));
            lVal.setForeground(Color.BLACK);

            if (isJog) miniJog[i] = lVal; else miniMaq[i] = lVal;

            mini.add(lAttr);
            mini.add(lVal);
        }
        p.add(mini);

        return p;
    }

    // ── Tabela de comparação ────────────────────────────────────────────────────
    private JPanel buildCompTable() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(BG);

        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(new Color(228, 245, 228));
        table.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(VERDE_MED, 2),
                new EmptyBorder(8, 10, 8, 10)));

        for (int i = 0; i < 5; i++) {
            if (i > 0) table.add(Box.createVerticalStrut(5));
            table.add(buildCompRow(i));
        }

        outer.add(table, new GridBagConstraints()); // centralizado
        return outer;
    }

    private JPanel buildCompRow(int i) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setBackground(i % 2 == 0 ? Color.WHITE : new Color(244, 252, 244));
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(195, 225, 195)),
                new EmptyBorder(3, 6, 3, 6)));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 1;
        gc.insets = new Insets(0, 3, 0, 3);

        // col 0: attr label esquerda
        JLabel lAttr = new JLabel(ATTRS_SHORT[i], SwingConstants.RIGHT);
        lAttr.setFont(new Font("Arial", Font.BOLD, 13));
        lAttr.setForeground(VERDE_ESC);
        gc.gridx = 0; gc.weightx = 0.30;
        row.add(lAttr, gc);

        // col 1: valor jogador
        valJog[i] = fieldLabel("--");
        gc.gridx = 1; gc.weightx = 0.14;
        row.add(valJog[i], gc);

        // col 2: ícone jogador
        icoJog[i] = iconLabel(' ');
        gc.gridx = 2; gc.weightx = 0.08;
        row.add(icoJog[i], gc);

        // col 3: ícone máquina
        icoMaq[i] = iconLabel(' ');
        gc.gridx = 3; gc.weightx = 0.08;
        row.add(icoMaq[i], gc);

        // col 4: valor máquina
        valMaq[i] = fieldLabel("--");
        gc.gridx = 4; gc.weightx = 0.14;
        row.add(valMaq[i], gc);

        // col 5: attr label direita
        JLabel rAttr = new JLabel(ATTRS_SHORT[i], SwingConstants.LEFT);
        rAttr.setFont(new Font("Arial", Font.BOLD, 13));
        rAttr.setForeground(VERDE_ESC);
        gc.gridx = 5; gc.weightx = 0.30;
        row.add(rAttr, gc);

        return row;
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  SOUTH — placar
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildSouth() {
        JPanel south = new JPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
        south.setBackground(new Color(230, 247, 230));
        south.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, VERDE_MED),
                new EmptyBorder(6, 24, 8, 24)));

        south.add(scoreRow("Pontuação da Rodada:", true));
        south.add(Box.createVerticalStrut(3));
        south.add(scoreRow("Pontuação Geral:",     false));
        south.add(Box.createVerticalStrut(3));

        // linha vencedor
        JPanel lv = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lv.setBackground(new Color(230, 247, 230));

        JLabel ttl = new JLabel("Vencedor:");
        ttl.setFont(new Font("Arial", Font.BOLD, 14));
        ttl.setForeground(VERDE_ESC);
        ttl.setPreferredSize(new Dimension(210, 24));

        lblVencedor = new JLabel("---");
        lblVencedor.setFont(new Font("Arial", Font.BOLD, 15));
        lblVencedor.setForeground(Color.DARK_GRAY);

        lv.add(ttl);
        lv.add(lblVencedor);
        south.add(lv);

        return south;
    }

    private JPanel scoreRow(String titulo, boolean isRodada) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        row.setBackground(new Color(230, 247, 230));

        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setForeground(VERDE_ESC);
        lbl.setPreferredSize(new Dimension(210, 24));

        JLabel voce = new JLabel("Você");
        voce.setFont(new Font("Arial", Font.BOLD, 14));
        voce.setForeground(new Color(0, 0, 180));

        JLabel vJ = numBox("0");
        JLabel vM = numBox("0");

        JLabel maq = new JLabel("Máquina");
        maq.setFont(new Font("Arial", Font.BOLD, 14));
        maq.setForeground(new Color(180, 0, 0));

        if (isRodada) { lblRodJog = vJ; lblRodMaq = vM; }
        else          { lblGerJog = vJ; lblGerMaq = vM; }

        row.add(lbl); row.add(voce); row.add(vJ); row.add(vM); row.add(maq);
        return row;
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  BARRA DE CARTAS
    // ════════════════════════════════════════════════════════════════════════════
    private void atualizarCartas() {
        painelCartas.removeAll();
        botoesMap.clear();
        List<Carta> disp = ctrl.getCartasDisponiveis();

        for (Carta c : ctrl.getTodasCartas()) {
            boolean ok = disp.contains(c);
            Color bg = !ok ? new Color(40, 40, 40)
                    : c.isSuperTrunfo() ? new Color(140, 100, 0)
                    : new Color(0, 75, 25);

            JButton btn = new JButton(
                "<html><center><small>" + c.getCodigo() + "</small><br><b>" + c.getNome() + "</b>"
                + (c.isSuperTrunfo() ? "<br><font color='gold'>★</font>" : "")
                + "</center></html>");
            btn.setPreferredSize(new Dimension(88, 118));
            btn.setFont(new Font("Arial", Font.PLAIN, 9));
            btn.setBackground(bg);
            btn.setForeground(ok ? Color.WHITE : new Color(80, 80, 80));
            btn.setFocusPainted(false);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setEnabled(ok);

            if (imageCache.containsKey(c.getPaisCodigo()))
                btn.setIcon(new ImageIcon(imageCache.get(c.getPaisCodigo())
                        .getScaledInstance(66, 44, Image.SCALE_SMOOTH)));

            if (ok) btn.addActionListener(e -> selecionarCarta(c));

            botoesMap.put(c.getPaisCodigo(), btn);
            painelCartas.add(btn);
        }
        painelCartas.revalidate();
        painelCartas.repaint();
    }

    private void selecionarCarta(Carta c) {
        cartaSelecionada = c;
        mostrarCarta(c, true);
        limparCarta(false);
        limparComparacao();
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  PRÉ-CARREGAMENTO
    // ════════════════════════════════════════════════════════════════════════════
    private void precarregarImagens() {
        for (Carta c : ctrl.getTodasCartas()) {
            String pais = c.getPaisCodigo();
            if (imageCache.containsKey(pais)) continue;
            new SwingWorker<BufferedImage, Void>() {
                @Override protected BufferedImage doInBackground() throws Exception {
                    return ImageIO.read(new URL(BASE_IMG_URL + pais + ".png"));
                }
                @Override protected void done() {
                    try {
                        BufferedImage img = get();
                        if (img == null) return;
                        imageCache.put(pais, img);
                        JButton btn = botoesMap.get(pais);
                        if (btn != null && btn.isEnabled()) {
                            btn.setIcon(new ImageIcon(img.getScaledInstance(66, 44, Image.SCALE_SMOOTH)));
                            btn.revalidate(); btn.repaint();
                        }
                    } catch (Exception ignored) {}
                }
            }.execute();
        }
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  JOGAR / NOVA PARTIDA
    // ════════════════════════════════════════════════════════════════════════════
    private void jogar() {
        if (cartaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma seleção primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!ctrl.getCartasDisponiveis().contains(cartaSelecionada)) {
            JOptionPane.showMessageDialog(this, "Esta seleção já foi usada!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carta maq = ctrl.sortearCartaMaquina(cartaSelecionada);
        if (maq == null) return;

        int[] comp    = compAtributos(cartaSelecionada, maq);
        int resultado = ctrl.jogarRodada(cartaSelecionada, maq);

        mostrarCarta(cartaSelecionada, true);
        mostrarCarta(maq, false);
        preencherComparacao(cartaSelecionada, maq, comp);

        int rodJ = 0, rodM = 0;
        for (int v : comp) { if (v > 0) rodJ++; else if (v < 0) rodM++; }

        lblRodJog.setText(String.valueOf(rodJ));
        lblRodMaq.setText(String.valueOf(rodM));
        lblGerJog.setText(String.valueOf(ctrl.getPontosJogador()));
        lblGerMaq.setText(String.valueOf(ctrl.getPontosMaquina()));

        cartaSelecionada = null;
        atualizarCartas();

        if (ctrl.partidaEncerrada()) encerrarPartida();
    }

    private void novaPartida() {
        ctrl.iniciarPartida();
        cartaSelecionada = null;
        limparCarta(true);
        limparCarta(false);
        limparComparacao();
        lblRodJog.setText("0"); lblRodMaq.setText("0");
        lblGerJog.setText("0"); lblGerMaq.setText("0");
        lblVencedor.setText("---"); lblVencedor.setForeground(Color.DARK_GRAY);
        btnJogar.setEnabled(true);
        atualizarCartas();
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  EXIBIÇÃO / LIMPEZA
    // ════════════════════════════════════════════════════════════════════════════
    private void mostrarCarta(Carta c, boolean jog) {
        JLabel cod   = jog ? lblCodJog  : lblCodMaq;
        JLabel cont  = jog ? lblContJog : lblContMaq;
        JLabel img   = jog ? imgJog     : imgMaq;
        JLabel nome  = jog ? lblNomeJog : lblNomeMaq;
        JLabel[] mini = jog ? miniJog   : miniMaq;

        cod.setText(c.getCodigo());
        cont.setText(c.getContinente());
        nome.setText(c.getNome() + (c.isSuperTrunfo() ? " ★" : ""));
        nome.setForeground(c.isSuperTrunfo() ? new Color(150, 100, 0) : VERDE_ESC);

        int[] v = {c.getTitulos(), c.getGols(), c.getParticipacoes(), c.getPontosFifa(), c.getJogadoresEstrela()};
        for (int i = 0; i < 5; i++) mini[i].setText(String.valueOf(v[i]));

        carregarBandeira(c.getPaisCodigo(), img);
    }

    private void limparCarta(boolean jog) {
        JLabel cod  = jog ? lblCodJog  : lblCodMaq;
        JLabel cont = jog ? lblContJog : lblContMaq;
        JLabel img  = jog ? imgJog     : imgMaq;
        JLabel nome = jog ? lblNomeJog : lblNomeMaq;
        JLabel[] mini = jog ? miniJog  : miniMaq;

        cod.setText("--"); cont.setText("---");
        img.setIcon(null); img.setText("");
        nome.setText("---"); nome.setForeground(VERDE_ESC);
        for (JLabel m : mini) m.setText("--");
    }

    private void carregarBandeira(String pais, JLabel target) {
        if (imageCache.containsKey(pais)) {
            target.setIcon(new ImageIcon(imageCache.get(pais).getScaledInstance(221, 147, Image.SCALE_SMOOTH)));
            target.setText("");
        } else {
            target.setIcon(null);
            target.setText("<html><center><font color='#999999'>...</font></center></html>");
            new SwingWorker<BufferedImage, Void>() {
                @Override protected BufferedImage doInBackground() throws Exception {
                    return ImageIO.read(new URL(BASE_IMG_URL + pais + ".png"));
                }
                @Override protected void done() {
                    try {
                        BufferedImage bi = get();
                        if (bi == null) return;
                        imageCache.put(pais, bi);
                        target.setIcon(new ImageIcon(bi.getScaledInstance(221, 147, Image.SCALE_SMOOTH)));
                        target.setText("");
                        target.revalidate(); target.repaint();
                        JButton btn = botoesMap.get(pais);
                        if (btn != null && btn.isEnabled()) {
                            btn.setIcon(new ImageIcon(bi.getScaledInstance(66, 44, Image.SCALE_SMOOTH)));
                            btn.revalidate(); btn.repaint();
                        }
                    } catch (Exception ignored) {}
                }
            }.execute();
        }
    }

    private void preencherComparacao(Carta j, Carta m, int[] comp) {
        int[] vJ = {j.getTitulos(), j.getGols(), j.getParticipacoes(), j.getPontosFifa(), j.getJogadoresEstrela()};
        int[] vM = {m.getTitulos(), m.getGols(), m.getParticipacoes(), m.getPontosFifa(), m.getJogadoresEstrela()};

        for (int i = 0; i < 5; i++) {
            valJog[i].setText(String.valueOf(vJ[i]));
            valMaq[i].setText(String.valueOf(vM[i]));

            if (comp[i] > 0) {
                setIcon(icoJog[i], "W", WIN);  setIcon(icoMaq[i], "L", LOSE);
                valJog[i].setForeground(WIN);   valMaq[i].setForeground(LOSE);
            } else if (comp[i] < 0) {
                setIcon(icoJog[i], "L", LOSE);  setIcon(icoMaq[i], "W", WIN);
                valJog[i].setForeground(LOSE);   valMaq[i].setForeground(WIN);
            } else {
                setIcon(icoJog[i], "=", TIE);   setIcon(icoMaq[i], "=", TIE);
                valJog[i].setForeground(TIE);    valMaq[i].setForeground(TIE);
            }
        }
    }

    private void limparComparacao() {
        for (int i = 0; i < 5; i++) {
            valJog[i].setText("--"); valMaq[i].setText("--");
            valJog[i].setForeground(Color.DARK_GRAY);
            valMaq[i].setForeground(Color.DARK_GRAY);
            setIcon(icoJog[i], "", new Color(190, 210, 190));
            setIcon(icoMaq[i], "", new Color(190, 210, 190));
        }
    }

    private void setIcon(JLabel l, String txt, Color bg) {
        l.setText(txt);
        l.setBackground(bg);
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  FIM DE PARTIDA
    // ════════════════════════════════════════════════════════════════════════════
    private void encerrarPartida() {
        int pJ = ctrl.getPontosJogador(), pM = ctrl.getPontosMaquina();
        btnJogar.setEnabled(false);

        String resultado;
        Color cor;
        String msg;

        if (pJ > pM) {
            resultado = "VOCÊ"; cor = WIN;
            msg = "Parabéns! Você venceu a Copa! (" + pJ + " × " + pM + ")";
        } else if (pM > pJ) {
            resultado = "MÁQUINA"; cor = LOSE;
            msg = "A máquina levantou a taça desta vez. (" + pJ + " × " + pM + ")";
        } else {
            resultado = "EMPATE"; cor = TIE;
            msg = "Partida encerrada em empate! (" + pJ + " × " + pM + ")";
        }

        lblVencedor.setText(resultado);
        lblVencedor.setForeground(cor);
        JOptionPane.showMessageDialog(this, msg, "Fim de Partida", JOptionPane.INFORMATION_MESSAGE);
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  HELPERS
    // ════════════════════════════════════════════════════════════════════════════
    private int[] compAtributos(Carta j, Carta m) {
        int[] vJ = {j.getTitulos(), j.getGols(), j.getParticipacoes(), j.getPontosFifa(), j.getJogadoresEstrela()};
        int[] vM = {m.getTitulos(), m.getGols(), m.getParticipacoes(), m.getPontosFifa(), m.getJogadoresEstrela()};
        int[] r = new int[5];
        for (int i = 0; i < 5; i++) r[i] = Integer.compare(vJ[i], vM[i]);
        return r;
    }

    private JLabel iconLabel(char sym) {
        JLabel l = new JLabel(sym == ' ' ? "" : String.valueOf(sym), SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 13));
        l.setForeground(Color.WHITE);
        l.setOpaque(true);
        l.setBackground(new Color(190, 210, 190));
        l.setPreferredSize(new Dimension(30, 26));
        return l;
    }

    private JLabel fieldLabel(String txt) {
        JLabel l = new JLabel(txt, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        l.setForeground(Color.DARK_GRAY);
        l.setOpaque(true);
        l.setBackground(Color.WHITE);
        l.setBorder(BorderFactory.createLineBorder(new Color(180, 210, 180)));
        l.setPreferredSize(new Dimension(75, 28));
        return l;
    }

    private JLabel numBox(String val) {
        JLabel l = new JLabel(val, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        l.setOpaque(true);
        l.setBackground(Color.WHITE);
        l.setBorder(BorderFactory.createLineBorder(new Color(160, 195, 160)));
        l.setPreferredSize(new Dimension(40, 28));
        return l;
    }

    private JLabel badge(String txt, Color bg, Color fg) {
        JLabel l = new JLabel(txt, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 11));
        l.setForeground(fg);
        l.setOpaque(true);
        l.setBackground(bg);
        l.setBorder(new EmptyBorder(1, 7, 1, 7));
        return l;
    }

    private JButton makeBtn(String txt, Color bg, Color fg, boolean bold) {
        JButton b = new JButton(txt);
        b.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, 14));
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
