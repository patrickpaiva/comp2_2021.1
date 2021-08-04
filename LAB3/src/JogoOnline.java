import java.util.ArrayList;
import java.util.Optional;

public class JogoOnline {
    private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
    private ArrayList<Partida> partidasEmAndamento = new ArrayList<Partida>();

    public void login(String username, String password) throws Exception {
        Optional<Jogador> buscarJogador = jogadores.stream().filter(jogador ->
                (jogador.getUsername() == username && jogador.getPassword() == password))
                .findAny();
        if (!buscarJogador.isPresent()) {
            throw new Exception("Usuário ou senha inválidos.");
        }

        buscarJogador.get().setStatus(true);
    }

    public void logout(String username) {
        jogadores.forEach((jogador) -> {
            if(jogador.getUsername() == username) {
                jogador.setStatus(false);
                jogador.setJogando(false);
            }
        });

    }

    public Jogador novoJogador(String username, String password) throws Exception {
        Optional<Jogador> buscarJogador = jogadores.stream().filter(jogador ->
                (jogador.getUsername() == username))
                .findFirst();
        if (buscarJogador.isPresent()) {
            throw new Exception("Username já está em uso.");
        }

        Jogador novoJogador = new Jogador(username, password);
        jogadores.add(novoJogador);
        return novoJogador;
    }

    public Partida iniciarPartida(Jogador jogador1, Jogador jogador2) throws Exception {
        if (jogador1.getJogando() || jogador2.getJogando()) {
            throw new Exception("Jogadores não podem estar em outro jogo");
        }

        if (!jogador1.getStatus() || !jogador2.getStatus()) {
            throw new Exception("Jogadores devem estar online");
        }

        Partida novaPartida = new Partida(jogador1, jogador2);
        this.partidasEmAndamento.add(novaPartida);
        return novaPartida;
    }

    public Jogador escolheAdversario(Jogador solicitante) throws Exception {
        Optional<Jogador> adversario = jogadores.stream().filter(jogador ->
                (jogador.getStatus() && !jogador.getJogando() && jogador != solicitante))
                .findAny();
        if (!adversario.isPresent()) {
            throw new Exception("Nenhum usuario online para ser adversario.");
        }

        return adversario.get();
    }

    public void encerrarPartida(Partida partida, int resultado) {
        partida.setResultado(resultado);

        partida.getJogador1().setJogando(false);
        partida.getJogador2().setJogando(false);

        partida.getJogador1().setHistoricoDePartidas(partida);
        partida.getJogador2().setHistoricoDePartidas(partida);

        if (resultado == 1) {
            partida.getJogador1().setPontuacao(1);
            partida.getJogador2().setPontuacao(-1);
        } else if (resultado == 2) {
            partida.getJogador2().setPontuacao(1);
            partida.getJogador1().setPontuacao(-1);
        }

    }
}
