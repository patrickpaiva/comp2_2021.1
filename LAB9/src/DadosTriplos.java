public class DadosTriplos implements Sorteador {
    @Override
    public int sortear() {
        Dado dado = new Dado();

        int resultado1 = dado.sortear();
        int resultado2 = dado.sortear();
        int resultado3 = dado.sortear();

        return resultado1 + resultado2 + resultado3;
    }
}
