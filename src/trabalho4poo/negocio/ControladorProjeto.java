package trabalho4poo.negocio;

import java.util.List;
import trabalho4poo.dados.RepositorioProjeto;

public class ControladorProjeto {
	private RepositorioProjeto repoProjeto;

	public ControladorProjeto() {
		repoProjeto = new RepositorioProjeto();
	}

	// criar projeto
	public boolean add(String nmProjeto, Usuario proprietario, String privacidade) {
		return repoProjeto.add(nmProjeto, proprietario, privacidade);
	}

	// procurar o projeto por codigo
	public Projeto buscar(int codigo) {
		return repoProjeto.buscar(codigo);
	}

	// procurar projeto por codigo, incluindo privados
	public Projeto buscarPrivado(int codigo) {
		return repoProjeto.buscarPrivado(codigo);
	}

	// listar
	public List<Projeto> listar() {
		return repoProjeto.listar();
	}
	
	public boolean excluir(int codigoProjeto, Usuario usuarioProprietario) {
		return repoProjeto.excluir(codigoProjeto, usuarioProprietario);
	}

	public boolean addColaborador(
			int codigoProjeto,
			Usuario usuarioColaborador) {

		return repoProjeto.addColaborador(
				codigoProjeto,
				usuarioColaborador);
	}

	public boolean addColaborador(Usuario usuarioColaborador) {
		return repoProjeto.addColaborador(usuarioColaborador);
	}

	public boolean alterar(Projeto projeto) {
		return repoProjeto.alterar(projeto);
	}

	public boolean alterarCodigo(
			int codigoProjeto,
			String codigoHTML) {

		return repoProjeto.alterarCodigo(
				codigoProjeto,
				codigoHTML);
	}

	public List<Projeto> buscarProjetosDoUsuario(
			Usuario usuario) {

		return repoProjeto.buscarProjetosDoUsuario(
				usuario);
	}
}
