package view;

import controller.GameController;
import model.Carta;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

public class MainFrame extends JFrame {

    private static final String WIKI_API = "https://en.wikipedia.org/api/rest_v1/page/summary/";

    // ── Paleta (azul marinho + dourado) ─────────────────────────────────────────
    private static final Color BG        = new Color(245, 247, 255);
    private static final Color AZUL_ESC  = new Color(0,  30, 100);
    private static final Color AZUL_MED  = new Color(0,  70, 180);
    private static final Color AZUL_CARD = new Color(0,  20,  80);
    private static final Color OURO      = new Color(220, 180,   0);
    private static final Color WIN       = new Color( 20, 160,  20);
    private static final Color LOSE      = new Color(200,  20,  20);
    private static final Color TIE       = new Color(160, 130,   0);

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

    private JPanel painelCartas;   // sidebar esquerda

    // Painel carta — Jogador (esq)
    private JLabel lblCodJog, lblPosJog, imgJog, lblNomeJog;
    private final JLabel[] miniJog = new JLabel[5];

    // Painel carta — Máquina (dir)
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
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(BG);

        add(buildNorth(),        BorderLayout.NORTH);
        add(buildSidebarPanel(), BorderLayout.WEST);
        add(buildCenter(),  BorderLayout.CENTER);
        add(buildSouth(),   BorderLayout.SOUTH);

        atualizarCartas();
        precarregarImagens();
        setVisible(true);
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  NORTH — barra compacta: título + Nova Partida
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildNorth() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(AZUL_ESC);
        bar.setBorder(new EmptyBorder(7, 18, 7, 18));

        JLabel title = new JLabel("Super Trunfo  —  Lendas do Brasileirão");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(OURO);

        btnNova = new JButton("Nova Partida");
        btnNova.setFont(new Font("Arial", Font.PLAIN, 13));
        btnNova.setBackground(new Color(160, 80, 0));
        btnNova.setForeground(Color.WHITE);
        btnNova.setFocusPainted(false);
        btnNova.setBorderPainted(false);
        btnNova.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNova.addActionListener(e -> novaPartida());

        bar.add(title,   BorderLayout.WEST);
        bar.add(btnNova, BorderLayout.EAST);
        return bar;
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  WEST — sidebar com todas as cartas em grid (2 colunas, scroll vertical)
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildSidebarPanel() {
        painelCartas = new JPanel(new GridLayout(0, 2, 4, 4));
        painelCartas.setBackground(new Color(0, 18, 65));
        painelCartas.setBorder(new EmptyBorder(6, 6, 6, 6));

        JScrollPane scroll = new JScrollPane(painelCartas,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getViewport().setBackground(new Color(0, 18, 65));
        scroll.setBorder(null);

        JLabel hdr = new JLabel("  Escolha seu jogador", SwingConstants.LEFT);
        hdr.setFont(new Font("Arial", Font.BOLD, 11));
        hdr.setForeground(OURO);
        hdr.setOpaque(true);
        hdr.setBackground(AZUL_CARD);
        hdr.setBorder(new EmptyBorder(5, 6, 5, 6));

        JPanel side = new JPanel(new BorderLayout());
        side.setPreferredSize(new Dimension(216, 0));
        side.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, AZUL_MED));
        side.add(hdr,    BorderLayout.NORTH);
        side.add(scroll, BorderLayout.CENTER);
        return side;
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  CENTER — topo: painéis VOCÊ | JOGAR | MÁQUINA
    //           baixo: tabela de comparação
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildCenter() {
        JPanel center = new JPanel(new BorderLayout(0, 6));
        center.setBackground(BG);
        center.setBorder(new EmptyBorder(6, 8, 4, 8));

        // Linha superior: carta do jogador | botão JOGAR | carta da máquina
        JPanel topRow = new JPanel(new BorderLayout(8, 0));
        topRow.setBackground(BG);
        topRow.add(buildCardPanel(true),  BorderLayout.WEST);
        topRow.add(buildJogarArea(),      BorderLayout.CENTER);
        topRow.add(buildCardPanel(false), BorderLayout.EAST);

        center.add(topRow,           BorderLayout.NORTH);
        center.add(buildCompTable(), BorderLayout.CENTER);
        return center;
    }

