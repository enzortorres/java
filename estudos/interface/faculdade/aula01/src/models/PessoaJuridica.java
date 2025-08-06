package models;

import view.Cliente;

public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String razaoSocial;

    public PessoaJuridica(String endereco, String telefone, String cnpj, String razaoSocial) {
        super(endereco, telefone);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String formatarRegistro() {
        String cnpjFormatado = cnpj.substring(0, 2) + "." +
                cnpj.substring(2,5) + "." +
                cnpj.substring(5,8) + "/" +
                cnpj.substring(8,12) + "-" +
                cnpj.substring(12,14);

        return cnpjFormatado;
    }
}
