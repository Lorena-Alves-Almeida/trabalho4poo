package alessio;

import java.util.ArrayList;
import java.util.List;

import alessio.*;

public class Projeto {
	private int cdProjeto;
	private String nmProjeto;
	private Usuario proprietario;
	private List<Usuario> colaboradores = new ArrayList<Usuario>();
	private String projetoCodigo;
	private int quantProjetos = 0;
	
	
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



	public Projeto(String nmProjeto, Usuario proprietario) {
		quantProjetos++;
		this.cdProjeto = quantProjetos;
		this.nmProjeto = nmProjeto;
		this.proprietario = proprietario;
		this.colaboradores = colaboradores;
		this.projetoCodigo = projetoCodigo;
	}

}
