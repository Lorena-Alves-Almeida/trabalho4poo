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
	private Usuario usuarioLogado;

	private ControladorUsuario cUsuario;
	private ControladorProjeto cProjeto;
//	private ControladorMarca cMarca;
//	private ControladorVenda cVenda;

	// Construtor de Sitema
	private Sistema() {
		cUsuario = new ControladorUsuario();
		cProjeto = new ControladorProjeto();
		projetos = new ArrayList<>();
		usuarios = new ArrayList<>();
	}


	public static Sistema getInstance() {
		if (instance == null) {
			instance = new Sistema();
		}
		return instance;
	}
	
	public boolean addUsuario(Usuario u) {
		return cUsuario.add(u);
	}

	public Usuario buscarUsuarioPorId(int id) {
		return cUsuario.buscarPorCodigo(id);
	}

	public Usuario buscarUsuarioPorLogin(String nomeUsuario) {
		return cUsuario.buscarPorNome(nomeUsuario);
	}

	public boolean existeNomeUsuario(String nomeUsuario) {
		return buscarUsuarioPorLogin(nomeUsuario) != null;
	}

//	public boolean atualizarUsuario(int id, String nome,
//			String senha) {
//		Usuario u = buscarUsuarioPorId(id);
//		if (u == null)
//			return false;
//
//		if (nome != null)
//			u.setNmUsuario(nome);
//		if (senha != null)
//			u.setSenha(senha);
//		return true;
//	}

	public boolean excluirUsuario(int id) {
		Usuario u = buscarUsuarioPorId(id);
		if (u == null)
			return false;
		return usuarios.remove(u);
	}
	
	public Usuario autenticarLogin(String nmUsuario, String senha) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNmUsuario().equals(nmUsuario) && usuarios.get(i).getSenha().equals(senha)) {
				usuarioLogado = usuarios.get(i);
				return usuarios.get(i);
			}
		}return null;
	}
	
	public List<Usuario> getUsuarios() {
		List<Usuario> copia = new ArrayList<Usuario>();
		for (int i = 0; i < usuarios.size(); i++) {

			if (usuarios.get(i) != null)
				copia.add(new Usuario(usuarios.get(i)));
		}

		return copia;
	}

	public List<Projeto> getProjetos() {
		List<Projeto> copia = new ArrayList<Projeto>();
		for (int i = 0; i < usuarios.size(); i++) {

			if (projetos.get(i) != null)
				copia.add(new Projeto(projetos.get(i)));
		}

		return copia;
	}
	
	public boolean adicionarColaborador(Usuario usuarioColaborador) {
		return cProjeto.addColaborador(usuarioColaborador);
	}
	
	public boolean excluirProjeto(int codigoProjeto, Usuario usuarioProprietario) {
		return cProjeto.excluir(codigoProjeto, usuarioProprietario);
	}
	
	
	public Projeto buscarProjetoPorCodigo(int codigo) {
		return cProjeto.buscar(codigo);
	}
	
	public List<Usuario> listarUsuarios(){
		return cUsuario.listar();
	}
<<<<<<< Updated upstream

=======
	
	public boolean alterarUsuario(Usuario uAlterado) {
		return cUsuario.alterar(uAlterado);
	}
	
	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}
>>>>>>> Stashed changes
}
