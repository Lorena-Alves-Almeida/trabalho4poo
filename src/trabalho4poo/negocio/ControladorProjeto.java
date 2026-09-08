package trabalho4poo.negocio;

import trabalho4poo.ui.*;
import trabalho4poo.negocio.*;

import java.util.List;
import trabalho4poo.dados.*;

public class ControladorProjeto {
	private RepositorioProjeto repoProjeto;

	public ControladorProjeto() {
		repoProjeto = new RepositorioProjeto();
	}

	public boolean add(Projeto p) {
		if (p != null && !repoProjeto.existeNome(p.getNmProjeto())) {
			return repoProjeto.add(p);
		} else {
			return false;
		}
	}

	//procurar o projeto por codigo
	public Projeto projetoPorCod(int codigo) {
		return repoProjeto.projetoPorCod(codigo);
	}

	// listar
	public List<Projeto> listar() {
		return repoProjeto.listar();
	}

}
