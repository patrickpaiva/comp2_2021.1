import java.util.HashMap;
import java.util.Map;

public class CaracterMaisFrequente {

    public static char encontrarCaracterMaisFrequenteQuadratico(String texto) {
        char maisFrequente = texto.charAt(0);
        int ocorrenciasDoMaisFrequente = 1;

        for (int i = 0; i < texto.length(); i++) {
            char caracterDaVez = texto.charAt(i);
            int contOcorrencias = 1;
            for (int j = i + 1; j < texto.length(); j++) {
                if (texto.charAt(j) == caracterDaVez) {
                    contOcorrencias++;
                }
            }

            if (contOcorrencias > ocorrenciasDoMaisFrequente) {
                maisFrequente = caracterDaVez;
                ocorrenciasDoMaisFrequente = contOcorrencias;
            }
        }

        return maisFrequente;
    }

    public static char encontrarCaracterMaisFrequenteLinear(String texto) {

        // Algoritmo eficiente (linear):
        HashMap<Character, Integer> listaCaracteres = new HashMap<>();

        char letraMaiorOcorrencia = '&';
        int maior = 0;

        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            if (listaCaracteres.containsKey(letra)) {
                listaCaracteres.put(letra, listaCaracteres.get(letra)+1);
            } else {
                listaCaracteres.put(letra, 1);
            }

        }

        for (Map.Entry<Character, Integer> par : listaCaracteres.entrySet()) {
            if (par.getValue() > maior) {
                maior = par.getValue();
                letraMaiorOcorrencia = par.getKey();
            }
        }

        return letraMaiorOcorrencia;

    }
}
