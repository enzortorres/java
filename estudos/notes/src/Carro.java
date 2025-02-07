public class Carro {

    String modelo;

    public static void main(String[] args) {
        System.out.println("teste");
    }

    public Carro(String modelo) {
        this.modelo = modelo;
    }

    public void acelerar() {
        System.out.println(this.modelo + " acelerando!");
    }
}
