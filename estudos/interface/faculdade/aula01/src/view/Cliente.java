package view;

public abstract class Cliente {
    protected String endereco;
    protected String telefone;

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

    public Cliente(String endereco, String telefone) {
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
