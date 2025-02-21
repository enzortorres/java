////! EXERCÍCIO 1
//package controller;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//
//        System.out.print("Digite um número: ");
//        int number1 = sc.nextInt();
//
//        System.out.print("Digite outro número: ");
//        int number2 = sc.nextInt();
//
//        System.out.printf("%d + %d = %d\n", number1, number2, number1 + number2);
//
//        sc.close();
//    }
//}



////! EXERCÍCIO 2
//package controller;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        double PI = 3.14159;
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Digite o raio do círculo: ");
//        double raio = sc.nextDouble();
//
//        double area = PI * Math.pow(raio, 2);
//        System.out.printf("A área do círculo é %.2f", area);
//
//        sc.close();
//    }
//}



////! EXERCÍCIO 3
//package controller;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Digite uma temperatura em Celsius: ");
//        double celsius = sc.nextDouble();
//
//        double fahrenheit = (celsius * 9/5) + 32;
//
//        System.out.printf("%.2fºC em fahrenheit: %.2fºF", celsius, fahrenheit);
//
//        sc.close();
//    }
//}


////! EXERCÍCIO 4
//package controller;
//import java.util.Scanner;
//import java.util.ArrayList;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        ArrayList<Double> notas = new ArrayList<>();
//        double soma = 0;
//
//        for (int i = 0; i < 3; i++) {
//            System.out.printf("Digite o %d numero: ", i+1);
//            notas.add(sc.nextDouble());
//        }
//        for (Double nota : notas) {
//            soma += nota;
//        }
//
//        double media = soma / 3;
//        System.out.printf("A media das notas é %.2f", media);
//    }
//}



////! EXERCÍCIO 5
//package controller;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Digite a sua idade: ");
//        int idade = sc.nextInt();
//
//        int dias = idade * 365;
//
//        System.out.printf("Você tem %d dias de vida!", dias);
//        sc.close();
//    }
//}


////! EXERCÍCIO 6
//package controller;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//
//        System.out.print("Digite o seu salário: ");
//        double salario = sc.nextDouble();
//
//        System.out.print("Digite o desconto do INSS: ");
//        double descontoINSS = sc.nextDouble();
//
//        System.out.printf("Seu salário de R$%.2f com um desconto do INSS de R$%.2f ficou em R$%.2f", salario, descontoINSS, salario - descontoINSS);
//    }
//}



////! EXERCÍCIO 7
//package controller;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Digite a base do retângulo: ");
//        double base = sc.nextDouble();
//        System.out.print("Digite a altura do retângulo: ");
//        double altura = sc.nextDouble();
//
//        double area = altura * base;
//        double perimetro = 2 * (altura + base);
//
//        System.out.printf("Área: %.2f\nPerímetro: %.2f", area, perimetro);
//        sc.close();
//    }
//}



////! EXERCÍCIO 8
//package controller;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        double PI = 3.14159;
//
//        System.out.printf("Digite o raio do círculo: ");
//        double raio = sc.nextDouble();
//
//        double volume = (4/3) * PI * Math.pow(raio, 3);
//        System.out.println("O volume do círculo é: %.2f");
//        sc.close();
//    }
//}



////! EXERCÍCIO 9
//package controller;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Digite um valor em reais: ");
//        double reais = sc.nextDouble();
//
//        System.out.print("Digite a cotação do dólar: ");
//        double cotacao = sc.nextDouble();
//
//        double dolar = reais / cotacao;
//        System.out.printf("R$%.2f -> US$%.2f", reais, dolar);
//
//        System.out.println();
//        sc.close();
//    }
//}



//! EXERCÍCIO 10
package controller;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o local x1: ");
        double x1 = sc.nextDouble();
        System.out.print("Digite o local x2: ");
        double x2 = sc.nextDouble();
        System.out.print("Digite o local y1: ");
        double y1 = sc.nextDouble();
        System.out.print("Digite o local y2: ");
        double y2 = sc.nextDouble();

        double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        System.out.printf("Distância: %.2f", distancia);
        sc.close();
    }
}