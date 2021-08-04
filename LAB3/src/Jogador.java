import java.util.ArrayList;

public class Jogador {
    private String username;
    private String password;
    private int pontuacao;
    private boolean status = false;
    private boolean jogando = false;
    private ArrayList<Partida> historicoDePartidas = new ArrayList<Partida>();

    public Jogador(String username, String password) {
        this.username = username;
        this.password = password;
        this.pontuacao = 1000;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = this.pontuacao + pontuacao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getJogando() {
        return jogando;
    }

    public void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    public ArrayList<Partida> getHistoricoDePartidas() {
        return historicoDePartidas;
    }

    public void setHistoricoDePartidas(Partida partida) {
        this.historicoDePartidas.add(partida);
    }
}
