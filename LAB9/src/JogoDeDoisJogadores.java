import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {
    private String nomeJogo;
    private String nomeJogador1;
    private String nomeJogador2;
    private int numeroDeRodadas;
    private ArrayList<Integer> historicoResultados;

    public JogoDeDoisJogadores(String nomeJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas) {
        this.nomeJogo = nomeJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public String getNomeJogador1() {
        return nomeJogador1;
    }

    public String getNomeJogador2() {
        return nomeJogador2;
    }

    public int getNumeroDeRodadas() {
        return numeroDeRodadas;
    }

    protected abstract int executarRodadaDoJogo();

    public String jogar() {
        int vitoriasJogador1 = 0;
        int vitoriasJogador2 = 0;
        int resultadoRodada;

        for (int i = 0; i < numeroDeRodadas; i++){
            resultadoRodada = executarRodadaDoJogo();

            if (resultadoRodada == 1) {vitoriasJogador1++;}
            if (resultadoRodada == 2) {vitoriasJogador2++;}

        }

        int calculoVencedor = vitoriasJogador1 - vitoriasJogador2;

        if (calculoVencedor == 0) {
            return "O jogo terminou em empate após " + numeroDeRodadas + " rodadas.";
        }

        if (calculoVencedor > 0) {
            return "O jogador " + this.nomeJogador1 + " venceu o jogo por " + vitoriasJogador1 + " a " + vitoriasJogador2;
        }

        return "O jogador " + this.nomeJogador2 + " venceu o jogo por " + vitoriasJogador2 + " a " + vitoriasJogador1;
    }
}
