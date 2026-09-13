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

	public boolean add(String nmProjeto, Usuario proprietario, String privacidade) {
		return repoProjeto.add(nmProjeto, proprietario, privacidade);
	}

	//procurar o projeto por codigo
	public Projeto buscar(int codigo) {
		return repoProjeto.buscar(codigo);
	}

	// listar
	public List<Projeto> listar() {
		return repoProjeto.listar();
	}
	
	public boolean excluir(int codigoProjeto, Usuario usuarioProprietario) {
		return repoProjeto.excluir(codigoProjeto, usuarioProprietario);
	}

	public boolean addColaborador(Usuario usuarioColaborador) {
		return repoProjeto.addColaborador(usuarioColaborador);
	}

}
