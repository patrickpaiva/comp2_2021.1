import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SomaDoParTest {

    private ArrayList<Integer> lista;

    @Before
    public void setUp() {
        int[] array = new int[] {1, 45, -8, 50, 12, 900, -7, 4, 49};
        lista = new ArrayList<>();
        for (int elemento : array) {
            lista.add(elemento);
        }

        for (int i = 1000; i < 1000000; i++) {
            lista.add(i);
        }
    }

    @Test
    public void testarSomaDoParQuandoEncontraLinear() {
        long inicio = System.currentTimeMillis();
        assertEquals(Integer.valueOf(-8), SomaDoPar.encontrarParComSomaDadaLinear(lista, 4));
        assertEquals(Integer.valueOf(4), SomaDoPar.encontrarParComSomaDadaLinear(lista, 53));
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("O tempo de duração da execução linear foi: %.4f\n", (duracao/1000f));
    }

    @Test
    public void testarSomaDoParQuandoEncontraQuadratico() {
        long inicio = System.currentTimeMillis();
        assertEquals(Integer.valueOf(-8), SomaDoPar.encontrarParComSomaDadaQuadratico(lista, 4));
        assertEquals(Integer.valueOf(4), SomaDoPar.encontrarParComSomaDadaQuadratico(lista, 53));
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("O tempo de duração da execução quadratica foi: %.4f\n", (duracao/1000f));
    }

    @Test
    public void testarSomaDoParQuandoNaoEncontra() {
        assertNull(SomaDoPar.encontrarParComSomaDadaLinear(lista, 10000000));
        assertNull(SomaDoPar.encontrarParComSomaDadaLinear(lista, 1));
        assertNull(SomaDoPar.encontrarParComSomaDadaLinear(lista, 0));
    }

}