    /** Painel central com botão JOGAR em destaque */
    private JPanel buildJogarArea() {
        JPanel area = new JPanel(new GridBagLayout());
        area.setBackground(BG);

        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setBackground(BG);

        // Instrução
        JLabel instrucao = new JLabel("Selecione uma carta e clique", SwingConstants.CENTER);
        instrucao.setFont(new Font("Arial", Font.ITALIC, 11));
        instrucao.setForeground(new Color(120, 130, 170));
        instrucao.setAlignmentX(CENTER_ALIGNMENT);

        inner.add(Box.createVerticalStrut(20));
        inner.add(instrucao);
        inner.add(Box.createVerticalStrut(16));

        // Botão JOGAR grande e centralizado
        btnJogar = new JButton("JOGAR");
        btnJogar.setFont(new Font("Arial", Font.BOLD, 18));
        btnJogar.setBackground(OURO);
        btnJogar.setForeground(AZUL_ESC);
        btnJogar.setFocusPainted(false);
        btnJogar.setPreferredSize(new Dimension(160, 52));
        btnJogar.setMaximumSize(new Dimension(160, 52));
        btnJogar.setBorderPainted(false);
        btnJogar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnJogar.setAlignmentX(CENTER_ALIGNMENT);
        btnJogar.addActionListener(e -> jogar());
        inner.add(btnJogar);

        area.add(inner);
        return area;
    }

