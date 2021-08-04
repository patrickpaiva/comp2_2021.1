import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JogoOnlineTest {
    private JogoOnline jogo;
    private Jogador player1;
    private Jogador player2;

    @Before
    public void setUp() throws Exception {
        jogo = new JogoOnline();
        player1 = jogo.novoJogador("player1", "123");
        player2 = jogo.novoJogador("player2", "123");
    }
    @Test
    public void testarCriarJogador() throws Exception {
        Jogador novoJogador = jogo.novoJogador("player10", "123");

        assertEquals("Deve ser possível criar um novo Jogador",
                novoJogador.getUsername(),"player10");
    }

    @Test(expected = Exception.class)
    public void testarCriarJogadorComMesmoUsername() throws Exception {
        // não deve ser possível criar um jogador com um username que já está em uso.
        jogo.novoJogador("player12", "123");
        jogo.novoJogador("player12", "123");
    }

    @Test
    public void testarLogin() throws Exception {
        jogo.login("player1", "123");
        assertEquals("Deve ser possível fazer login", player1.getStatus(), true);
    }

    @Test(expected = Exception.class)
    public void testarLoginComSenhaInvalida() throws Exception {
        jogo.login("player1", "1234");
        assertEquals("Não deve ser possível fazer login com dados inválidos",
                player1.getStatus(), false);
    }

    @Test
    public void testarLogout() throws Exception {
        jogo.login("player1", "123");
        jogo.logout("player1");

        assertEquals("Deve ser possível fazer logout", player1.getStatus(), false);
    }

    @Test
    public void testarIniciarPartida() throws Exception {
        jogo.login("player1", "123");
        jogo.login("player2", "123");

        Partida novaPartida = jogo.iniciarPartida(player1, player2);

        assertTrue(novaPartida instanceof Partida);

    }

    @Test(expected = Exception.class)
    public void testarIniciarPartidaComJogadorIndisponivel() throws Exception {
        jogo.login("player1", "123");
        jogo.login("player2", "123");
        player1.setJogando(true);

        jogo.iniciarPartida(player1, player2);
    }

    @Test(expected = Exception.class)
    public void testarIniciarPartidaComJogadorOffline() throws Exception {
        jogo.login("player1", "123");
        jogo.logout("player1");
        jogo.login("player2", "123");

        jogo.iniciarPartida(player1, player2);
    }

    @Test
    public void testarEscolherAdversario() throws Exception {
        jogo.login("player1", "123");
        jogo.login("player2", "123");

        Jogador adversario = jogo.escolheAdversario(player1);

        assertTrue(adversario instanceof Jogador);
    }

    @Test(expected = Exception.class)
    public void testarEscolherAdversarioComApenasUmOnline() throws Exception {
        jogo.login("player1", "123");
        jogo.login("player2", "123");
        jogo.logout("player2");

        jogo.escolheAdversario(player1);

    }

    @Test
    public void testarEncerrarPartida() throws Exception {
        jogo.login("player1", "123");
        jogo.login("player2", "123");

        Partida novaPartida = jogo.iniciarPartida(player1, player2);

        jogo.encerrarPartida(novaPartida, 1);
        assertEquals("Player 1 deve ganhar 1 ponto",
                player1.getPontuacao(), 1001);
        assertEquals("Player 2 deve perder 1 ponto",
                player2.getPontuacao(), 999);
        assertEquals("Player 1 deve ficar disponivel",
                player1.getJogando(), false);
        assertEquals("Player 2 deve ficar disponivel",
                player2.getJogando(), false);

        Partida novaPartida2 = jogo.iniciarPartida(player1, player2);

        jogo.encerrarPartida(novaPartida2, 2);
        assertEquals("Player 2 deve ganhar 1 ponto",
                player2.getPontuacao(), 1000);
        assertEquals("Player 1 deve perder 1 ponto",
                player1.getPontuacao(), 1000);

        jogo.encerrarPartida(novaPartida2, 0);
        assertEquals("Player 2 deve manter pontuacao",
                player2.getPontuacao(), 1000);
        assertEquals("Player 1 deve manter pontuacao",
                player1.getPontuacao(), 1000);

        assertEquals("Historico deve constar 3 partidas",
                player1.getHistoricoDePartidas().size(), 3);
    }
}
