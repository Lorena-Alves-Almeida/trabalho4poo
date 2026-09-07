package trabalho4poo;

import java.util.List;
import java.util.ArrayList;

import alessio.*;


public class Usuario {
	private int cdUsuario;
	private String nmUsuario;
	private String senha;
//	private int tipo;

	public static int quantUsuarios;
	
	private Usuario() {
		System.out.println("Criou um objeto usuario()");
	}

	public Usuario(Usuario outro) {
		this.cdUsuario = outro.cdUsuario;
		this.nmUsuario = outro.nmUsuario;
		this.senha = outro.senha;
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

	public void setNomeUsuario(String nmUsuario) {
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

//	public int getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(int tipo) {
//		if (tipo == 1 || tipo == 2)
//			this.tipo = tipo;
//	}

	public Usuario(String nmUsuario, String senha/*, int tipo*/) {
		cdUsuario++;
		this.nmUsuario = nmUsuario;
		this.senha = senha;
//		this.tipo = tipo;
		quantUsuarios++;
	}

}
