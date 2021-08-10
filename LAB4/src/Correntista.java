import java.util.ArrayList;

public class Correntista extends PessoaFisica {

    private static final int LIMITE_DEFAULT = 100;

    private float limiteChequeEspecial;
    private Conta contaCorrente;
    private ContaInvestimento contaInvestimento;

    public Correntista(String nome, long cpf) {
        super(nome, cpf);
        this.contaCorrente = null;
        this.limiteChequeEspecial = LIMITE_DEFAULT;
    }

    public float getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(float limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public float getTotalInvestido() {
        return this.contaInvestimento.getSaldoEmReais();
    }

    public Conta getContaCorrente() {
        return contaCorrente;
    }

    public ContaInvestimento getContaInvestimento() { return contaInvestimento; }

    public void setContaCorrente(Conta contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public void setContaInvestimento(ContaInvestimento contaInvestimento) {
        this.contaInvestimento = contaInvestimento;
    }
}
