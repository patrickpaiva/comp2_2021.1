import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CaracterMaisFrequenteTest {
    private String textoGigante = " ";
    @Before
    public void setUp() {
        for (int i = 0; i < 1000; i++) {
            textoGigante += "abcdefghijklmnopqrstuvwxyz";
        }
    }

    @Test
    public void testarCaracterMaisFrequenteLinear() {
        long inicio = System.currentTimeMillis();
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequenteLinear("arara"+textoGigante));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequenteLinear("a r a r a 345 sdf hhh"));
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("O tempo de duração da execução linear foi: %.4f\n", (duracao/1000f));
    }

    @Test
    public void testarCaracterMaisFrequenteQuadratico() {
        long inicio = System.currentTimeMillis();
        assertEquals('a', CaracterMaisFrequente.encontrarCaracterMaisFrequenteQuadratico("arara"+textoGigante));
        assertEquals(' ', CaracterMaisFrequente.encontrarCaracterMaisFrequenteQuadratico("a r a r a 345 sdf hhh"));
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("O tempo de duração da execução quadratica foi: %.4f\n", (duracao/1000f));
    }
}