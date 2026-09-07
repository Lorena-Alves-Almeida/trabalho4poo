package trabalho4poo;

import java.util.ArrayList;
import java.util.List;

import alessio.*;


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


	//funcao que cria um novo projeto
	String criarProjeto(String nmProjeto, String proprietario) {

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

		projetos.add(new Projeto(nmProjeto, proprietarioCadastro));

		return "Projeto criado com sucesso!";
	}
	
	//funcao que mostra projeto por cdoigo
	Projeto projetoPorCod(int codProjeto) {

		for (int i = 0; i < projetos.size(); i++) {
			if (codProjeto == projetos.get(i).getCdProjeto()) {
				return projetos.get(i);
			}
		}
		return null;
	}
}

