import java.util.Objects;

public class Fracao implements Comparable<Fracao> {

    private int numerador;  // o valor absoluto (não-negativo) do numerador
    private int denominador;  // o valor absoluto (positivo) do denominador
    private boolean sinal;  // true, se não-negativa; false, se negativa

    private Fracao minhaFracaoIrredutivel;

    /**
     * Cria uma fração. O sinal da fração será inferido a partir dos sinais
     * do numerador e denominador.
     *
     * Obs.: Caso a fração seja igual a zero, o denominador será sempre
     *       armazenado como 1.
     *
     * @param numerador um inteiro qualquer
     * @param denominador um inteiro diferente de zero
     */
    public Fracao(int numerador, int denominador) {
        this(Math.abs(numerador),
                Math.abs(denominador),
                AritmeticaBasica.extrairSinal(numerador / (float) denominador));
    }

    /**
     * Cria uma fração. O sinal da fração é informado explicitamente.
     *
     * Obs.: Caso a fração seja igual a zero, o denominador será sempre
     *       armazenado como 1.
     *
     * @param numerador um inteiro qualquer não-negativo
     * @param denominador um inteiro positivo
     * @param sinal true, se positiva (ou zero); false, se negativa
     */
    public Fracao(int numerador, int denominador, boolean sinal) {  // overload do construtor
        if (numerador < 0) {
            throw new RuntimeException("O numerador precisa ser não-negativo!");
        }
        if (denominador <= 0) {
            throw new RuntimeException("O denominador precisa ser positivo!");
        }

        this.numerador = numerador;
        this.denominador = numerador != 0 ? denominador : 1;
        this.sinal = numerador != 0 ? sinal : true;
        this.minhaFracaoIrredutivel = null;  // não inicializado! (lazy intantiation vai acontecer no momento oportuno)
    }

    /**
     * Retorna o (valor absoluto do) numerador da fração
     *
     * @return um inteiro não-negativo
     */
    public int getNumerador() {
        return this.numerador;
    }

    /**
     * Retorna o (valor absoluto do) denominador da fração
     *
     * @return um inteiro positivo
     */
    public int getDenominador() {
        return this.denominador;
    }

    /**
     * Retorna um boolean indicando o sinal da fração
     *
     * @return true, se a fração for não-negativa; false, se for negativa
     */
    public boolean getSinal() {
        return this.sinal;
    }

    public double getValorNumerico() {
        double valor = this.numerador / (double) this.denominador;
        // se não fizer o cast o resultado seria um int, porque int / int == int (truncado)

        if (!this.sinal) {  // se for negativa...
            valor = -valor;
        }

        return valor;
    }

    /**
     * Retorna uma Fracao que seja equivalente à fração original e irredutível
     * (numerador e denominador primos entre si).
     *
     * @return um outro objeto Fracao, caso esta fracao não seja irredutível;
     *         ou esta própria fração (this), caso ela própria já seja irredutível
     */
    public Fracao getFracaoIrredutivel() {
        garantirInicializacaoFracaoIrredutivel();
        return this.minhaFracaoIrredutivel;
    }

    private void garantirInicializacaoFracaoIrredutivel() {
        if (this.minhaFracaoIrredutivel != null) {
            return;  // já inicializado, nada a fazer!
        }

        int mdc = AritmeticaBasica.calcularMaximoDivisorComum(
                this.numerador, this.denominador);

        if (mdc == 1) {
            this.minhaFracaoIrredutivel = this;

        } else {
            this.minhaFracaoIrredutivel = new Fracao(
                    this.numerador / mdc, this.denominador / mdc, this.sinal);
        }
    }

    /**
     * Efetua uma adição.
     *
     * @param outra o segundo operando da adição (o primeiro é esta própria fração)
     *
     * @return uma TERCEIRA fração, criada agora, com o resultado da operação
     */
    public Fracao somar(Fracao outra) {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    public Fracao somar(int numero) {
        return null;  // ToDo IMPLEMENT ME!!!!
    }

    /**
     * Efetua uma multiplicação.
     *
     * @param outra o segundo operando da multiplicação (o primeiro é esta própria fração)
     *
     * @return uma TERCEIRA fração, criada agora, com o resultado da operação
     */
    public Fracao multiplicar(Fracao outra) {
        Fracao produto = new Fracao(
                this.numerador * outra.getNumerador(),
                this.denominador * outra.getDenominador(),
                this.sinal == outra.getSinal());
        produto.simplificar();
        return produto;
    }

    public Fracao multiplicar(int numero) {   // sobrecarga (overload) de método: tem o mesmo nome e assinatura diferente
        Fracao produto = new Fracao(
                this.numerador * numero,
                this.denominador,
                this.sinal == AritmeticaBasica.extrairSinal(numero));
        produto.simplificar();
        return produto;
    }



    /**
     * Modifica, possivelmente, o numerador e o denominador desta fração,
     * tornando-a irredutível (e equivalente à fração original)
     */
    public void simplificar() {
        garantirInicializacaoFracaoIrredutivel();
        this.numerador = this.minhaFracaoIrredutivel.getNumerador();
        this.denominador = this.minhaFracaoIrredutivel.getDenominador();
    }

    @Override
    public String toString() {
        return (this.sinal ? "" : "-") +
                this.numerador +
                (this.denominador != 1
                        ? "/" + this.denominador
                        : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fracao fracao = (Fracao) o;

        Fracao minhaFracaoIrredutivel = getFracaoIrredutivel();
        Fracao outraFracaoIrredutivel = fracao.getFracaoIrredutivel();

        return minhaFracaoIrredutivel.numerador == outraFracaoIrredutivel.numerador &&
                minhaFracaoIrredutivel.denominador == outraFracaoIrredutivel.denominador &&
                this.sinal == fracao.sinal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerador, denominador, sinal);
    }

    @Override
    public int compareTo(Fracao o) {
        if (this.getValorNumerico() > o.getValorNumerico()) {
            return 1;
        } else if (this.getValorNumerico() < o.getValorNumerico()) {
            return -1;
        } else {
            return 0;
        }
    }
}
