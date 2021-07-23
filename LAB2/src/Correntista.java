public class Correntista {

    private long cpf;
    private String nome;

    public Correntista( String nome, long cpf) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
}