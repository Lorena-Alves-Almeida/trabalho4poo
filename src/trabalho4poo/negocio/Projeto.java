package trabalho4poo.negocio;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
	private int cdProjeto;
	private String nmProjeto;
	private Usuario proprietario;
	private List<Usuario> colaboradores = new ArrayList<Usuario>();
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

	public List<Usuario> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Usuario> colaboradores) {
		if (colaboradores != null)
		this.colaboradores = colaboradores;
	}


	public Projeto(String nmProjeto, Usuario proprietario, List<Usuario> colaboradores) {
		quantProjetos++;
		this.cdProjeto = quantProjetos;
		this.nmProjeto = nmProjeto;
		this.proprietario = proprietario;
		this.colaboradores = colaboradores;
		this.projetoCodigo = projetoCodigo;
	}



}
