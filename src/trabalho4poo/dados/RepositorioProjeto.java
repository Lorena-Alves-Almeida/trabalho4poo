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

	public boolean addColaborador(String colaborador) {

		Usuario colaboradorCadastro = null;
		for (int i = 0; i < Sistema.getInstance().getUsuarios().size(); i++) {
			if (colaborador == Sistema.getInstance().getUsuarios().get(i).getNmUsuario()) {
				colaboradorCadastro = Sistema.getInstance().getUsuarios().get(i);
			}
		}

		if (colaboradorCadastro.equals(projetos.get(Projeto.quantProjetos).getProprietario())) {
			colaboradorCadastro = null;
			return false;
		}

		projetos.get(Projeto.quantProjetos).getColaboradores().add(colaboradorCadastro);
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

	public boolean excluir(int codigoProjeto, String nomeProprietario, String senhaProprietario) {

		Usuario usuarioProjetoExcluido = null;
		for (int i = 0; i < Sistema.getInstance().getUsuarios().size(); i++) {
			if (senhaProprietario == Sistema.getInstance().getUsuarios().get(i).getSenha()
					&& nomeProprietario == Sistema.getInstance().getUsuarios().get(i).getNmUsuario()) {
				usuarioProjetoExcluido = Sistema.getInstance().getUsuarios().get(i);
			} else {
				return false;
			}
		}
		for (int j = 0; j < projetos.size(); j++) {
			if (codigoProjeto == projetos.get(j).getCdProjeto()
					&& usuarioProjetoExcluido == projetos.get(j).getProprietario()) {
				projetos.remove(projetos.get(j));
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