    // ── Painel de carta (jogador ou máquina) ─────────────────────────────────────
    private JPanel buildCardPanel(boolean isJog) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(AZUL_MED, 2));
        p.setPreferredSize(new Dimension(235, 310));

        // Cabeçalho
        JPanel hdr = new JPanel(new BorderLayout(3, 0));
        hdr.setBackground(AZUL_CARD);
        hdr.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        hdr.setBorder(new EmptyBorder(3, 6, 3, 6));

        JLabel cod = badge("--",  OURO,    AZUL_CARD);
        JLabel pos = badge("---", AZUL_MED, Color.WHITE);

        if (isJog) { lblCodJog = cod; lblPosJog = pos; }
        else       { lblCodMaq = cod; lblPosMaq = pos; }

        hdr.add(cod, BorderLayout.WEST);
        hdr.add(pos, BorderLayout.CENTER);
        p.add(hdr);

        // Foto
        JLabel img = new JLabel("", SwingConstants.CENTER);
        img.setPreferredSize(new Dimension(231, 148));
        img.setMaximumSize(new Dimension(Integer.MAX_VALUE, 148));
        img.setOpaque(true);
        img.setBackground(new Color(220, 225, 245));

        if (isJog) imgJog = img; else imgMaq = img;
        p.add(img);

        // Nome
        JLabel nome = new JLabel("---", SwingConstants.CENTER);
        nome.setFont(new Font("Arial", Font.BOLD, 13));
        nome.setForeground(AZUL_ESC);
        nome.setAlignmentX(CENTER_ALIGNMENT);
        nome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
        nome.setBorder(new EmptyBorder(3, 4, 3, 4));

        if (isJog) lblNomeJog = nome; else lblNomeMaq = nome;
        p.add(nome);

        // Mini-stats
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
            mini.add(lAttr); mini.add(lVal);
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
        table.setBackground(new Color(228, 233, 252));
        table.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AZUL_MED, 2),
                new EmptyBorder(8, 10, 8, 10)));

        // Cabeçalho da tabela
        JPanel cabec = new JPanel(new GridLayout(1, 6, 4, 0));
        cabec.setBackground(AZUL_CARD);
        cabec.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        cabec.setBorder(new EmptyBorder(2, 6, 2, 6));
        for (String s : new String[]{"Você", "Valor", "W/L", "W/L", "Valor", "Máquina"}) {
            JLabel l = new JLabel(s, SwingConstants.CENTER);
            l.setFont(new Font("Arial", Font.BOLD, 10));
            l.setForeground(new Color(180, 200, 255));
            cabec.add(l);
        }
        table.add(cabec);
        table.add(Box.createVerticalStrut(5));

        for (int i = 0; i < 5; i++) {
            if (i > 0) table.add(Box.createVerticalStrut(4));
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

        // nome atributo esquerda
        JLabel lAttr = new JLabel(ATTRS_SHORT[i], SwingConstants.RIGHT);
        lAttr.setFont(new Font("Arial", Font.BOLD, 12));
        lAttr.setForeground(AZUL_ESC);
        gc.gridx = 0; gc.weightx = 0.28;
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

        // nome atributo direita
        JLabel rAttr = new JLabel(ATTRS_SHORT[i], SwingConstants.LEFT);
        rAttr.setFont(new Font("Arial", Font.BOLD, 12));
        rAttr.setForeground(AZUL_ESC);
        gc.gridx = 5; gc.weightx = 0.28;
        row.add(rAttr, gc);

        return row;
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  SOUTH — barra de placar horizontal
    // ════════════════════════════════════════════════════════════════════════════
    private JPanel buildSouth() {
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 6));
        south.setBackground(AZUL_ESC);
        south.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, AZUL_MED));

        south.add(scoreSegment("Pontuação da Rodada:", true));
        south.add(makeSeparador());
        south.add(scoreSegment("Pontuação Geral:", false));
        south.add(makeSeparador());

        JLabel vLabel = new JLabel("Vencedor:");
        vLabel.setFont(new Font("Arial", Font.BOLD, 13));
        vLabel.setForeground(new Color(180, 200, 255));

        lblVencedor = new JLabel("---");
        lblVencedor.setFont(new Font("Arial", Font.BOLD, 15));
        lblVencedor.setForeground(OURO);

        south.add(vLabel);
        south.add(lblVencedor);
        return south;
    }

    private JPanel scoreSegment(String titulo, boolean isRodada) {
        JPanel seg = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        seg.setBackground(AZUL_ESC);

        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setForeground(new Color(180, 200, 255));

        JLabel lVoce = new JLabel("Você");
        lVoce.setFont(new Font("Arial", Font.BOLD, 13));
        lVoce.setForeground(new Color(100, 180, 255));

        JLabel vJ = numBox("0");
        JLabel sep = new JLabel("×");
        sep.setFont(new Font("Arial", Font.BOLD, 14));
        sep.setForeground(new Color(120, 140, 200));
        JLabel vM = numBox("0");

        JLabel lCpu = new JLabel("CPU");
        lCpu.setFont(new Font("Arial", Font.BOLD, 13));
        lCpu.setForeground(new Color(255, 120, 100));

        if (isRodada) { lblRodJog = vJ; lblRodMaq = vM; }
        else          { lblGerJog = vJ; lblGerMaq = vM; }

        seg.add(lbl); seg.add(lVoce); seg.add(vJ); seg.add(sep); seg.add(vM); seg.add(lCpu);
        return seg;
    }

    private JSeparator makeSeparador() {
        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setPreferredSize(new Dimension(1, 24));
        sep.setForeground(new Color(60, 80, 140));
        return sep;
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  SIDEBAR DE CARTAS
    // ════════════════════════════════════════════════════════════════════════════
    private void atualizarCartas() {
        painelCartas.removeAll();
        botoesMap.clear();
        List<Carta> disp = ctrl.getCartasDisponiveis();

        for (Carta c : ctrl.getTodasCartas()) {
            boolean ok = disp.contains(c);
            Color bg = !ok      ? new Color(30, 32, 50)
                     : c.isSuperTrunfo() ? new Color(100, 75, 0)
                     : AZUL_CARD;

            JButton btn = new JButton(
                "<html><center><tiny>" + c.getCodigo() + "</tiny><br><b>" + c.getNome() + "</b>"
                + (c.isSuperTrunfo() ? "<br><font color='gold'>★</font>" : "")
                + "</center></html>");
            btn.setPreferredSize(new Dimension(96, 112));
            btn.setFont(new Font("Arial", Font.PLAIN, 8));
            btn.setBackground(bg);
            btn.setForeground(ok ? Color.WHITE : new Color(70, 75, 100));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setEnabled(ok);

            String key = c.getWikiPage();
            if (imageCache.containsKey(key))
                btn.setIcon(new ImageIcon(imageCache.get(key).getScaledInstance(52, 52, Image.SCALE_SMOOTH)));

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
        List<Carta> cartas = new ArrayList<>(ctrl.getTodasCartas());
        new SwingWorker<Void, Object[]>() {
            @Override
            protected Void doInBackground() {
                for (Carta c : cartas) {
                    String key = c.getWikiPage();
                    if (imageCache.containsKey(key)) continue;
                    try {
                        BufferedImage img = fetchPlayerImage(key);
                        if (img != null) publish(new Object[]{key, img});
                    } catch (Exception ignored) {}
                    try { Thread.sleep(150); } catch (InterruptedException e) { break; }
                }
                return null;
            }

            @Override
            protected void process(List<Object[]> chunks) {
                for (Object[] item : chunks) {
                    String key        = (String)       item[0];
                    BufferedImage img = (BufferedImage) item[1];
                    imageCache.put(key, img);
                    JButton btn = botoesMap.get(key);
                    if (btn != null && btn.isEnabled()) {
                        btn.setIcon(new ImageIcon(img.getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
                        btn.revalidate(); btn.repaint();
                    }
                }
                painelCartas.revalidate();
                painelCartas.repaint();
            }
        }.execute();
    }

    // ════════════════════════════════════════════════════════════════════════════
    //  JOGAR / NOVA PARTIDA
    // ════════════════════════════════════════════════════════════════════════════
    private void jogar() {
        if (cartaSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione um jogador na lista à esquerda!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!ctrl.getCartasDisponiveis().contains(cartaSelecionada)) {
            JOptionPane.showMessageDialog(this, "Este jogador já foi usado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Carta maq  = ctrl.sortearCartaMaquina(cartaSelecionada);
        if (maq == null) return;

        int[] comp = compAtributos(cartaSelecionada, maq);
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
        limparCarta(true); limparCarta(false);
        limparComparacao();
        lblRodJog.setText("0"); lblRodMaq.setText("0");
        lblGerJog.setText("0"); lblGerMaq.setText("0");
        lblVencedor.setText("---"); lblVencedor.setForeground(OURO);
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

        carregarFoto(c.getWikiPage(), img);
    }

    private void limparCarta(boolean jog) {
        (jog ? lblCodJog : lblCodMaq).setText("--");
        (jog ? lblPosJog : lblPosMaq).setText("---");
        JLabel img = jog ? imgJog : imgMaq;
        img.setIcon(null); img.setText("");
        JLabel nome = jog ? lblNomeJog : lblNomeMaq;
        nome.setText("---"); nome.setForeground(AZUL_ESC);
        for (JLabel m : (jog ? miniJog : miniMaq)) m.setText("--");
    }

    private void carregarFoto(String wikiPage, JLabel target) {
        String key = wikiPage;
        if (imageCache.containsKey(key)) {
            target.setIcon(new ImageIcon(imageCache.get(key).getScaledInstance(231, 148, Image.SCALE_SMOOTH)));
            target.setText("");
        } else {
            target.setIcon(null);
            target.setText("<html><center><font color='#8888bb'>...</font></center></html>");
            new SwingWorker<BufferedImage, Void>() {
                @Override protected BufferedImage doInBackground() throws Exception {
                    return fetchPlayerImage(key);
                }
                @Override protected void done() {
                    try {
                        BufferedImage bi = get();
                        if (bi == null) return;
                        imageCache.put(key, bi);
                        target.setIcon(new ImageIcon(bi.getScaledInstance(231, 148, Image.SCALE_SMOOTH)));
                        target.setText("");
                        target.revalidate(); target.repaint();
                        JButton btn = botoesMap.get(key);
                        if (btn != null && btn.isEnabled()) {
                            btn.setIcon(new ImageIcon(bi.getScaledInstance(52, 52, Image.SCALE_SMOOTH)));
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
            setIcon(icoJog[i], "", new Color(190, 200, 230));
            setIcon(icoMaq[i], "", new Color(190, 200, 230));
        }
    }

    private void setIcon(JLabel l, String txt, Color bg) {
        l.setText(txt); l.setBackground(bg);
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

    private BufferedImage fetchPlayerImage(String wikiPage) throws Exception {
        String encoded = URLEncoder.encode(wikiPage, StandardCharsets.UTF_8).replace("+", "_");
        HttpURLConnection api = (HttpURLConnection) new URL(WIKI_API + encoded).openConnection();
        api.setRequestProperty("User-Agent", "SuperTrunfoBrasileirao/1.0 (educational project)");
        api.setConnectTimeout(6000);
        api.setReadTimeout(10000);

        String json;
        try (java.io.InputStream is = api.getInputStream()) {
            json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }

        int tIdx = json.indexOf("\"thumbnail\"");
        if (tIdx == -1) return null;
        String block = json.substring(tIdx);
        int sIdx = block.indexOf("\"source\":\"");
        if (sIdx == -1) return null;
        String imgUrl = block.substring(sIdx + 10, block.indexOf("\"", sIdx + 10)).replace("\\/", "/");

        HttpURLConnection img = (HttpURLConnection) new URL(imgUrl).openConnection();
        img.setRequestProperty("User-Agent", "Mozilla/5.0");
        img.setConnectTimeout(6000);
        img.setReadTimeout(10000);
        return ImageIO.read(img.getInputStream());
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
        l.setFont(new Font("Arial", Font.BOLD, 15));
        l.setForeground(Color.WHITE);
        l.setOpaque(true);
        l.setBackground(new Color(30, 60, 150));
        l.setBorder(BorderFactory.createLineBorder(new Color(60, 100, 200)));
        l.setPreferredSize(new Dimension(36, 26));
        return l;
    }

    private JLabel badge(String txt, Color bg, Color fg) {
        JLabel l = new JLabel(txt, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 11));
        l.setForeground(fg);
        l.setOpaque(true);
        l.setBackground(bg);
        l.setBorder(new EmptyBorder(2, 7, 2, 7));
        return l;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
