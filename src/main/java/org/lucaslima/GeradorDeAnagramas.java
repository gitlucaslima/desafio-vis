package org.lucaslima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/** * Este programa gera todos os anagramas possíveis a partir de uma entrada fornecida pelo usario
 * a entrada deve conter letras distintas e ser válida, não vazia e sem caracteres inválidos
 * inclui funcionalidades para validação, geração de anagramas e testes unitário */
public class GeradorDeAnagramas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite algumas letras diferentes para criar anagramas:");
        String entrada = scanner.nextLine();

        if (!validarEntrada(entrada)) {
            System.out.println("Entrada inválida. certifique-se de informar apenas letras e que não esteja vazia.");
        } else {
            List<String> anagramas = gerarAnagramas(entrada);

            if (anagramas.isEmpty()) {
                System.out.println("Nenhum anagrama foi gerado, certifique-se de digitar algo válido.");
            } else {
                System.out.println("Aqui estão os anagramas:");
                anagramas.forEach(System.out::println);
            }
        }

        scanner.close();
    }

    // Verifica se a entrada fornecida é válida, ou seja, não é vazia e contém apenas letras
    public static boolean validarEntrada(String entrada) {
        if (entrada == null) {
            return false;
        }

        if (entrada.isEmpty()){
            return false;
        }

        return entrada.chars().allMatch(Character::isLetter);
    }

    // Cria uma lista com todos os anagramas possíveis baseados nas letras fornecidas
    public static List<String> gerarAnagramas(String entrada) {
        if (entrada == null || entrada.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> resultado = new ArrayList<>();
        List<Character> letras = new ArrayList<>();
        for (char c : entrada.toCharArray()) {
            letras.add(c);
        }

        List<List<Character>> trocas = trocasIterativas(letras);
        for (List<Character> troca : trocas) {
            StringBuilder anagrama = new StringBuilder();
            for (Character c : troca) {
                anagrama.append(c);
            }
            resultado.add(anagrama.toString());
        }

        return resultado;
    }

    // Gera todas as trocas possíveis das letras fornecidas usando um algoritmo iterativo.
    public static List<List<Character>> trocasIterativas(List<Character> letras) {
        // Lista de anagramas
        List<List<Character>> resultado = new ArrayList<>();
        // Tamanho da lista de letras
        int n = letras.size();
        //inicializa os índices com zero
        int[] indices = new int[n];

        // Adiciona a primeira entrada como primeiro anagrama
        resultado.add(new ArrayList<>(letras));

        for (int i = 0; i < n; ) { // Itera sobre as letras
            if (indices[i] < i) { // Realiza a troca
                Collections.swap(letras, i % 2 == 0 ? 0 : indices[i], i); // Troca as letras
                resultado.add(new ArrayList<>(letras)); // Adiciona o anagrama gerado
                indices[i]++; // Incrementa o índice
                i = 0; // Reinicia o índice
            } else {
                indices[i++] = 0;
            }
        }

        return resultado;
    }
}

// Testes para verificar o funcionamento da geração de anagramas e validações.
class TesteGeradorDeAnagramas {
    public static void main(String[] args) {
        // Caso 1: Entrada com múltiplas letras
        String teste1 = "abc";
        System.out.println("Teste 1 - Entrada: " + teste1);
        System.out.println(GeradorDeAnagramas.gerarAnagramas(teste1));

        // Caso 2: Entrada com uma única letra
        String teste2 = "a";
        System.out.println("Teste 2 - Entrada: " + teste2);
        System.out.println(GeradorDeAnagramas.gerarAnagramas(teste2));

        // Caso 3: Entrada vazia
        String teste3 = "";
        System.out.println("Teste 3 - Entrada: (vazia)");
        System.out.println(GeradorDeAnagramas.gerarAnagramas(teste3));

        // Caso 4: Entrada com caracteres inválidos
        String teste4 = "a1b";
        System.out.println("Teste 4 - Entrada: " + teste4);
        System.out.println(GeradorDeAnagramas.validarEntrada(teste4) ? "Válida" : "Inválida");
    }
}