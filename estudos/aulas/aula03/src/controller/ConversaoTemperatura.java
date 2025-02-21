package controller;

import java.util.Scanner;

public class ConversaoTemperatura {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite uma temperatura em Celsius: ");
        double celsius = sc.nextDouble();
        double fahrenheit = (celsius * 1.8) + 32;
        System.out.printf("%.2fºC em fahrenheit: %.2fºF\n", celsius, fahrenheit);

        sc.close();
    }
}
