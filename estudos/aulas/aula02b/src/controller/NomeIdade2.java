package controller;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Scanner;

public class NomeIdade2 {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.00");
        Scanner sc = new Scanner(System.in);
        double desconto;

        System.out.print("Digite o código do produto: ");
        int cod_produto = sc.nextInt();
        System.out.print("Digite a quantidade do produto: ");
        int qtd_produto = sc.nextInt();
        System.out.print("Digite a preço unitário do produto: ");
        double preco = sc.nextDouble();
        System.out.print("Escolha a forma de pagamento:\n1 - Dinheiro\n2 - Pix\n3 - Débito\n4 - Crédito\n>>> ");
        int forma_pagamento = sc.nextInt();

        switch (forma_pagamento) {
            case 1:
                desconto = 0.15;
                break;
            case 2:
                desconto = 0.12;
                break;
            case 3:
                desconto = 0.10;
                break;
            case 4:
                desconto = 0.05;
                break;
            default:
                System.out.println("Forma de pagamento inválida!\n");
                desconto = 0;
        }

        double valorTotal = preco * qtd_produto;
        double valorComDesconto = valorTotal - (valorTotal * desconto);

        System.out.println("Valor total: R$" + df.format(valorTotal));
        System.out.println("Valor com desconto: R$" + df.format(valorComDesconto));

    }
}
