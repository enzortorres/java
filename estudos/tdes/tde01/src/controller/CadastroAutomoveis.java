package controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

import models.Automovel;

public class CadastroAutomoveis {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Automovel> lista = new ArrayList<>();
    static String nomeArquivo = "automoveis.txt";

    public static void main(String[] args) {
        carregar();

        int op;
        do {
            System.out.println("\n1 - Incluir automóvel");
            System.out.println("2 - Remover automóvel");
            System.out.println("3 - Alterar dados de automóvel");
            System.out.println("4 - Consultar automóvel por placa");
            System.out.println("5 - Listar automóveis (ordenado)");
            System.out.println("6 - Salvar e sair");
            System.out.print("Opção: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    incluir();
                    break;
                case 2:
                    remover();
                    break;
                case 3:
                    alterar();
                    break;
                case 4:
                    consultar();
                    break;
                case 5:
                    listarOrdenado();
                    break;
                case 6:
                    salvar();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (op != 6);
    }

    static void incluir() {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        if (buscar(placa) != null) {
            System.out.println("Placa já cadastrada.");
            return;
        }
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Marca: ");
        String marca = sc.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(sc.nextLine());
        System.out.print("Valor: ");
        double valor = Double.parseDouble(sc.nextLine());

        lista.add(new Automovel(placa, modelo, marca, ano, valor));
        System.out.println("Automóvel incluído.");
    }

    static void remover() {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        Automovel a = buscar(placa);
        if (a != null) {
            lista.remove(a);
            System.out.println("Automóvel removido.");
        } else {
            System.out.println("Não encontrado.");
        }
    }

    static void alterar() {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        Automovel a = buscar(placa);
        if (a != null) {
            System.out.print("Novo modelo: ");
            a.modelo = sc.nextLine();
            System.out.print("Nova marca: ");
            a.marca = sc.nextLine();
            System.out.print("Novo ano: ");
            a.ano = Integer.parseInt(sc.nextLine());
            System.out.print("Novo valor: ");
            a.valor = Double.parseDouble(sc.nextLine());
            System.out.println("Dados atualizados.");
        } else {
            System.out.println("Não encontrado.");
        }
    }

    static void consultar() {
        System.out.print("Placa: ");
        String placa = sc.nextLine();
        Automovel a = buscar(placa);
        if (a != null) {
            a.imprimir();
        } else {
            System.out.println("Não encontrado.");
        }
    }

    static void listarOrdenado() {
        System.out.print("Ordenar por (placa/modelo/marca): ");
        String crit = sc.nextLine();

        ArrayList<Automovel> copia = new ArrayList<>(lista);

        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = i + 1; j < copia.size(); j++) {
                boolean troca = false;

                if (crit.equals("placa")) {
                    if (copia.get(i).placa.compareToIgnoreCase(copia.get(j).placa) > 0) troca = true;
                } else if (crit.equals("modelo")) {
                    if (copia.get(i).modelo.compareToIgnoreCase(copia.get(j).modelo) > 0) troca = true;
                } else if (crit.equals("marca")) {
                    if (copia.get(i).marca.compareToIgnoreCase(copia.get(j).marca) > 0) troca = true;
                } else {
                    System.out.println("Critério inválido.");
                    return;
                }

                if (troca) {
                    Automovel temp = copia.get(i);
                    copia.set(i, copia.get(j));
                    copia.set(j, temp);
                }
            }
        }

        for (Automovel a : copia) {
            a.imprimir();
        }
    }

    static Automovel buscar(String placa) {
        for (Automovel a : lista)
            if (a.placa.equalsIgnoreCase(placa))
                return a;
        return null;
    }

    static void carregar() {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(",");
                lista.add(new Automovel(p[0], p[1], p[2], Integer.parseInt(p[3]), Double.parseDouble(p[4])));
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado. Novo será criado ao salvar.");
        }
    }

    static void salvar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Automovel a : lista)
                bw.write(a.toCSV() + "\n");
            System.out.println("Dados salvos.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar.");
        }
    }
}
