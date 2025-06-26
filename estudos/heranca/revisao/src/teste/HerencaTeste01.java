package teste;

import model.Endereco;
import model.Funcionario;
import model.Pessoa;

public class HerencaTeste01 {
    public static void main(String[] args) {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua 3");
        endereco.setCep("012345-678");

        Pessoa pessoa = new Pessoa("Jorgin");
        pessoa.setCpf("123.456.789-34");
        pessoa.setEndereco(endereco);

        pessoa.imprime();

        Funcionario funcionario = new Funcionario("Cleiton");
        funcionario.setCpf("312.654.987.01");
        funcionario.setEndereco(endereco);
        funcionario.setSalario(20000);

        System.out.println("--------------------");

        funcionario.imprime();
    }
}
