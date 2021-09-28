public class SorteadorViaDoisParesConsecutivos extends DadoComum {

    private DadoGenerico dado;

    public SorteadorViaDoisParesConsecutivos(DadoGenerico dado) {
        this.dado = dado;
    }

    @Override
    public Integer sortear() {

        int A = (int) dado.sortear();
        int B = (int) dado.sortear();
        int C = (int) dado.sortear();
        int D = (int) dado.sortear();

        return (A == B) && (C == D) ? 1 : 0;
    }
}
