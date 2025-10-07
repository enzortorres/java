package model;

public class PessoaJuridica extends Cliente {
    public String cnpj, razaoSocial;

    public PessoaJuridica(String endereco, String telefone, String razaoSocial, String cnpj) {
        super(endereco, telefone);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String formatarRegistro() {
        String cnpjFormatado = cnpj.substring(0, 2) + "." +
                cnpj.substring(2, 5) + "." +
                cnpj.substring(5, 8) + "/" +
                cnpj.substring(8, 12) + "-" +
                cnpj.substring(12, 14);

        return cnpjFormatado;
    }
}
