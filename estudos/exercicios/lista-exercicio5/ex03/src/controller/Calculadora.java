package controller;
import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Digite um número: ");
            double number1 = sc.nextDouble();
            System.out.print("Digite outro número: ");
            double number2 = sc.nextDouble();

            System.out.print("Digite um operador (+, -, *, /): ");
            String oper = sc.next();

            switch (oper) {
                case "+":
                    System.out.printf("%.2f + %.2f = %.2f", number1, number2, number1 + number2);
                    break;
                case "-":
                    System.out.printf("%.2f - %.2f = %.2f", number1, number2, number1 - number2);
                    break;
                case "*":
                    System.out.printf("%.2f * %.2f = %.2f", number1, number2, number1 * number2);
                    break;
                case "/":
                    if (number1 != 0 && number2 != 0) {
                        System.out.printf("%.2f / %.2f = %.2f", number1, number2, number1 / number2);
                    } else {
                        System.out.println("Não pode realizar divisão por 0.");
                    }
                    break;
                default:
                    System.out.println("Digite um operador válido.");
            }
        } catch (Exception ex){
            System.out.println("Digite um valor válido. " + ex);
        } finally {
            System.out.println("Sistema finalizado...");
        }

        sc.close();
    }
}
