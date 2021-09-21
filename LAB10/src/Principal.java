public class Principal {

    public static void main(String[] args) {

        DadosDeGamao dadosDeGamao = new DadosDeGamao();
        DadosTriplos dadosTriplos = new DadosTriplos();

        JogoMalucoComSorteadores<Sorteador, Sorteador> jogoMaluco = new JogoMalucoComSorteadores<Sorteador, Sorteador>(
                "JogadorDosDadosDeGamao", "JogadorDosDadosTriplos",
                0, dadosDeGamao, dadosTriplos);

        for (int numeroDeRodadas = 1; numeroDeRodadas <= 100000; numeroDeRodadas *= 2) {
            jogoMaluco.setNumeroDeRodadas(numeroDeRodadas);

            for (int i=0; i < 1000; i++) {
                jogoMaluco.jogar();
            }

            System.out.println("#===========================================#");
            System.out.println("Com "+ numeroDeRodadas + " rodadas:");
            System.out.println("Vitórias do Jogador 1: " + jogoMaluco.getPercentualVitoriasJogador1()+"%");
            System.out.println("Vitórias do Jogador 2: " + jogoMaluco.getPercentualVitoriasJogador2()+"%");
            System.out.println("Empates: " + jogoMaluco.getPercentualEmpates());


//            System.out.println(jogoMaluco.obterResultadoUltimoJogo());
        }

    }
}
