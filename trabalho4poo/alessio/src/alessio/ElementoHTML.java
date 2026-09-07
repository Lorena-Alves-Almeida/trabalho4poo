package alessio;

import java.util.Map;
import java.util.HashMap;

public abstract class ElementoHTML {
    protected String id;
    protected String classeCss;
    protected Map<String, String> estilos = new HashMap<>();

    public ElementoHTML(String id, String classeCss) {
        this.id = id;
        this.classeCss = classeCss;
    }

    public void adicionarEstilo(String propriedade, String valor) {
        this.estilos.put(propriedade, valor);
    }

    protected String formatarEstilos() {
        if (estilos.isEmpty()) return "";
        StringBuilder sb = new StringBuilder(" style=\"");
        estilos.forEach((prop, val) -> sb.append(prop).append(": ").append(val).append("; "));
        sb.append("\"");
        return sb.toString();
    }
//
    // Método polimórfico que cada elemento implementa
    public abstract String renderizar(int nivelIndentacao);

    protected String getIndentacao(int nivel) {
        return "  ".repeat(nivel);
    }
}

