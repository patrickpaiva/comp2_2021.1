import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FracaoTest {

    private final double DELTA = 0.000001;

    @Test
    public void testarEqualsParaNumeradoEDenominadorIdenticos() {
        Fracao fracao1 = new Fracao(1, 2);
        Fracao fracao2 = new Fracao(1, 2);
        assertFalse(fracao1 == fracao2);
        assertTrue(fracao1.equals(fracao2));  // o equals default é ==
    }

    @Test
    public void testarEqualsParaFracoesEquivalentes() {
        Fracao fracao1 = new Fracao(1, 2);
        Fracao fracao2 = new Fracao(2, 4);
        assertFalse(fracao1 == fracao2);
        assertTrue(fracao1.equals(fracao2));
    }

    @Test
    public void testarEqualsParaFracoesOpostas() {
        Fracao fracao1 = new Fracao(1, 2, true);  //   1/2
        Fracao fracao2 = new Fracao(1, 2, false); //  -1/2
        assertFalse(fracao1 == fracao2);
        assertFalse(fracao1.equals(fracao2));
    }

    @Test
    public void testarFracaoIgualAZero() {
        Fracao fracao = new Fracao(0, 678);
        assertEquals(0, fracao.getNumerador());
        assertEquals(1, fracao.getDenominador());
    }

    @Test
    public void testarGetNumerador() {
        Fracao fracao;

        fracao = new Fracao(2, 7);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(-2, 7);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(2, -7);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(-2, -7);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(2, 7, true);
        assertEquals(2, fracao.getNumerador());

        fracao = new Fracao(2, 7, false);
        assertEquals(2, fracao.getNumerador());
    }

    @Test
    public void testarGetDenominador() {
        Fracao fracao;

        fracao = new Fracao(2, 7);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(-2, 7);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(2, -7);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(-2, -7);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(2, 7, true);
        assertEquals(7, fracao.getDenominador());

        fracao = new Fracao(2, 7, false);
        assertEquals(7, fracao.getDenominador());
    }

    @Test
    public void testarFracaoIrredutivel() {
        Fracao fracao = new Fracao(5, 20, false);
        Fracao fracaoIrredutivel = fracao.getFracaoIrredutivel();

        assertEquals(1, fracaoIrredutivel.getNumerador());
        assertEquals(4, fracaoIrredutivel.getDenominador());
        assertFalse(fracaoIrredutivel.getSinal());
    }

    @Test
    public void testarFracaoIrredutivelQuandoAPropriaFracaoJaEhIrredutivel() {
        Fracao fracao = new Fracao(4, 25, true);
        Fracao fracaoIrredutivel = fracao.getFracaoIrredutivel();

        assertTrue(fracao == fracaoIrredutivel);
    }

    @Test
    public void testarSimplificar() {
        Fracao fracao = new Fracao(10, -36);

        assertEquals(10, fracao.getNumerador());
        assertEquals(36, fracao.getDenominador());
        assertFalse(fracao.getSinal());

        fracao.simplificar();

        assertEquals(5, fracao.getNumerador());
        assertEquals(18, fracao.getDenominador());
        assertFalse(fracao.getSinal());
    }

    @Test
    public void testarValorNumericoParaFracaoPositiva() {
        Fracao fracao = new Fracao(2, 5);
        assertEquals(0.4, fracao.getValorNumerico(), DELTA);
    }

    @Test
    public void testarValorNumericoParaFracaoNegativa() {
        Fracao fracao = new Fracao(2, 5, false);
        assertEquals(-0.4, fracao.getValorNumerico(), DELTA);
    }

    @Test
    public void testarValorNumericoParaFracaoIgualAZero() {
        Fracao fracao = new Fracao(0, 678);
        assertEquals(0, fracao.getValorNumerico(), DELTA);
    }

    @Test
    public void testarSoma() {
        Fracao fracao1 = new Fracao(1, 3);
        Fracao fracao2 = new Fracao(3, 5);

        Fracao resultadoEsperado = new Fracao(14, 15);
        Fracao resultadoObtido = fracao1.somar(fracao2);

        assertEquals(resultadoEsperado.getNumerador(), resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(), resultadoObtido.getDenominador());
        assertTrue(resultadoObtido.getSinal());
    }

    @Test
    public void testarMultiplicacao() {
        verificarMultiplicacaoEspecifica(
                1, 3,
                3, 5,
                1, 5, true);

        verificarMultiplicacaoEspecifica(
                -1, 3,
                3, 5,
                1, 5, false);

        verificarMultiplicacaoEspecifica(
                -1, 3,
                0, 5,
                0, 1, true);

        verificarMultiplicacaoEspecifica(
                -1, 3,
                2, -9,
                2, 27, true);
    }

    private void verificarMultiplicacaoEspecifica(
            int numeradorFracao1, int denominadorFracao1,
            int numeradorFracao2, int denominadorFracao2,
            int numeradorResultadoEsperado, int denominadorResultadoEsperado, boolean sinalEsperado) {

        Fracao fracao1 = new Fracao(numeradorFracao1, denominadorFracao1);
        Fracao fracao2 = new Fracao(numeradorFracao2, denominadorFracao2);

        Fracao resultadoEsperado = new Fracao(numeradorResultadoEsperado, denominadorResultadoEsperado);
        Fracao resultadoObtido = fracao1.multiplicar(fracao2);

        assertEquals(resultadoEsperado.getNumerador(), resultadoObtido.getNumerador());
        assertEquals(resultadoEsperado.getDenominador(), resultadoObtido.getDenominador());
        assertEquals(resultadoObtido.getSinal(), sinalEsperado);
    }

    @Test
    public void testarToString() {
        Fracao fracao;

        fracao = new Fracao(1, 3, false);
        assertEquals("-1/3", fracao.toString());

        fracao = new Fracao(1, -3);
        assertEquals("-1/3", fracao.toString());

        fracao = new Fracao(2, 5);
        assertEquals("2/5", fracao.toString());

        fracao = new Fracao(8, 4);
        assertEquals("8/4", fracao.toString());

        fracao = new Fracao(9, 1);
        assertEquals("9", fracao.toString());

        fracao = new Fracao(0, 7);
        assertEquals("0", fracao.toString());

        fracao = new Fracao(0, 7, false);
        assertEquals("0", fracao.toString());
    }

    @Test
    public void testarCompareTo() {
        Fracao fracao1 = new Fracao(1, 3, false);
        Fracao fracao2 = new Fracao(1, -3);
        Fracao fracao3 = new Fracao(2, 5);
        Fracao fracao4 = new Fracao(8, 4);
        Fracao fracao5 = new Fracao(9, 1);
        Fracao fracao6 = new Fracao(0, 7);
        Fracao fracao7 = new Fracao(0, 7, false);

        List<Fracao> lista = new ArrayList<>();

        lista.add(fracao1);
        lista.add(fracao2);
        lista.add(fracao3);
        lista.add(fracao4);
        lista.add(fracao5);
        lista.add(fracao6);
        lista.add(fracao7);

        Collections.sort(lista);

        assertEquals("-1/3", lista.get(0).toString());
        assertEquals("-1/3", lista.get(1).toString());
        assertEquals("0", lista.get(2).toString());
        assertEquals("0", lista.get(3).toString());
        assertEquals("2/5", lista.get(4).toString());
        assertEquals("8/4", lista.get(5).toString());
        assertEquals("9", lista.get(6).toString());
    }
}