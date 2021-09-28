import java.util.*;

public class DadoGenerico<T> implements Sorteador<T> {

    Map<T, Integer> frequenciaByResultado;

    public DadoGenerico(Map<T, Integer> frequenciaByResultado) {
        this.frequenciaByResultado = frequenciaByResultado;
    }

    @Override
    public T sortear() {
        T maiorKey = null;

        Object[] keys = frequenciaByResultado.keySet().toArray();
        Arrays.sort(keys);


        Integer max = Collections.max(frequenciaByResultado.values());
        int valorSorteado = new Random().nextInt(max);

        for (Object key : keys) {
            if (valorSorteado < frequenciaByResultado.get(key)) {
                maiorKey = (T) key;
            }
        }

        return maiorKey;
    }
}
