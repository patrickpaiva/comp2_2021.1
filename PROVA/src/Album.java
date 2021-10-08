import java.util.HashMap;
import java.util.Map;

/**
 * Esta classe implementa um álbum (de figurinhas, selos, etc.) online, permitindo
 * colecionar itens que possuem uma posição específica no álbum.
 *
 * Itens são acrescentados ao álbum por meio de "pacotinhos" contendo uma quantidade
 * fixa, pré-feterminada de itens.
 *
 * Itens já existentes no álbum e recebidos novamente em pacotinhos posteriores são
 * armazenados para eventuais trocas com outro usuários.
 *
 * @param <T> o tipo de objeto colecionável que o álbum irá armazenar
 */
public class Album<T extends Colecionavel> {

    private int tamanhoDoAlbum;
    private int quantItensPorPacotinho;
    private Map<Integer, T> colecionavelByPosicao; // colecionaveis coladas no album
    private Map<Integer, Integer> qtdRepetidasByPosicao; // colecionaveis repetidas

    /**
     * Construtor.
     *
     * @param tamanhoDoAlbum O tamanho do álbum sendo criado (note que os itens que serão colecionados
     *                       terão posições entre 1 e o tamanho do álbum)
     *
     * @param quantItensPorPacotinho A quantidade de itens em cada pacotinho adquirido para este álbum
     */
    public Album(int tamanhoDoAlbum, int quantItensPorPacotinho) {
        this.tamanhoDoAlbum = tamanhoDoAlbum;
        this.quantItensPorPacotinho = quantItensPorPacotinho;
        this.colecionavelByPosicao = new HashMap<>();
        this.qtdRepetidasByPosicao = new HashMap<>();
    }

    /**
     * Recebe novos itens que serão imediatamente "colados" ao álbum, se inéditos,
     * ou guardados para troca, se repetidos.
     *
     * @param pacotinho Um pacotinho de itens, que poderão ser inéditos ou repetidos
     *
     * @throws PacotinhoInvalidoException se o pacotinho contiver uma quantidade errada de itens,
     *                                    ou se contiver algum item que não pertença ao álbum
     *                                    (por indicar uma posição menor que 1 ou maior que seu tamanho)
     */
    public void receberNovoPacotinho(Pacotinho<T> pacotinho) throws PacotinhoInvalidoException {
        if (pacotinho.size() != this.quantItensPorPacotinho) throw new PacotinhoInvalidoException();

        for (T colecionavel: pacotinho) {
            if (colecionavel.getPosicao() < 1 || colecionavel.getPosicao() > this.tamanhoDoAlbum){
                throw new PacotinhoInvalidoException();
            }
        }

        for (T fig: pacotinho) {
            if (this.colecionavelByPosicao.containsKey(fig.getPosicao())) {
                this.adicionarRepetida(fig.getPosicao());
            } else {
                this.colecionavelByPosicao.put(fig.getPosicao(), fig);
            }
        }
    }

    private void adicionarRepetida(Integer posicao) {
        if (!this.qtdRepetidasByPosicao.containsKey(posicao)) {
            this.qtdRepetidasByPosicao.put(posicao, 1);
        } else {
            Integer qtdColecionavelRepetida = this.qtdRepetidasByPosicao.get(posicao);
            this.qtdRepetidasByPosicao.put(posicao, (qtdColecionavelRepetida + 1));
        }
    }

    /**
     * @return a quantidade total de figurinhas que este álbum apresenta quando se encontra completo
     */
    public int getTamanho() {
        return this.tamanhoDoAlbum;
    }

    /**
     * @return a quantidade total de itens que estão "colados" no álbum,
     * isto é, o total de itens distintos que foram recebidos até o momento
     */
    public int getQuantItensColados() {
        return this.colecionavelByPosicao.size();
    }

    /**
     * @return o total de itens repetidos que foram acumulados até o momento
     */
    public int getQuantItensRepetidos() {
        return this.qtdRepetidasByPosicao.size();
    }

    public boolean possuiItemColado(int posicao) {
        return this.colecionavelByPosicao.containsKey(posicao);
    }

    public boolean possuiItemRepetido(int posicao) {
        return this.qtdRepetidasByPosicao.containsKey(posicao);
    }

    /**
     * @return a quantidade de itens que faltam para o álbum ficar completo
     */
    public int getQuantItensFaltantes() {
        return (this.getTamanho() - this.colecionavelByPosicao.size());
    }

    /**
     * @param posicao a posição do iten desejado
     * @return o item que está colado na posição especificada, se houver; null, caso contrário
     */
    public T getItemColado(int posicao) {
        return this.colecionavelByPosicao.get(posicao);
    }
}
