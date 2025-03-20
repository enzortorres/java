package dominio;

public class CaixaEletronico {
    private double saldo;

    public CaixaEletronico(double saldo) {
        this.saldo = saldo;
    }

    public boolean sacar(double valor) {
        if (valor % 10 == 0) {
            if(valor > saldo) {
                System.out.println("\nSaldo insuficiente!\n");
                return false;
            } else {
                saldo -= valor;
                System.out.println("\nSaque realizado com sucesso\n");
                return true;
            }
        } else {
            System.out.println("\nSó temos cédulas de 10. Somente valores múltiplos de 10.\n");
            return false;
        }
    }

    public void exibirSaldo() {
        System.out.println("Saldo atual: " + saldo);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
