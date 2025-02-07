import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//         ! TIPOS PRIMITIVOS

//        * numeros inteiros = 100, 234345, 255, 120, 1, 91939393939939394
//      TODO:      byte => 8 bits => -128 a 127
//      TODO:      short => 16 bits => -32.768 a 32.767
//      TODO:      int => 32 bits => -2.147.483.648 a 2.147.483.647
//      TODO:      long => 64 bits => -9.223.372.036.854.775.808 a 9.223.372.036.854.775.807
//
//      * numeros decimais = 1.2, 3.4, 5.6, 7.8, 9.8
//      TODO:      float => 32 bits => precisão simples
//      TODO:      double => 64 bits => precisão dupla

//      * String => representar palavras e frases
//      TODO:      char => representar um único caractere => a, b, c, d
//      TODO:      char meuChar = 'a';
//      TODO:      meuChar = "Fernanda";

//      * boolean => true ou false

//         ! CONDICIONAIS

        byte b = 100;
        short s = 10000;
        int i = 100000;
        long l = 100000L;
        float f = 10.5f;
        double d = 20.5;
        char c = 'A';
        String str = "Enzo";
        boolean bool = false;


        if (str.isBlank()) {
            System.out.println("Vazio\n");
        } else if (str.equals("Enzo")) {
            System.out.println("True\n");
        } else {
            System.out.println("Falso\n");
        }


//        ! VETORES/ARRAYS

        int[] idades = {16,17,18,19,20};

        idades[0] = 20;
        idades[1] = 21;
        idades[2] = 22;
        idades[3] = 23;
        idades[4] = 24;


        ArrayList<String> nomes = new ArrayList<>(); //* array dinâmica (precisa importar o java.util.ArrayList)

        nomes.add("Enzo");
        nomes.add("Leo");
        nomes.add("Joao");
        nomes.add("Maria");

        System.out.println("\n" + nomes.get(0));
        nomes.remove(0);
        System.out.println(nomes.get(0) + "\n");

        System.out.println();
        String[] nomesArr = new String[10];

        nomesArr[0] = "Enzo";
        nomesArr[2] = "Vitor";
        nomesArr[6] = "Bruno";
        nomesArr[8] = "Guilherme";


//        ! LOOPS (for/while)


        for (var j = 0; j < idades.length; j++) {
            System.out.println(idades[j]);
        }

        for (int j = 0; j < nomes.size(); j++) {
            System.out.println(nomes.get(j));
        }


        for (int j = 0; j < nomesArr.length; j++) {
            System.out.println(nomesArr[j]);
        }

        System.out.println();
        for (String nome : nomes) {
            System.out.println(nome);
        }

        System.out.println();

        int contador = 0;

        while (contador < 10) {
            System.out.println(contador + 1);
            contador++;
        }


//        ! CASTINGS (MUDANÇA DE TIPAGEM)

        double resultado = 1.5;
        int resultadoInt = (int) resultado;

        int valor = 10;
        double valorDouble = 10;

        String meuString = "10";
        int meuInt = Integer.parseInt(meuString);

        String minhaString = String.valueOf(meuInt);

//        ! OOP ( CLASSES, OBJETOS, MÉTODOS )

        Carro meuCarro1 = new Carro("Fusca");
        Carro meuCarro2 = new Carro("Sandero");
        Carro meuCarro3 = new Carro("Gol");

        meuCarro1.acelerar();
        meuCarro2.acelerar();
        meuCarro3.acelerar();
    }
}

class Carro {
    String modelo;

    public Carro(String modelo) {
        this.modelo = modelo;
    }

    public void acelerar() {
        System.out.println(this.modelo + " acelerando!");
    }
}