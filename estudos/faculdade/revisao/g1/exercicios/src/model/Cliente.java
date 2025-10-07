package model;

public abstract class Cliente {
    public String endereco, telefone;

    public Cliente(String endereco, String telefone) {
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Méthod Concreto
    public String formatarTelefone() {
        String telFormatado = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);

        return telFormatado;
    }

    // Méthod Abstrato
    public abstract String formatarRegistro();
}
