public class SorteadorViaTrio extends DadoComum {

    private DadoGenerico dado;

    public SorteadorViaTrio(DadoGenerico dado) {
        this.dado = dado;
    }

    @Override
    public Integer sortear() {

        int X = (int) dado.sortear();
        int Y = (int) dado.sortear();
        int Z = (int) dado.sortear();

        return (X == Y) && (X == Z) ? 1 : 0;
    }
}
