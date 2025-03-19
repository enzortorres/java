package dominio;

public class ContaBancaria {
    private String titular = "";
    private double saldo;

    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;

    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.printf("R$%.2f depositado com sucesso!\n", valor);
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.printf("Saque de R$%.2f realizado com sucesso!\n", valor);
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public void exibirSaldo() {
        System.out.printf("Saldo atual: R$%.2f\n", saldo);
    }


    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
