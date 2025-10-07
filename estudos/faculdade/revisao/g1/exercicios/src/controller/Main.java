package controller;

import model.PessoaFisica;
import model.PessoaJuridica;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("CADASTRO CLIENTE");
        System.out.print("Digite o CPF/CNPJ do cliente: ");
        String cadastro = scanner.nextLine();
        System.out.print("Digite o nome/razão social do cliente: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o endereço do cliente: ");
        String endereco = scanner.nextLine();

        System.out.print("Digite 1 para PF ou 2 para PJ: ");
        int tipo = scanner.nextInt();

        switch(tipo) {
            case 1:
                PessoaFisica clientePF = new PessoaFisica(endereco, telefone, descricao, cadastro);

                System.out.println("Dados do cliente PF");
                System.out.println("Nome do cliente: " + clientePF.getNome());
                System.out.println("CPF do cliente: " + clientePF.formatarRegistro());
                System.out.println("Telefone do cliente: " + clientePF.getTelefone());
                System.out.println("Endereço do cliente: " + clientePF.getEndereco());
                break;

            case 2:
                PessoaJuridica clientePJ = new PessoaJuridica(endereco, telefone, descricao, cadastro);

                System.out.println("Dados do cliente PJ");
                System.out.println();
                System.out.println("Razão Social do cliente: " + clientePJ.getRazaoSocial());
                System.out.println("CPNJ do cliente: " + clientePJ.formatarRegistro());
                System.out.println("Telefone do cliente: " + clientePJ.getTelefone());
                System.out.println("Endereço do cliente: " + clientePJ.getEndereco());
                break;
        }
    }
}
