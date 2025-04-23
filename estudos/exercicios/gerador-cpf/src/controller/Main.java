package controller;

public class Main {
    public static void main(String[] args) {
        String cpf_string = "";

        // ? Gerar os 9 primeiros digitos
        for (int i = 0; i < 9; i++) {
            cpf_string += (int) (Math.random() * 10);
        }

        // ? Calculo para gerar o primeiro digito pós '-'
        int soma_primeiro_digito = 0;

        for (int i = 0; i < 9; i++) {
            int digito = cpf_string.charAt(i) - '0';
            soma_primeiro_digito += digito * (10 - i);
        }

        int resultado_primeiro_digito = soma_primeiro_digito % 11;
        int primeiro_digito = (resultado_primeiro_digito < 2) ? 0 : 11 - resultado_primeiro_digito;

        cpf_string += primeiro_digito;

        // ? Calculo para gerar o segundo digito pós '-'
        int soma_segundo_digito = 0;
    
        for (int i = 0; i < 10; i++) {
            int digito = cpf_string.charAt(i) - '0';
            soma_segundo_digito += digito * (11 - i);
        }

        int resultado_segundo_digito = soma_segundo_digito % 11;
        int segundo_digito = (resultado_segundo_digito < 2) ? 0 : 11 - resultado_segundo_digito; // ? Fórmula para o segundo dígito

        cpf_string += segundo_digito;
        String cpf_formatado = cpf_string.substring(0, 3) + "." + cpf_string.substring(3, 6) + "." + cpf_string.substring(6, 9) + "-" + cpf_string.substring(9, 11);

        System.out.printf("CPF gerado: %s", cpf_formatado);
    }
}

