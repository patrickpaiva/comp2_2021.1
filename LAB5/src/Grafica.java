import java.util.ArrayList;

public class Grafica {

    private float precoPretoeBranco;
    private float precoColorido;
    private int ordemImpressora = 0;

    private final ArrayList<Impressora> impressoras = new ArrayList<>();

    public void imprimirDocumento(Documento documento) {
        impressoras.get(ordemImpressora).imprimirDocumento(documento);
        ordemImpressora++;
        if (ordemImpressora > 2) {
            ordemImpressora = 0;
        }
    }

    public float orcarImpressao(Documento documento) {
        float valorTotal;
        if (documento.isEmCores()) {
            valorTotal = (precoColorido * documento.getQuantPaginas());
        } else {
            valorTotal = (precoPretoeBranco * documento.getQuantPaginas());
        }

        return valorTotal;
    }

    public void adicionarImpressora(Impressora impressora) {
        this.impressoras.add(impressora);
    }

    public void setPrecoPorPagina(float precoPorPagina, boolean emCores) {
        if (emCores) {
            this.precoColorido = precoPorPagina;
        } else {
            this.precoPretoeBranco = precoPorPagina;
        }
    }
}
