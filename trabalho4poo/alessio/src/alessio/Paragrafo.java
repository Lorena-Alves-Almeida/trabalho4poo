package alessio;

public class Paragrafo extends ElementoHTML {
    private String texto;

    public Paragrafo(String id, String classeCss, String texto) {
        super(id, classeCss);
        this.texto = texto;
    }

    @Override
    public String renderizar(int nivel) {
        String indent = getIndentacao(nivel);
        return String.format("%s<p id=\"%s\" class=\"%s\"%s>%s</p>\n",
                indent, id, classeCss, formatarEstilos(), texto);
    }
}

