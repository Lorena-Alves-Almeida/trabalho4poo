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
	public boolean add(String nmProjeto, String proprietario, String privacidade) {

		Usuario proprietarioCadastro = null;
		for (int i = 0; i < projetos.size(); i++) {
			if (nmProjeto == projetos.get(i).getNmProjeto()) {
				return false;
			}
		}

		for (int i = 0; i < Usuario.quantUsuarios; i++) {
			if (proprietario.equals(usuarios.get(i).getNmUsuario()) && privacidade.equals("Publico")
					|| privacidade.equals("Privado")) {
				proprietarioCadastro = usuarios.get(i);
			}
		}

		if (proprietarioCadastro == null) {
			return false;
		}

		projetos.add(new Projeto(nmProjeto, proprietarioCadastro, privacidade, null, null));

		return true;
	}

	public String addColaborador(String colaborador) {

		Usuario colaboradorCadastro = null;
		for (int i = 0; i < usuarios.size(); i++) {
			if (colaborador == usuarios.get(i).getNmUsuario()) {
				colaboradorCadastro = usuarios.get(i);
			}
		}

		if (colaboradorCadastro.equals(projetos.get(Projeto.quantProjetos).getProprietario())) {
			colaboradorCadastro = null;
			return "Falha ao adicionar colaborador!";
		}

		projetos.get(Projeto.quantProjetos).getColaboradores().add(colaboradorCadastro);
		return "Colaborador adicionado com sucesso!";

	}

	public List<Projeto> listar() {
		List<Projeto> listaCopia = new ArrayList<Projeto>();

		for (Projeto p : projetos)
			for (int i = 0; i < projetos.size(); i++) {
				if (projetos.get(i).getPrivacidade() == "Pubico") {
					listaCopia.add(new Projeto(p));
				}
			}

	}

	public Projeto projetoPorCod(int codProjeto) {

		for (int i = 0; i < projetos.size(); i++) {
			if (codProjeto == projetos.get(i).getCdProjeto() && projetos.get(i).getPrivacidade() == "Publico") {
				return projetos.get(i);
			}
		}
		return null;
	}
	
	public String excluir(int codigoProjeto, String nomeProprietario, String senhaProprietario) {

		Usuario usuarioProjetoExcluido = null;
		for (int i = 0; i < usuarios.size(); i++) {
			if (senhaProprietario == usuarios.get(i).getSenha() && nomeProprietario == usuarios.get(i).getNmUsuario()) {
				usuarioProjetoExcluido = usuarios.get(i);
			} else {
				return "Senha ou nome de usuario invalido!";
			}
		}
		for (int j = 0; j < projetos.size(); j++) {
			if (codigoProjeto == projetos.get(j).getCdProjeto()
					&& usuarioProjetoExcluido == projetos.get(j).getProprietario()) {
				projetos.remove(projetos.get(j));
			} else {
				return "codigo invalido!";
			}
		}
		return "Projeto excluido.";
	}


	public boolean existeNome(String nome) {
		for (Produto p : produtos)
			if (p.getNome().equalsIgnoreCase(nome))
				return true;
		return false;
	}

}
