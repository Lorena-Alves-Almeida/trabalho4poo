package alessio;

import java.util.ArrayList;
import java.util.List;

public class Div extends ElementoHTML {
    private List<ElementoHTML> filhos = new ArrayList<>();

    public Div(String id, String classeCss) {
        super(id, classeCss);
    }

    public void adicionarFilho(ElementoHTML elemento) {
        this.filhos.add(elemento);
    }

    @Override
    public String renderizar(int nivel) {
        String indent = getIndentacao(nivel);
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("%s<div id=\"%s\" class=\"%s\"%s>\n", 
                indent, id, classeCss, formatarEstilos()));
        
        for (ElementoHTML filho : filhos) {
            sb.append(filho.renderizar(nivel + 1));
        }
        
        sb.append(indent).append("</div>\n");
        return sb.toString();
    }
}

