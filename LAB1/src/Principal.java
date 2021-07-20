import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Principal {

    static Random meuGerador = new Random();

    public static int obterTamanhoIntersecao(
            ArrayList<Integer> lista1, ArrayList<Integer> lista2) {
        ArrayList<Integer> intersection = new ArrayList<Integer>();

        for (Integer item : lista1) {
            if (lista2.contains(item)) {
                intersection.add(item);
            }
        }

        Integer intersectionSize = intersection.size();

        return intersectionSize;
    }

    /**
     * Sorteia um inteiro aleatório entre "menor" (inclusive) e "maior" (inclusive)
     *
     * @param menor o limite inferior do intervalo desejado (fechado)
     * @param maior o limite superior do intervalo desejado (fechado)
     *
     * @return um inteiro pseudo-aleatório
     */
    public static int sortearInt(int menor, int maior) {
        return meuGerador.nextInt(maior - menor + 1) + menor;
    }

    public static void main(String[] args) {
        ArrayList<Integer> lista1 = new ArrayList<Integer>();
        ArrayList<Integer> lista2 = new ArrayList<Integer>();
        System.out.println("Insira o tamanho das listas: ");
        Scanner scanner = new Scanner(System.in);
        Integer tamanho = scanner.nextInt();
        if (tamanho < 1) {
            System.out.println("Tamanho invalido, tente novamente.");
            return;
        }
        for (int i = 0; i < tamanho; i++) {
            lista1.add(sortearInt(0, 10*tamanho));
            lista2.add(sortearInt(0, 10*tamanho));
        }
        System.out.println("Lista 1");
        System.out.println(lista1);

        System.out.println("Lista 2");
        System.out.println(lista2);

        Integer quantidadeIntersecao = obterTamanhoIntersecao(lista1, lista2);

        System.out.println("A quantidade de termos na interseção é: " + quantidadeIntersecao);

        return;

    }
}