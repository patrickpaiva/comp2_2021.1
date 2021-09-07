import java.util.Comparator;

public class ComparadorDeJogadoresLexicografico implements Comparator<Jogador> {

    @Override
    public int compare(Jogador jogador1, Jogador jogador2) {
        return jogador1.getUsername().compareTo(jogador2.getUsername());
    }
}
