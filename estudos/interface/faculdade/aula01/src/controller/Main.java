package controller;

import models.Carro;
import models.Moto;
import models.PessoaFisica;
import models.PessoaJuridica;

public class Main {
    public static void main(String[] args) {
        Carro uno = new Carro("LTH-2020", "Uno", "Preto", 4);
        Moto dominar200 = new Moto("THC-6785", "Dominar NS200", "Branco", 200.5);

        PessoaJuridica pj = new PessoaJuridica("Rua 123", "21 99999-9999", "51.555.016/0001-00", "Empresa LTDA");
        PessoaFisica pf = new PessoaFisica("Rua 456", "21 12345-6789", "123.456.789-10", "Enzo");

        System.out.printf("Placa: %s | Modelo: %s | Cor: %s | Portas: %d", uno.getPlaca(), uno.getModelo(), uno.getCor(), uno.getPortas());
        System.out.printf("Placa: %s | Modelo: %s | Cor: %s | Portas: %.2f", dominar200.getPlaca(), dominar200.getModelo(), dominar200.getCor(), dominar200.getCilindrada());

        System.out.printf("Endereço: %s | Telefone: %s | Nome: %s | CPF: %s", pf.getEndereco(), pf.formatarTelefone(), pf.getNome(), pf.formatarRegistro());
        System.out.printf("Endereço: %s | Telefone: %s | CPNJ: %s | Razão Social: %s",  pj.getEndereco(),  pj.formatarTelefone(),  pj.formatarRegistro(),  pj.getRazaoSocial());


    }
}
