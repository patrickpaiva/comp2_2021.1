package modelo;

import java.util.*;

public class Usuario {

    private String nome;

    private long cpf;

    private String endereco;

    private Map<Livro, Integer> objetosADevolver = new HashMap<>();

    private Integer livrosEmPosseDoUsuario;

    public Usuario(String nome, long cpf) {

        this.nome = nome;

        this.cpf = cpf;

        this.livrosEmPosseDoUsuario = 0;
    }

    public long getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        return;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        return;
    }

    public void devolverLivro( Livro livro) {
        if(objetosADevolver.get(livro) == 1) {
            objetosADevolver.remove(livro);
            livrosEmPosseDoUsuario--;
            return;
        }

        int quantidadeNova = objetosADevolver.get(livro) - 1;

        objetosADevolver.replace(livro, quantidadeNova);

        livrosEmPosseDoUsuario--;

        return;
    }

    public void emprestarLivro (Livro livro) {
        if (objetosADevolver.containsKey(livro)) {
            objetosADevolver.replace(livro, objetosADevolver.get(livro) + 1);
        } else {
            objetosADevolver.put(livro, 1);
        }
        livrosEmPosseDoUsuario++;
    }

    public boolean possuiObjeto (Object obj){
        if(objetosADevolver.containsKey(obj)){
            return true;
        }else{
            return false;
        }
    }

    public int quantidadeLivrosEmPosse () {
        return this.livrosEmPosseDoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return cpf == usuario.cpf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
