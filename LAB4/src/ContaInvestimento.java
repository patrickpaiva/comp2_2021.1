public class ContaInvestimento extends Conta {
    private String tipoInvestimento;
    private float taxaJuros;

    public ContaInvestimento(int numeroDaConta, Correntista correntista, String tipoInvestimento, float taxaJuros) {
        super(numeroDaConta, correntista);
        this.taxaJuros = taxaJuros;
        this.tipoInvestimento = tipoInvestimento;
        if(correntista.getContaCorrente() == null) {
            throw new RuntimeException("Correntista sem conta corrente!");
        }
    }

    public void aplicarJuros() {
        super.saldoEmReais = super.saldoEmReais * (this.taxaJuros + 1);
    }

    public void resgatarTotal() {
        this.correntista.getContaCorrente().receberDepositoEmDinheiro(this.saldoEmReais);
        this.saldoEmReais = 0;
    }

}
