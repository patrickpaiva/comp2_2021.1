public class JogoMalucoComSorteadores<S1 extends Sorteador, S2 extends Sorteador> extends JogoDeDoisJogadores {

    /**
     * Sorteador que será utilizada, em cada rodada, pelo jogador 1.
     */
    S1 sorteador1;

    /**
     * Sorteador que será utilizada, em cada rodada, pelo jogador 2.
     */
    S2 sorteador2;

    public JogoMalucoComSorteadores(String nomeJogador1, String nomeJogador2,
                                    int numeroDeRodadas,
                                    Sorteador sorteador1, Sorteador sorteador2) {
        super("Jogo Maluco", nomeJogador1, nomeJogador2, numeroDeRodadas);
        this.sorteador1 = (S1) sorteador1;
        this.sorteador2 = (S2) sorteador2;
    }

    @Override
    protected int executarRodadaDoJogo() {
        int valorSorteadoParaOJogador1 = sorteador1.sortear();
        int valorSorteadoParaOJogador2 = sorteador2.sortear();

        if (valorSorteadoParaOJogador1 > valorSorteadoParaOJogador2) {
            return VITORIA_JOGADOR_1;
        } else if (valorSorteadoParaOJogador1 < valorSorteadoParaOJogador2) {
            return VITORIA_JOGADOR_2;
        }
        return EMPATE;
    }
}
