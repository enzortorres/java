package controller;
import java.util.ArrayList;
import java.util.Scanner;

public class aula07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>(); // Opcional colocar o tipo pela segunda vez

        for (int i = 0; i < 3;i++) {
            numbers.add(i);
        }
        for (int i = 0; i < 3;i++) {
            System.out.println(numbers.get(i));
        }
    }
}
