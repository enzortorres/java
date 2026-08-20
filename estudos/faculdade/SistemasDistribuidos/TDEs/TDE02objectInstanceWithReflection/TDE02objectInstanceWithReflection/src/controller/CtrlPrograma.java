// Enzo Ribas Torres
package controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class CtrlPrograma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome completo da classe (ex: Empresa ou pacote.Empresa): ");
        String nomeClasse = scanner.nextLine();

        try {
            Class classe = Class.forName(nomeClasse);
            
            Object objeto = classe.getDeclaredConstructor().newInstance();

            System.out.println("\nInspecionando os atributos da classe " + classe.getSimpleName() + "...");
            
            Field[] atributos = classe.getDeclaredFields();

            for (Field atributo : atributos) {
                Class tipoAtributo = atributo.getType();

                if (tipoAtributo.equals(int.class) || tipoAtributo.equals(Integer.class) || tipoAtributo.equals(String.class)) {
                    System.out.print("Informe o valor para o atributo '" + atributo.getName() + "' (" + tipoAtributo.getSimpleName() + "): ");
                    String valorDigitado = scanner.nextLine();

                    String nomeAtributo = atributo.getName();
                    String nomeMetodoSet = "set" + nomeAtributo.substring(0, 1).toUpperCase() + nomeAtributo.substring(1);

                    try {
                        Method metodoSet = classe.getMethod(nomeMetodoSet, tipoAtributo);

                        if (tipoAtributo.equals(int.class) || tipoAtributo.equals(Integer.class)) {
                            metodoSet.invoke(objeto, Integer.parseInt(valorDigitado));
                        } else if (tipoAtributo.equals(String.class)) {
                            metodoSet.invoke(objeto, valorDigitado);
                        }
                    } catch (NoSuchMethodException e) {
                        System.out.println("  -> Aviso: Método setter '" + nomeMetodoSet + "' não encontrado para o atributo '" + nomeAtributo + "'.");
                    }
                }
            }

            System.out.println("\n--- Resultado final do objeto ---");
            System.out.println(objeto.toString());

        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Classe '" + nomeClasse + "' não encontrada. Certifique-se de usar o nome completo da classe, incluindo pacotes se houver.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a reflexão: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
