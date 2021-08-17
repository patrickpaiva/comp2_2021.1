public abstract class Impressora {

    private int numeroDeFolhas = 0;
    private int documentosImpressos = 0;

    public boolean imprimirDocumento(Documento documento) {
        // verifica se há papel suficiente (se não houver, não imprime)
        if (numeroDeFolhas < documento.getQuantPaginas()) {
            return false;
        }

        // incrementa o número de documentos impressos
        this.documentosImpressos++;

        // para cada página, manda imprimir de fato
        for (int i = 1; i <= documento.getQuantPaginas(); i++) {
            executarImpressaoPagina(documento.getPagina(i));
            this.numeroDeFolhas--;
        }

        return true;
    }

    public void carregarPapel(int numeroDeFolhas) {
        this.numeroDeFolhas += numeroDeFolhas;
    }

    public int getQuantidadeFolhasRestantes() {
        return this.numeroDeFolhas;
    }

    public int getQuantidadeDocumentosImpressos() {
        return this.documentosImpressos;
    }

    public abstract void executarRotinaLimpeza();

    public abstract void executarImpressaoPagina(String pagina);
}
