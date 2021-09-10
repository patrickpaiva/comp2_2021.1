public class Principal {
    public static void main(String[] args) {
        DadosDeGamao dadosDeGamao = new DadosDeGamao();
        DadosTriplos dadosTriplos = new DadosTriplos();

        JogoMalucoComSorteadores jogo1 = new JogoMalucoComSorteadores(
                "jogo1",
                "zezinho",
                "luizinho",
                10,
                dadosDeGamao,
                dadosTriplos
        );

        jogo1.jogar();
    }
}
