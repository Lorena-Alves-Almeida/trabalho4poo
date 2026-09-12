package trabalho4poo.negocio;

import java.util.List;

import trabalho4poo.dados.RepositorioUsuario;
import trabalho4poo.ui.*;

public class ControladorUsuario {
	private RepositorioUsuario repoUsuario;

	public ControladorUsuario() {
		repoUsuario = new RepositorioUsuario();
	}

	public boolean add(Usuario u) {
		if (u!= null && !repoUsuario.existeNome(u.getNmUsuario())) {
			return repoUsuario.add(u);
		} else {
			return false;
		}
	}

	//procurar o projeto por codigo
	public Usuario projetoPorCod(int codigo) {
		return repoUsuario.buscarPorCodigo(codigo);
	}

	// listar
	public List<Usuario> listar() {
		return repoUsuario.listar();
	}

}
