package trabalho4poo.dados;

import java.util.ArrayList;
import java.util.List;

import trabalho4poo.negocio.Projeto;
import trabalho4poo.negocio.Usuario;

public class RepositorioProjeto {
	private List<Projeto> projetos;

	public RepositorioProjeto() {
		projetos = new ArrayList<Projeto>();
	}

	// inserir
	public boolean add(
			String nmProjeto,
			Usuario proprietario,
			String privacidade) {

		for (int i = 0; i < projetos.size(); i++) {
			if (nmProjeto == projetos.get(i).getNmProjeto()) {
				return false;
			}
		}

		if (privacidade.equals("Publico")
				|| privacidade.equals("Privado")) {
		}

		projetos.add(
				new Projeto(
						nmProjeto,
						proprietario,
						privacidade));

		return true;
	}

	public boolean addColaborador(
			int codigoProjeto,
			Usuario usuarioColaborador) {

		if (usuarioColaborador == null) {
			return false;
		}

		for (int i = 0; i < projetos.size(); i++) {

			Projeto projeto = projetos.get(i);

			if (codigoProjeto == projeto.getCdProjeto()) {

				if (usuarioColaborador.equals(
						projeto.getProprietario())) {

					return false;
				}

				projeto.getColaboradores().add(
						usuarioColaborador);

				return true;
			}
		}

		return false;
	}

	public boolean addColaborador(
			Usuario usuarioColaborador) {

		if (usuarioColaborador == null
				|| projetos.isEmpty()) {

			return false;
		}

		Projeto projeto =
				projetos.get(projetos.size() - 1);

		if (usuarioColaborador.equals(
				projeto.getProprietario())) {

			return false;
		}

		projeto.getColaboradores().add(
				usuarioColaborador);

		return true;
	}

	public List<Projeto> listar() {

		List<Projeto> listaCopia =
				new ArrayList<Projeto>();

		for (Projeto p : projetos) {

			for (int i = 0;
					i < projetos.size();
					i++) {

				if (projetos.get(i)
						.getPrivacidade()
						== "Pubico") {

					listaCopia.add(
							new Projeto(p));
				}
			}
		}

		return listaCopia;
	}

	public Projeto buscar(int codigo) {

		for (Projeto p : projetos) {

			if (codigo == p.getCdProjeto()
					&& p.getPrivacidade()
						== "Publico") {

				return new Projeto(p);
			}
		}

		return null;
	}

	public Projeto buscarPrivado(int codigo) {

		for (Projeto p : projetos) {

			if (codigo == p.getCdProjeto()) {

				return new Projeto(p);
			}
		}

		return null;
	}

	public boolean excluir(
			int codigoProjeto,
			Usuario usuarioProprietario) {

		if (usuarioProprietario == null) {
			return false;
		}

		for (int i = 0;
				i < projetos.size();
				i++) {

			Projeto projeto =
					projetos.get(i);

			if (codigoProjeto
					== projeto.getCdProjeto()
					&& usuarioProprietario.equals(
							projeto.getProprietario())) {

				projetos.remove(i);

				return true;
			}
		}

		return false;
	}

	public boolean alterar(
			Projeto projetoAlterado) {

		if (projetoAlterado == null) {
			return false;
		}

		for (int i = 0;
				i < projetos.size();
				i++) {

			if (projetos.get(i)
					.getCdProjeto()
					== projetoAlterado.getCdProjeto()) {

				projetos.set(
						i,
						projetoAlterado);

				return true;
			}
		}

		return false;
	}

	public boolean alterarCodigo(
			int codigoProjeto,
			String codigoHTML) {

		if (codigoHTML == null) {
			return false;
		}

		for (int i = 0;
				i < projetos.size();
				i++) {

			if (projetos.get(i)
					.getCdProjeto()
					== codigoProjeto) {

				projetos.get(i)
						.setProjetoCodigo(
								codigoHTML);

				return true;
			}
		}

		return false;
	}

	public List<Projeto> buscarProjetosDoUsuario(
			Usuario usuario) {

		List<Projeto> listaCopia =
				new ArrayList<Projeto>();

		if (usuario == null) {
			return listaCopia;
		}

		for (Projeto p : projetos) {

			if (p.getProprietario()
					.getCdUsuario()
					== usuario.getCdUsuario()) {

				listaCopia.add(
						new Projeto(p));
			}
		}

		return listaCopia;
	}

	public boolean existeNome(String nome) {

		for (Projeto projeto : projetos) {

			if (projeto.getNmProjeto()
					.equalsIgnoreCase(nome)) {

				return true;
			}
		}

		return false;
	}
}
