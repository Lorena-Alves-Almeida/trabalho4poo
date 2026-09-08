package trabalho4poo.negocio;

import java.util.List;

import java.util.ArrayList;


public class Usuario {
	private int cdUsuario;
	private String nmUsuario;
	private String senha;
//	private int tipo;

	public static int quantUsuarios;

    // Construtor de Usuário
	public Usuario(String nmUsuario, String senha) {
		cdUsuario++;
		this.nmUsuario = nmUsuario;
		this.senha = senha;
		quantUsuarios++;
	}
	
	// Construtor de cópia
	public Usuario(Usuario outro) {
		this.cdUsuario = outro.cdUsuario;
		this.nmUsuario = outro.nmUsuario;
		this.senha = outro.senha;
	}

    // Instance de Usuário
    public static Usuario getInstance(String nmUsuario, String senha) {
        if (nmUsuario != null && senha != null)
            return new Usuario(nmUsuario, senha);
        return null;
    }
	
    public boolean autenticar(String nomeUsuario, String senha) {
        return this.nmUsuario.equalsIgnoreCase(nomeUsuario) && this.senha.equals(senha);
    }
	
	public int getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(int cdUsuario) {
		if (cdUsuario > 0)
			this.cdUsuario = cdUsuario;
	}
	
	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		if (!nmUsuario.isEmpty() && nmUsuario != null)
			this.nmUsuario = nmUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if (!senha.isEmpty() && senha != null)
			this.senha = senha;
	}

}
