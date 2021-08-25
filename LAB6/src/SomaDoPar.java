import java.util.ArrayList;
import java.util.HashSet;

public class SomaDoPar {

    /**
     * Percorre a lista dada para encontrar um par de elementos
     * cuja soma seja o valor desejado.
     *
     * @param numeros uma lista de inteiros
     * @param somaDesejada a soma desejada
     *
     * @return O menor dos elementos do par encontrado;
     *         ou null, caso nenhum par de elementos some o valor desejado
     */

    public static Integer encontrarParComSomaDadaQuadratico(
            ArrayList<Integer> numeros, int somaDesejada) {

        for (int i = 0; i < numeros.size(); i++) {
            for (int j = i + 1; j < numeros.size(); j++) {
                int x = numeros.get(i);
                int y = numeros.get(j);
                if (x + y == somaDesejada) {
                    return Math.min(x, y);
                }
            }
        }

        return null;
    }

    public static Integer encontrarParComSomaDadaLinear(ArrayList<Integer> numeros, int somaDesejada) {

        HashSet<Integer> listaNumeros = new HashSet<>(numeros);

        for (Integer item: listaNumeros) {
            int complemento = somaDesejada - item;
            if(listaNumeros.contains(complemento)) {
                return Math.min(item, complemento);
            }
        }

        return null;
    }
}