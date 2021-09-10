import java.util.Random;

public class Dado implements Sorteador {
    public int sortear() {
        Random meuRandom = new Random();

        int resultado = meuRandom.nextInt(6);

        return resultado + 1;
    }
}
