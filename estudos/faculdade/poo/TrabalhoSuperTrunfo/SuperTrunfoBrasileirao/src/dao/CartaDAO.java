package dao;

import model.Carta;
import util.Conexao;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {

    public List<Carta> listarTodas() {
        List<Carta> cartas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(Conexao.getCaminhoCsv()), StandardCharsets.UTF_8))) {

            br.readLine(); // pula cabeçalho

            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] c = linha.split(",", -1);

                int     id          = Integer.parseInt(c[0].trim());
                String  codigo      = c[1].trim();
                String  nome        = c[2].trim();
                String  posicao     = c[3].trim();
                int     gols        = Integer.parseInt(c[4].trim());
                int     titulos     = Integer.parseInt(c[5].trim());
                int     golsSel     = Integer.parseInt(c[6].trim());
                int     valorPico   = Integer.parseInt(c[7].trim());
                int     premios     = Integer.parseInt(c[8].trim());
                boolean superTrunfo = c[9].trim().equalsIgnoreCase("true");
                int     jogadorId   = Integer.parseInt(c[10].trim());

                cartas.add(new Carta(id, codigo, nome, posicao,
                        gols, titulos, golsSel, valorPico, premios, superTrunfo, jogadorId));
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o CSV: " + e.getMessage(), e);
        }

        return cartas;
    }
}
