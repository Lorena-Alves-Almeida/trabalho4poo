package trabalho4poo.dados;

import trabalho4poo.negocio.Usuario;
import trabalho4poo.negocio.Sistema; 
import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {
	private List<Usuario> usuarios;
	
	public RepositorioUsuario() {
		usuarios = new ArrayList<Usuario>();
	}

	// inserir
	public boolean add(Usuario p) {
		if (p == null)
			return false;
		return usuarios.add(p);
	}

	// alterar
	public boolean alterar(Usuario pAlterado) {
		if (pAlterado == null)
			return false;
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getCdUsuario() == pAlterado.getCdUsuario()) {
				usuarios.set(i, pAlterado);
				return true;
			}
		}
		return false;
	}

	// excluir
	//COLOCAR EM CAMADAS NÃO ESQUECER
	public boolean excluir(int codigo) {
		Usuario u = buscarPorCodigo(codigo);
		if (u == null)
			return false;
		u.setExcluido(true);
		//usuarios.remove(u);
		return true;
	}

	// Listagens
	public List<Usuario> listar() {
		List<Usuario> listaCopia = new ArrayList<Usuario>();

		for (Usuario p : usuarios)
			listaCopia.add(new Usuario(p));

		return listaCopia;
	}

	public Usuario buscarPorCodigo(int codigo) {
		for (Usuario u : usuarios) {
			if (!u.isExcluido() && u.getCdUsuario() == codigo)
				return new Usuario(u);
		}
		return null;
	}

	public Usuario buscarPorNome(String nome) {
		for (Usuario u : usuarios) {
			if (!u.isExcluido() && u.getNmUsuario().equals(nome))
				return new Usuario(u);
		}
		return null;
	}

	public boolean existeNome(String nome) {
		for (Usuario u : usuarios)
			if (u.getNmUsuario().equalsIgnoreCase(nome))
				return true;
		return false;
	}
}
