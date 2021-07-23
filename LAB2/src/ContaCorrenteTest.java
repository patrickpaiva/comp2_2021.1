import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTest {

    // para cobrir pequenos erros de precisão do tipo float
    private float FLOAT_DELTA = 0.00001f;

    private ContaCorrente contaDoJoao;
    private Correntista joao;
    private float saldoInicial;

    private ContaCorrente contaDoBeltrano;
    private Correntista beltrano;

    @Before
    public void setUp() {
        joao = new Correntista("Joao", 222222);
        contaDoJoao = new ContaCorrente(1, joao);
        saldoInicial = contaDoJoao.getSaldoEmReais();

        beltrano = new Correntista("Beltrano", 5555);
        contaDoBeltrano = new ContaCorrente(5, beltrano);
    }

    @Test
    public void testarSaldoInicialDaConta() {
        assertEquals("Toda conta, ao ser criada, deve começar com saldo de R$10,00.",
                10.0,
                saldoInicial,
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresValidos() {
        contaDoJoao.receberDepositoEmDinheiro(50);
        assertEquals("O saldo da conta deve ser atualizado após receber um depósito",
                saldoInicial + 50,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValoresNegativos() {
        contaDoJoao.receberDepositoEmDinheiro(-200);
        assertEquals("Depósitos de valores negativos devem ser solenemente ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);
    }

    @Test
    public void testarRecebimentoDepositoParaValorZero() {
        String extratoAntes = contaDoJoao.getExtrato();

        contaDoJoao.receberDepositoEmDinheiro(0);
        assertEquals("Depósitos de valor zero devem ser ignorados",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA);

        String extratoDepois = contaDoJoao.getExtrato();

        assertEquals("Depósitos ignorados não devem sequer constar do extrato",
                extratoAntes, extratoDepois);

    }

    @Test
    public void testarSaqueComFundos() {
        contaDoJoao.sacar(2);
        assertEquals("O valor sacado deve ser descontado do saldo da conta",
                saldoInicial - 2,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarSaqueSemFundos() {
        contaDoJoao.sacar(100000);
        assertEquals("Saques de valores maiores que o saldo não devem ser permitidos",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarTransferencia() {
        Correntista maria = new Correntista("Maria", 22222);
        ContaCorrente contaDaMaria = new ContaCorrente(2, maria);

        contaDoJoao.efetuarTransferecia(contaDaMaria, 3);

        assertEquals("",
                saldoInicial + 3,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA
        );

        assertEquals("",
                saldoInicial - 3,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarTransferenciaSemFundos() {
        Correntista maria = new Correntista("Maria", 22222);
        ContaCorrente contaDaMaria = new ContaCorrente(2, maria);

        contaDoJoao.efetuarTransferecia(contaDaMaria, 100000);

        assertEquals("Deve manter o saldo em caso de transferência sem fundos.",
                saldoInicial,
                contaDaMaria.getSaldoEmReais(),
                FLOAT_DELTA
        );

        assertEquals("Deve manter o saldo em caso de transferência sem fundos.",
                saldoInicial,
                contaDoJoao.getSaldoEmReais(),
                FLOAT_DELTA
        );
    }

    @Test
    public void testarGetQuantidadeDeTransacoesDeTodasAsContas() {
        Correntista maria = new Correntista("Maria", 22222);
        ContaCorrente contaDaMaria = new ContaCorrente(2, maria);

        Correntista fulano = new Correntista("Fulano", 33333);
        ContaCorrente contaDoFulano = new ContaCorrente(3, fulano);

        contaDoJoao.efetuarTransferecia(contaDaMaria, 100000); // transação sem sucesso
        contaDaMaria.receberDepositoEmDinheiro(50); // transação feita com sucesso
        contaDoFulano.sacar(5); // transação feita com sucesso

        // outras três transações foram feitas com sucesso nos testes anteriores.
        assertEquals("Deve exibir a quantidade total de transações efetuadas no banco.",
                5, // três transações que foram feitas com sucesso em testes anteriores mais 2 acima
                contaDoJoao.getQuantidadeDeTransacoesDeTodasAsContas());
    }

    @Test
    public void testarGetCpfDoCorrentista() {
        assertEquals("Deve ser possível consultar o cpf do correntista pela conta.",
                beltrano.getCpf(),
                contaDoBeltrano.getCpfDoCorrentista());
    }

}