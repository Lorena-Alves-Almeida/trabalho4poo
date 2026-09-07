package trabalho4poo.negocio;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
	private int cdProjeto;
	private String nmProjeto;
	private Usuario proprietario;
	private List<Usuario> colaboradores = new ArrayList<Usuario>();
	private String privacidade;
	private String projetoCodigo;
	public static int quantProjetos = 0;
	
	
	public Projeto() {
		System.out.println("Criou um objeto Projeto()");
	}
	
	public Projeto(Projeto outro) {
		this.cdProjeto = outro.cdProjeto;
		this.nmProjeto = outro.nmProjeto;
		this.proprietario = outro.proprietario;
		this.colaboradores = outro.colaboradores;
	}


	public int getCdProjeto() {
		return cdProjeto;
	}

	public void setCdProjeto(int cdProjeto) {
		this.cdProjeto = cdProjeto;
	}

	public String getNmProjeto() {
		return nmProjeto;
	}

	public void setNmProjeto(String nmProjeto) {
		if (nmProjeto != null)
		this.nmProjeto = nmProjeto;
	}

	public Usuario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Usuario proprietario) {
		if (proprietario != null)
		this.proprietario = proprietario;
	}

	public String getPrivacidade() {
		return privacidade;
	}

	public void setPrivacidade(String privacidade) {
		if (privacidade != null)
		this.privacidade = privacidade;
	}
	
	public List<Usuario> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Usuario> colaboradores) {
		if (colaboradores != null)
		this.colaboradores = colaboradores;
	}
	
	public String getProjetoCodigo() {
		return projetoCodigo;
	}
	
	public void setProjetoCodigo(String projetoCodigo) {
		if (projetoCodigo != null)
		this.projetoCodigo = projetoCodigo;
	}


	public Projeto(String nmProjeto, Usuario proprietario, String privacidade, List<Usuario> colaboradores, String projetoCodigo) {
		quantProjetos++;
		this.cdProjeto = quantProjetos;
		this.nmProjeto = nmProjeto;
		this.proprietario = proprietario;
		this.privacidade = privacidade;
		this.colaboradores = colaboradores;
		this.projetoCodigo = projetoCodigo;
	}



}
