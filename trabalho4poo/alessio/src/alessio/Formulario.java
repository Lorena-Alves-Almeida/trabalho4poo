package alessio;

import java.util.ArrayList;
import java.util.List;

public class Formulario extends ElementoHTML {
    private List<ElementoHTML> filhos = new ArrayList<>();
    private String acao;
    private String metodo;

    public Formulario(String id, String classeCss, String acao, String metodo) {
        super(id, classeCss);
        this.acao = acao;
        this.metodo = metodo;
    }

    public void adicionarFilho(ElementoHTML elemento) {
        this.filhos.add(elemento);
    }

    @Override
    public String renderizar(int nivel) {
        String indent = getIndentacao(nivel);
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s<form id=\"%s\" class=\"%s\" action=\"%s\" method=\"%s\"%s>\n",
                indent, id, classeCss, acao, metodo, formatarEstilos()));

        for (ElementoHTML filho : filhos) {
            sb.append(filho.renderizar(nivel + 1));
        }

        sb.append(indent).append("</form>\n");
        return sb.toString();
    }
}
