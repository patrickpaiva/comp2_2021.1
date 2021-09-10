public class DadosDeGamao implements Sorteador {
    @Override
    public int sortear() {
        Dado dado = new Dado();
        int resultadoDado1 = dado.sortear();
        int resultadoDado2 = dado.sortear();

        if (resultadoDado1 == resultadoDado2) return resultadoDado1 * 4;

        return resultadoDado1 + resultadoDado2;
    }
}
