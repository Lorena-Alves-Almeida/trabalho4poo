package alessio;

public class Input extends ElementoHTML {
    private String tipo;
    private String nome;
    private String placeholder;

    public Input(String id, String classeCss, String tipo, String nome, String placeholder) {
        super(id, classeCss);
        this.tipo = tipo;
        this.nome = nome;
        this.placeholder = placeholder;
    }

    @Override
    public String renderizar(int nivel) {
        String indent = getIndentacao(nivel);
        return String.format("%s<input type=\"%s\" id=\"%s\" name=\"%s\" class=\"%s\" placeholder=\"%s\"%s />\n",
                indent, tipo, id, nome, classeCss, placeholder, formatarEstilos());
    }
}
