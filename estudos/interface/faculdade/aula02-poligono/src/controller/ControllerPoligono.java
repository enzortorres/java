package controller;

import model.*;

import java.util.Scanner;

public class ControllerPoligono {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        System.out.println("Digite o polígono que deseja calcular a área:");
        System.out.println("1 - Quadrado");
        System.out.println("2 - Retângulo");
        System.out.println("3 - Triângulo");
        System.out.println("4 - Trapézio");
        System.out.print(">>> ");

        opcao = sc.nextInt();

        switch(opcao) {
            case 1:
                System.out.print("Digite o lado do quadrado em metros: ");
                Double lado_quadrado = sc.nextDouble();
                Quadrado quadrado = new Quadrado(lado_quadrado);
                System.out.printf("Área: %.2fm²", quadrado.calcularArea());
                break;
            case 2:
                System.out.print("Digite o lado do retângulo em metros: ");
                Double base_retangulo = sc.nextDouble();
                System.out.print("Digite a altura do retângulo em metros: ");
                Double altura_retangulo = sc.nextDouble();
                Retangulo retangulo = new Retangulo(base_retangulo, altura_retangulo);
                System.out.printf("Área: %.2fm²", retangulo.calcularArea());
                break;
            case 3:
                System.out.print("Digite a base do triângulo em metros: ");
                Double base_triangulo = sc.nextDouble();
                System.out.print("Digite a altura do triângulo em metros: ");
                Double altura_triangulo = sc.nextDouble();
                Triangulo triangulo = new Triangulo(base_triangulo, altura_triangulo);
                System.out.printf("Área: %.2fm²", triangulo.calcularArea());
                break;
            case 4:
                System.out.print("Digite a base menor do trapézio em metros: ");
                Double baseMenor_trapezio = sc.nextDouble();
                System.out.print("Digite a base maior do trapézio em metros: ");
                Double baseMaior_trapezio = sc.nextDouble();
                System.out.print("Digite a altura do trapézio em metros: ");
                Double altura_trapezio = sc.nextDouble();
                Trapezio trapezio = new Trapezio(baseMenor_trapezio, baseMaior_trapezio, altura_trapezio);
                System.out.printf("Área: %.2fm²", trapezio.calcularArea());
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}
