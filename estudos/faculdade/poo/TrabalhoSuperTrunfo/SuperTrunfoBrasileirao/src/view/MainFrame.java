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

    // Imagens dos jogadores via Sofascore
    private static final String BASE_IMG_URL = "https://api.sofascore.com/api/v1/player/";

    // ── Paleta — azul marinho + dourado (cores da CBF) ─────────────────────────
    private static final Color BG         = new Color(245, 247, 255);
    private static final Color AZUL_ESC   = new Color(0,  30, 100);
    private static final Color AZUL_MED   = new Color(0,  70, 180);
    private static final Color AZUL_CARD  = new Color(0,  20,  80);
    private static final Color OURO       = new Color(220, 180, 0);
    private static final Color WIN        = new Color(20, 160, 20);
    private static final Color LOSE       = new Color(200, 20,  20);
    private static final Color TIE        = new Color(160, 130, 0);

    private static final String[] ATTRS_FULL  = {
        "Gols na Carreira", "Títulos", "Gols pela Seleção", "Valor Pico (M€)", "Prêmios"
    };
    private static final String[] ATTRS_SHORT = {
        "Gols", "Títulos", "Gols Seleção", "Valor M€", "Prêmios"
    };

    // ── Estado ──────────────────────────────────────────────────────────────────
    private final GameController ctrl;
    private Carta cartaSelecionada;

    private final Map<String, BufferedImage> imageCache = new HashMap<>();
    private final Map<String, JButton>       botoesMap  = new HashMap<>();

    private JPanel painelCartas;

    // Painel carta — Jogador
    private JLabel lblCodJog, lblPosJog, imgJog, lblNomeJog;
    private final JLabel[] miniJog = new JLabel[5];

    // Painel carta — Máquina
    private JLabel lblCodMaq, lblPosMaq, imgMaq, lblNomeMaq;
    private final JLabel[] miniMaq = new JLabel[5];

    // Tabela de comparação
    private final JLabel[] valJog = new JLabel[5];
    private final JLabel[] valMaq = new JLabel[5];
    private final JLabel[] icoJog = new JLabel[5];
    private final JLabel[] icoMaq = new JLabel[5];

    // Placar
    private JLabel lblRodJog, lblRodMaq, lblGerJog, lblGerMaq, lblVencedor;

    // Botões
    private JButton btnJogar, btnNova;

    // ── Construção ───────────────────────────────────────────────────────────────
    public MainFrame() {
        ctrl = new GameController();
        setTitle("Super Trunfo — Lendas do Brasileirão");
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
    //  NORTH
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildNorth() {
        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(AZUL_ESC);

        painelCartas = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        painelCartas.setBackground(new Color(0, 18, 65));
        JScrollPane scroll = new JScrollPane(painelCartas,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(1200, 140));
        scroll.setBorder(null);

        JPanel bar = new JPanel(new BorderLayout(0, 0));
        bar.setBackground(AZUL_ESC);
        bar.setBorder(new EmptyBorder(5, 16, 6, 16));

        JLabel title = new JLabel("  Super Trunfo  —  Lendas do Brasileirão");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(OURO);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnRow.setBackground(AZUL_ESC);

        btnJogar = makeBtn("  JOGAR  ", OURO, AZUL_ESC, true);
        btnJogar.addActionListener(e -> jogar());

        btnNova = makeBtn("Nova Partida", new Color(140, 50, 0), Color.WHITE, false);
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
    //  CENTER
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildCenter() {
        JPanel center = new JPanel(new BorderLayout(6, 0));
        center.setBackground(BG);
        center.setBorder(new EmptyBorder(6, 8, 4, 8));

        center.add(buildCardPanel(true),  BorderLayout.WEST);
        center.add(buildCompTable(),      BorderLayout.CENTER);
        center.add(buildCardPanel(false), BorderLayout.EAST);

        return center;
    }

    private JPanel buildCardPanel(boolean isJog) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(AZUL_MED, 2));
        p.setPreferredSize(new Dimension(225, 0));

        // Cabeçalho: código + posição
        JPanel hdr = new JPanel(new BorderLayout(3, 0));
        hdr.setBackground(AZUL_CARD);
        hdr.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        hdr.setBorder(new EmptyBorder(3, 5, 3, 5));

        JLabel cod = badge("--",  OURO,     AZUL_CARD);
        JLabel pos = badge("---", AZUL_MED, Color.WHITE);

        if (isJog) { lblCodJog = cod; lblPosJog = pos; }
        else       { lblCodMaq = cod; lblPosMaq = pos; }

        hdr.add(cod, BorderLayout.WEST);
        hdr.add(pos, BorderLayout.CENTER);
        p.add(hdr);

        // Foto do jogador (quadrada)
        JLabel img = new JLabel("", SwingConstants.CENTER);
        img.setPreferredSize(new Dimension(221, 165));
        img.setMaximumSize(new Dimension(Integer.MAX_VALUE, 165));
        img.setOpaque(true);
        img.setBackground(new Color(220, 225, 245));

        if (isJog) imgJog = img; else imgMaq = img;
        p.add(img);

        // Nome
        JLabel nome = new JLabel("---", SwingConstants.CENTER);
        nome.setFont(new Font("Arial", Font.BOLD, 13));
        nome.setForeground(AZUL_ESC);
        nome.setAlignmentX(CENTER_ALIGNMENT);
        nome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        nome.setBorder(new EmptyBorder(3, 4, 3, 4));

        if (isJog) lblNomeJog = nome; else lblNomeMaq = nome;
        p.add(nome);

        // Mini-tabela de stats
        JPanel mini = new JPanel(new GridLayout(5, 2, 2, 1));
        mini.setBackground(new Color(235, 238, 252));
        mini.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, AZUL_MED),
                new EmptyBorder(3, 6, 4, 6)));

        for (int i = 0; i < 5; i++) {
            JLabel lAttr = new JLabel(ATTRS_FULL[i] + ":", SwingConstants.LEFT);
            lAttr.setFont(new Font("Arial", Font.PLAIN, 10));
            lAttr.setForeground(new Color(30, 50, 120));

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

    private JPanel buildCompTable() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(BG);

        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
        table.setBackground(new Color(228, 233, 252));
        table.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AZUL_MED, 2),
                new EmptyBorder(8, 10, 8, 10)));

        for (int i = 0; i < 5; i++) {
            if (i > 0) table.add(Box.createVerticalStrut(5));
            table.add(buildCompRow(i));
        }

        outer.add(table, new GridBagConstraints());
        return outer;
    }

    private JPanel buildCompRow(int i) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setBackground(i % 2 == 0 ? Color.WHITE : new Color(242, 244, 255));
        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(195, 205, 240)),
                new EmptyBorder(3, 6, 3, 6)));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.weighty = 1;
        gc.insets = new Insets(0, 3, 0, 3);

        JLabel lAttr = new JLabel(ATTRS_SHORT[i], SwingConstants.RIGHT);
        lAttr.setFont(new Font("Arial", Font.BOLD, 13));
        lAttr.setForeground(AZUL_ESC);
        gc.gridx = 0; gc.weightx = 0.30;
        row.add(lAttr, gc);

        valJog[i] = fieldLabel("--");
        gc.gridx = 1; gc.weightx = 0.14;
        row.add(valJog[i], gc);

        icoJog[i] = iconLabel();
        gc.gridx = 2; gc.weightx = 0.08;
        row.add(icoJog[i], gc);

        icoMaq[i] = iconLabel();
        gc.gridx = 3; gc.weightx = 0.08;
        row.add(icoMaq[i], gc);

        valMaq[i] = fieldLabel("--");
        gc.gridx = 4; gc.weightx = 0.14;
        row.add(valMaq[i], gc);

        JLabel rAttr = new JLabel(ATTRS_SHORT[i], SwingConstants.LEFT);
        rAttr.setFont(new Font("Arial", Font.BOLD, 13));
        rAttr.setForeground(AZUL_ESC);
        gc.gridx = 5; gc.weightx = 0.30;
        row.add(rAttr, gc);

        return row;
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  SOUTH
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildSouth() {
        JPanel south = new JPanel();
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
        south.setBackground(new Color(230, 234, 252));
        south.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, AZUL_MED),
                new EmptyBorder(6, 24, 8, 24)));

        south.add(scoreRow("Pontuação da Rodada:", true));
        south.add(Box.createVerticalStrut(3));
        south.add(scoreRow("Pontuação Geral:", false));
        south.add(Box.createVerticalStrut(3));

        JPanel lv = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        lv.setBackground(new Color(230, 234, 252));

        JLabel ttl = new JLabel("Vencedor:");
        ttl.setFont(new Font("Arial", Font.BOLD, 14));
        ttl.setForeground(AZUL_ESC);
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
        row.setBackground(new Color(230, 234, 252));

        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setForeground(AZUL_ESC);
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
            Color bg = !ok ? new Color(40, 40, 55)
                    : c.isSuperTrunfo() ? new Color(130, 95, 0)
                    : new Color(0, 30, 100);

            JButton btn = new JButton(
                "<html><center><small>" + c.getCodigo() + "</small><br><b>" + c.getNome() + "</b>"
                + (c.isSuperTrunfo() ? "<br><font color='gold'>★</font>" : "")
                + "</center></html>");
            btn.setPreferredSize(new Dimension(95, 118));
            btn.setFont(new Font("Arial", Font.PLAIN, 9));
            btn.setBackground(bg);
            btn.setForeground(ok ? Color.WHITE : new Color(80, 80, 80));
            btn.setFocusPainted(false);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setEnabled(ok);

            String key = String.valueOf(c.getJogadorId());
            if (imageCache.containsKey(key))
                btn.setIcon(new ImageIcon(imageCache.get(key).getScaledInstance(55, 55, Image.SCALE_SMOOTH)));

            if (ok) btn.addActionListener(e -> selecionarCarta(c));

            botoesMap.put(key, btn);
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
            String key = String.valueOf(c.getJogadorId());
            if (imageCache.containsKey(key)) continue;
            String url = BASE_IMG_URL + c.getJogadorId() + "/image";
            new SwingWorker<BufferedImage, Void>() {
                @Override protected BufferedImage doInBackground() throws Exception {
                    return ImageIO.read(new URL(url));
                }
                @Override protected void done() {
                    try {
                        BufferedImage img = get();
                        if (img == null) return;
                        imageCache.put(key, img);
                        JButton btn = botoesMap.get(key);
                        if (btn != null && btn.isEnabled()) {
                            btn.setIcon(new ImageIcon(img.getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
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
            JOptionPane.showMessageDialog(this, "Selecione um jogador primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!ctrl.getCartasDisponiveis().contains(cartaSelecionada)) {
            JOptionPane.showMessageDialog(this, "Este jogador já foi usado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carta maq = ctrl.sortearCartaMaquina(cartaSelecionada);
        if (maq == null) return;

        int[] comp    = compAtributos(cartaSelecionada, maq);
        ctrl.jogarRodada(cartaSelecionada, maq);

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
        limparCarta(true);  limparCarta(false);
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
        JLabel cod  = jog ? lblCodJog  : lblCodMaq;
        JLabel pos  = jog ? lblPosJog  : lblPosMaq;
        JLabel img  = jog ? imgJog     : imgMaq;
        JLabel nome = jog ? lblNomeJog : lblNomeMaq;
        JLabel[] mini = jog ? miniJog  : miniMaq;

        cod.setText(c.getCodigo());
        pos.setText(c.getPosicao());
        nome.setText(c.getNome() + (c.isSuperTrunfo() ? " ★" : ""));
        nome.setForeground(c.isSuperTrunfo() ? new Color(160, 110, 0) : AZUL_ESC);

        int[] v = {c.getGols(), c.getTitulos(), c.getGolsSelecao(), c.getValorPico(), c.getPremios()};
        for (int i = 0; i < 5; i++) mini[i].setText(String.valueOf(v[i]));

        carregarFoto(c.getJogadorId(), img);
    }

    private void limparCarta(boolean jog) {
        (jog ? lblCodJog  : lblCodMaq).setText("--");
        (jog ? lblPosJog  : lblPosMaq).setText("---");
        JLabel img = jog ? imgJog : imgMaq;
        img.setIcon(null); img.setText("");
        JLabel nome = jog ? lblNomeJog : lblNomeMaq;
        nome.setText("---"); nome.setForeground(AZUL_ESC);
        for (JLabel m : (jog ? miniJog : miniMaq)) m.setText("--");
    }

    private void carregarFoto(int jogadorId, JLabel target) {
        String key = String.valueOf(jogadorId);
        if (imageCache.containsKey(key)) {
            target.setIcon(new ImageIcon(imageCache.get(key).getScaledInstance(165, 165, Image.SCALE_SMOOTH)));
            target.setText("");
        } else {
            target.setIcon(null);
            target.setText("<html><center><font color='#9999cc'>...</font></center></html>");
            String url = BASE_IMG_URL + jogadorId + "/image";
            new SwingWorker<BufferedImage, Void>() {
                @Override protected BufferedImage doInBackground() throws Exception {
                    return ImageIO.read(new URL(url));
                }
                @Override protected void done() {
                    try {
                        BufferedImage bi = get();
                        if (bi == null) return;
                        imageCache.put(key, bi);
                        target.setIcon(new ImageIcon(bi.getScaledInstance(165, 165, Image.SCALE_SMOOTH)));
                        target.setText("");
                        target.revalidate(); target.repaint();
                        JButton btn = botoesMap.get(key);
                        if (btn != null && btn.isEnabled()) {
                            btn.setIcon(new ImageIcon(bi.getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
                            btn.revalidate(); btn.repaint();
                        }
                    } catch (Exception ignored) {}
                }
            }.execute();
        }
    }

    private void preencherComparacao(Carta j, Carta m, int[] comp) {
        int[] vJ = {j.getGols(), j.getTitulos(), j.getGolsSelecao(), j.getValorPico(), j.getPremios()};
        int[] vM = {m.getGols(), m.getTitulos(), m.getGolsSelecao(), m.getValorPico(), m.getPremios()};

        for (int i = 0; i < 5; i++) {
            valJog[i].setText(String.valueOf(vJ[i]));
            valMaq[i].setText(String.valueOf(vM[i]));

            if (comp[i] > 0) {
                setIcon(icoJog[i], "✔", WIN);  setIcon(icoMaq[i], "✘", LOSE);
                valJog[i].setForeground(WIN);   valMaq[i].setForeground(LOSE);
            } else if (comp[i] < 0) {
                setIcon(icoJog[i], "✘", LOSE);  setIcon(icoMaq[i], "✔", WIN);
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
            setIcon(icoJog[i], "", new Color(190, 200, 230));
            setIcon(icoMaq[i], "", new Color(190, 200, 230));
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

        String resultado; Color cor; String msg;
        if (pJ > pM) {
            resultado = "VOCÊ";    cor = WIN;
            msg = "Parabéns! Você é o maior do Brasileirão! (" + pJ + " × " + pM + ")";
        } else if (pM > pJ) {
            resultado = "MÁQUINA"; cor = LOSE;
            msg = "A máquina ganhou desta vez. (" + pJ + " × " + pM + ")";
        } else {
            resultado = "EMPATE";  cor = TIE;
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
        int[] vJ = {j.getGols(), j.getTitulos(), j.getGolsSelecao(), j.getValorPico(), j.getPremios()};
        int[] vM = {m.getGols(), m.getTitulos(), m.getGolsSelecao(), m.getValorPico(), m.getPremios()};
        int[] r = new int[5];
        for (int i = 0; i < 5; i++) r[i] = Integer.compare(vJ[i], vM[i]);
        return r;
    }

    private JLabel iconLabel() {
        JLabel l = new JLabel("", SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 13));
        l.setForeground(Color.WHITE);
        l.setOpaque(true);
        l.setBackground(new Color(190, 200, 230));
        l.setPreferredSize(new Dimension(30, 26));
        return l;
    }

    private JLabel fieldLabel(String txt) {
        JLabel l = new JLabel(txt, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        l.setForeground(Color.DARK_GRAY);
        l.setOpaque(true);
        l.setBackground(Color.WHITE);
        l.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 230)));
        l.setPreferredSize(new Dimension(75, 28));
        return l;
    }

    private JLabel numBox(String val) {
        JLabel l = new JLabel(val, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 16));
        l.setOpaque(true);
        l.setBackground(Color.WHITE);
        l.setBorder(BorderFactory.createLineBorder(new Color(160, 175, 220)));
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
