package trabalho4poo.negocio;

import java.util.ArrayList;
import java.util.List;
import trabalho4poo.ui.*;
import trabalho4poo.negocio.*;
import trabalho4poo.*;
import trabalho4poo.dados.*;

public class Sistema {

	private static Sistema instance;

	public static Sistema getInstance() {
		if (instance == null) {
			instance = new Sistema();
		}
		return instance;
	}

	public List<Usuario> usuarios = new ArrayList<Usuario>();
	public List<Projeto> projetos = new ArrayList<Projeto>();

	// funcao que cria um novo projeto
	public String criarProjeto(String nmProjeto, String proprietario) {

		Usuario proprietarioCadastro = null;
		for (int i = 0; i < projetos.size(); i++) {
			if (nmProjeto == projetos.get(i).getNmProjeto()) {
				return "Ja existe um projeto com esse nome!";
			}
		}

		for (int i = 0; i < Usuario.quantUsuarios; i++) {
			if (proprietario.equals(usuarios.get(i).getNmUsuario())) {
				proprietarioCadastro = usuarios.get(i);
			}
		}

		if (proprietarioCadastro == null) {
			return "usuario nao encontrado!";
		}

		projetos.add(new Projeto(nmProjeto, proprietarioCadastro, null));

		return "Projeto criado com sucesso!";
	}

	// adiciona colaborador
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

	// funcao que mostra projeto por cdoigo
	Projeto projetoPorCod(int codProjeto) {

		for (int i = 0; i < projetos.size(); i++) {
			if (codProjeto == projetos.get(i).getCdProjeto()) {
				return projetos.get(i);
			}
		}
		return null;
	}

}
