package trabalho4poo.negocio;

import java.util.ArrayList;
import java.util.List;

import trabalho4poo.negocio.Usuario;
import trabalho4poo.ui.*;
import trabalho4poo.negocio.*;
import trabalho4poo.*;
import trabalho4poo.dados.*;

public class Sistema {

	private static Sistema instance;
	
	private List<Usuario> usuarios;
	private List<Projeto> projetos;

	public static Sistema getInstance() {
		if (instance == null) {
			instance = new Sistema();
		}
		return instance;
	}
	
	private Sistema() {
		projetos = new ArrayList<Projeto>();
		usuarios = new ArrayList<Usuario>();
	}

	// funcao que cria um novo projeto
	public String criarProjeto(String nmProjeto, String proprietario, String privacidade) {

		Usuario proprietarioCadastro = null;
		for (int i = 0; i < projetos.size(); i++) {
			if (nmProjeto == projetos.get(i).getNmProjeto()) {
				return "Ja existe um projeto com esse nome!";
			}
		}

		for (int i = 0; i < Usuario.quantUsuarios; i++) {
			if (proprietario.equals(usuarios.get(i).getNmUsuario()) && privacidade.equals("Publico")
					|| privacidade.equals("Privado")) {
				proprietarioCadastro = usuarios.get(i);
			}
		}

		if (proprietarioCadastro == null) {
			return "usuario nao encontrado!";
		}

		projetos.add(new Projeto(nmProjeto, proprietarioCadastro, privacidade, null, null));

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
	public Projeto projetoPorCod(int codProjeto) {

		for (int i = 0; i < projetos.size(); i++) {
			if (codProjeto == projetos.get(i).getCdProjeto() && projetos.get(i).getPrivacidade() == "Publico") {
				return projetos.get(i);
			}
		}
		return null;
	}

	//excluir projeto
	public String excluirProjeto(int codigoProjeto, String nomeProprietario, String senhaProprietario) {

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
	
	public boolean addUsuario(Usuario u) {
		if (u == null)
			return false;
		return usuarios.add(u);
	}

	public Usuario buscarUsuarioPorId(int id) {
		for (Usuario u : usuarios)
			if (u.getCdUsuario() == id)
				return u;
		return null;
	}

	public Usuario buscarUsuarioPorLogin(String nomeUsuario) {
		for (Usuario u : usuarios)
			if (u.getNmUsuario().equalsIgnoreCase(nomeUsuario))
				return u;
		return null;
	}

	public boolean existeNomeUsuario(String nomeUsuario) {
		return buscarUsuarioPorLogin(nomeUsuario) != null;
	}

	public boolean atualizarUsuario(int id, String nome,
			String senha) {
		Usuario u = buscarUsuarioPorId(id);
		if (u == null)
			return false;

		if (nome != null)
			u.setNmUsuario(nome);
		if (senha != null)
			u.setSenha(senha);
		return true;
	}

	public boolean excluirUsuario(int id) {
		Usuario u = buscarUsuarioPorId(id);
		if (u == null)
			return false;
		return usuarios.remove(u);
	}

	public List<Usuario> listarUsuarios() {
		return usuarios;
	}
	
	public Usuario autenticarLogin(String nmUsuario, String senha) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNmUsuario().equals(nmUsuario) && usuarios.get(i).getSenha().equals(senha))
				return usuarios.get(i);
		}return null;
	}
	
	public List<Usuario> getUsuarios() {
		List<Usuario> copia = new ArrayList<Usuario>();
		for (int i = 0; i < usuarios.size(); i++) {

			if (usuarios.get(i) != null)
				copia.set(Usuario(usuarios.get(i))); //olhar no google como chamar o construtor para um arraylist
		}

		return copia;
	}

}
