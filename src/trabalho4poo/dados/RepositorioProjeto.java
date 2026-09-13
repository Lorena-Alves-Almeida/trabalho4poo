package trabalho4poo.dados;

import java.util.ArrayList;
import java.util.List;

import trabalho4poo.ui.*;
import trabalho4poo.negocio.*;
import trabalho4poo.dados.*;

public class RepositorioProjeto {
	private List<Projeto> projetos;

	public RepositorioProjeto() {
		projetos = new ArrayList<Projeto>();
	}

	// inserir
	public boolean add(String nmProjeto, Usuario proprietario, String privacidade) {

		for (int i = 0; i < projetos.size(); i++) {
			if (nmProjeto == projetos.get(i).getNmProjeto()) {
				return false;
			}
		}

		if (privacidade.equals("Publico") || privacidade.equals("Privado")) {
		}
		projetos.add(new Projeto(nmProjeto, proprietario, privacidade));

		return true;
	}

	public boolean addColaborador(Usuario usuarioColaborador) {

		if (usuarioColaborador.equals(projetos.get(Projeto.quantProjetos).getProprietario())) {
			usuarioColaborador = null;
			return false;
		}

		projetos.get(Projeto.quantProjetos).getColaboradores().add(usuarioColaborador);
		return true;
	}

	public List<Projeto> listar() {
		List<Projeto> listaCopia = new ArrayList<Projeto>();

		for (Projeto p : projetos)
			for (int i = 0; i < projetos.size(); i++) {
				if (projetos.get(i).getPrivacidade() == "Pubico") {
					listaCopia.add(new Projeto(p));
				}
			}

		return listaCopia;
	}

	public Projeto buscar(int codigo) {

		for (Projeto p : projetos) {
			if (codigo == p.getCdProjeto() && p.getPrivacidade() == "Publico") {
				return new Projeto(p);

			}
		}
		return null;
	}

	public boolean excluir(int codigoProjeto, Usuario usuarioProprietario) {

		for (int i = 0; i < Sistema.getInstance().getUsuarios().size(); i++) {
			if (usuarioProprietario.equals(projetos.get(i).getProprietario())
					&& codigoProjeto == Sistema.getInstance().getProjetos().get(i).getCdProjeto()) {
				projetos.remove(projetos.get(i));

			} else {
				return false;
			}
		}

		return true;
	}

	public boolean existeNome(String nome) {
		for (int i = 0; i < Sistema.getInstance().getProjetos().size(); i++) {
			if (Sistema.getInstance().getProjetos().get(i).getNmProjeto().equalsIgnoreCase(nome))
				return true;
		}
		return false;
	}

}
