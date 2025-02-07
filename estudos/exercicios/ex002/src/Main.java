import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do funcionário: ");
        String nome = sc.nextLine();

        System.out.print("Digite o salario do funcionário: R$");
        double prev_sal = sc.nextDouble();
        double percent, aumento;

        if (prev_sal <= 280) percent = 20;
        else if (prev_sal <= 700) percent = 15;
        else if (prev_sal <= 1500) percent = 10;
        else percent = 5;

        double curr_sal = prev_sal * (1 + percent / 100);
        aumento = curr_sal - prev_sal;

        System.out.printf("\nFuncionário: %s\n", nome);
        System.out.printf("Salário anterior: R$%.2f\n", prev_sal);
        System.out.printf("Percentual de ajuste: %.0f%%\n", percent);
        System.out.printf("Aumento: R$%.2f\n", aumento);
        System.out.printf("Salário atual: R$%.2f\n", curr_sal);

        sc.close();
    }
}
