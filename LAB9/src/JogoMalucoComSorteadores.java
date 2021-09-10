public class JogoMalucoComSorteadores extends JogoDeDoisJogadores{

    private Sorteador sorteador1;
    private Sorteador sorteador2;

    public JogoMalucoComSorteadores(
            String nomeJogo,
            String nomeJogador1,
            String nomeJogador2,
            int numeroDeRodadas,
            Sorteador sorteador1,
            Sorteador sorteador2
    ) {
        super(nomeJogo, nomeJogador1, nomeJogador2, numeroDeRodadas);
        this.sorteador1 = sorteador1;
        this.sorteador2 = sorteador2;
    }

    @Override
    protected int executarRodadaDoJogo() {
        int inteiroJogador1 = sorteador1.sortear();
        int inteiroJogador2 = sorteador2.sortear();

        if (inteiroJogador1 > inteiroJogador2) {
            return 1;
        } else if(inteiroJogador2 > inteiroJogador1) {
            return 2;
        }
        return 0;
    }
}
