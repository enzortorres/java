import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite seu nome: ");
        var nome = sc.nextLine();
        System.out.print("Digite a sua idade: ");
        var idade = sc.nextInt();

        System.out.println("Cidadão: " + nome);
        System.out.println("Idade: " + idade);
        if (idade >= 16 && idade <= 18) {
            System.out.println("Situação: Voto opcional!");
        } else if (idade >= 18 && idade <= 70) {
            System.out.println("Situação: Voto obrigatório!");
        } else {
            System.out.println("Situação: Não vota!");
        }
    }
}