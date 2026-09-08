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
			if (usuarios.get(i).getCodigo() == pAlterado.getCodigo()) {
				usuarios.set(i, pAlterado);
				return true;
			}
		}
		return false;
	}

	// excluir
	public boolean excluir(int codigo) {
		Usuario p = buscarPorCodigo(codigo);
		if (p == null)
			return false;

		// codigo vai para Sistema no futuro
		if (Sistema.getInstance().produtoTemVendas(codigo))
			p.setExcluido(true);
		else
			usuarios.remove(p);
		return true;
	}

	// Listagens
	public List<Usuario> listar() {
		List<Usuario> listaCopia = new ArrayList<Usuario>();

		for (Usuario p : usuarios)
			listaCopia.add(new Usuario(p));

		return listaCopia;
	}

	// buscar
	public boolean marcaTemProdutos(int codigoMarca) {
		for (Usuario p : produtos)
			if (p.getMarca() != null && p.getMarca().getCodigo() == codigoMarca)
				return true;
		return false;
	}

	public Usuario buscarPorCodigo(int codigo) {
		for (Usuario p : produtos) {
			if (!p.isExcluido() && p.getCodigo() == codigo)
				return new Usuario(p);
		}
		return null;
	}

	public Usuario buscarPorNome(String nome) {
		for (Usuario p : produtos) {
			if (!p.isExcluido() && p.getNome().equals(nome))
				return new Usuario(p);
		}
		return null;
	}

	public boolean existeNome(String nome) {
		for (Usuario p : produtos)
			if (p.getNome().equalsIgnoreCase(nome))
				return true;
		return false;
	}
}
