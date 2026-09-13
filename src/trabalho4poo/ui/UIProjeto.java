package trabalho4poo.ui;

import trabalho4poo.ui.*;
import trabalho4poo.negocio.*;
import trabalho4poo.dados.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class UIProjeto {

	private static Scanner scn;

	public UIProjeto() {
		scn = new Scanner(System.in);
	}

	
	public void add() {
		System.out.println("================================================================");
		System.out.print("\n--- Criando um novo projeto: ---");
		System.out.print("\nNome do projeto: ");
		scn.nextLine();
		String nmProjeto = scn.nextLine();
		
		String proprietario;
		do {
			System.out.print("\nNome do proprietario: ");
			proprietario = scn.nextLine();
		}while(!Sistema.getInstance().existeNomeUsuario(proprietario));
		
		
		System.out.print("\nO projeto é Publico ou Privado? ");
		System.out.println("OBS.: Escreva exatamente 'Publico'/'Privado'");
		String privacidade = scn.next();

		Usuario usuarioProprietario = Sistema.getInstance().buscarUsuarioPorLogin(proprietario);
		
	    Projeto.getInstance(nmProjeto, usuarioProprietario, privacidade);
	}

	public void addColaborador() {
		System.out.println();
		System.out.println("----- Adicionar colaborador: -----");
		System.out.println("\n ---- Usuarios: ----");
		System.out.println("|COD.\t |NOME\t");

		for (int i = 0; i < Sistema.getInstance().getUsuarios().size(); i++) {
			if (Sistema.getInstance().getUsuarios().get(i) != null) {
				System.out.printf("%-8s %-20s%n", Sistema.getInstance().getUsuarios().get(i).getCdUsuario(),
						Sistema.getInstance().getUsuarios().get(i).getNmUsuario());
			}
		}

		System.out.print("\nNome do colaborador: ");
		scn.nextLine();
		String colaborador = scn.nextLine();
		do {
			System.out.print("\nNome do colaborador: ");
			colaborador = scn.nextLine();
		}while(!Sistema.getInstance().existeNomeUsuario(colaborador));
		
		Projeto usuarioColaborador = Sistema.getInstance().buscarUsuarioPorLogin(colaborador);

		Projeto adicionaColaborador = Sistema.getInstance().adicionarColaborador(colaborador);
		    if (adicionaColaborador != null) {
		    	System.out.println("Colaborador adicionado com sucesso.");
		    }else {
		    	System.out.println("Falha ao adicionar colaborador.");
		    }
	}

	public void fazer() {
		System.out.println("=========================================================================");
		System.out.println("=== CONFIGURADOR DE LAYOUT DINÂMICO ===");

		// 1. Definição da quantidade de Colunas Principais
		System.out.print("\nEm quantas colunas deseja dividir o container principal? ");
		int qtdColunas = scn.nextInt();

		// 2. Definição da quantidade de Divs
		System.out.print("Quantas Divs deseja criar empilhadas na coluna de Divs? ");
		int qtdDivs = scn.nextInt();

		// 3. Definição dos Formulários e seus Inputs
		System.out.print("Quantos Formulários deseja criar na coluna de Formulários? ");
		int qtdForms = scn.nextInt();
		scn.nextLine(); // Consome a quebra de linha do scanner

		// 4. Posicionamento e Altura
		System.out.println("\nQual a disposição no container principal?");
		System.out.println("1 - Coluna de Divs na Esquerda | Formulários na Direita");
		System.out.println("2 - Formulários na Esquerda | Coluna de Divs na Direita");
		System.out.print("Opção: ");
		int posicao = scn.nextInt();

		System.out.print("\nQual a altura total do container (em px)? ");
		int alturaPx = scn.nextInt();
		scn.nextLine();

		// --- CONSTRUÇÃO DA ESTRUTURA HTML ---

		// Container Principal
		Div containerPrincipal = new Div("main-container", "flex-container");
		containerPrincipal.adicionarEstilo("display", "flex");
		containerPrincipal.adicionarEstilo("gap", "20px");
		containerPrincipal.adicionarEstilo("width", "100%");
		containerPrincipal.adicionarEstilo("height", alturaPx + "px");

		// Cálculo dinâmico da largura de cada coluna com base na escolha do usuário
		int larguraPorcentagemColuna = qtdColunas > 0 ? 100 / qtdColunas : 100;

		// Coluna de Divs
		Div colunaDivs = new Div("coluna-divs", "coluna");
		colunaDivs.adicionarEstilo("display", "flex");
		colunaDivs.adicionarEstilo("flex-direction", "column");
		colunaDivs.adicionarEstilo("width", larguraPorcentagemColuna + "%");
		colunaDivs.adicionarEstilo("height", "100%");
		colunaDivs.adicionarEstilo("gap", "10px");

		// Calcula a porcentagem de altura proporcional para cada Div criada
		int alturaPorcentagemDiv = qtdDivs > 0 ? 100 / qtdDivs : 100;

		for (int i = 1; i <= qtdDivs; i++) {
			Div divFilha = new Div("div-filha-" + i, "caixa-filha");
			divFilha.adicionarEstilo("height", alturaPorcentagemDiv + "%");
			divFilha.adicionarEstilo("background-color", "#e0e0e0");
			divFilha.adicionarFilho(
					new Paragrafo("p-" + i, "texto", "Conteúdo da Div " + i));
			colunaDivs.adicionarFilho(divFilha);
		}

		// Coluna de Formulários
		Div colunaForms = new Div("coluna-forms", "coluna");
		colunaForms.adicionarEstilo("display", "flex");
		colunaForms.adicionarEstilo("flex-direction", "column");
		colunaForms.adicionarEstilo("width", larguraPorcentagemColuna + "%");
		colunaForms.adicionarEstilo("height", "100%");
		colunaForms.adicionarEstilo("gap", "15px");

		for (int f = 1; f <= qtdForms; f++) {
			System.out.println("\n--- CONFIGURAÇÃO DO FORMULÁRIO " + f + " ---");
			System.out.print("Quantos inputs este formulário terá? ");
			int qtdInputs = scn.nextInt();
			scn.nextLine();

			Formulario form = new Formulario("form-" + f, "formulario-estilizado",
					"/enviar", "POST");
			form.adicionarEstilo("display", "flex");
			form.adicionarEstilo("flex-direction", "column");
			form.adicionarEstilo("gap", "10px");
			form.adicionarEstilo("background-color", "#f9f9f9");
			form.adicionarEstilo("padding", "15px");

			for (int inp = 1; inp <= qtdInputs; inp++) {
				System.out.print("Texto do placeholder para o Input " + inp + ": ");
				String placeholder = scn.nextLine();

				Input input = new Input("input-" + f + "-" + inp, "campo", "text",
						"campo_" + f + "_" + inp, placeholder);
				form.adicionarFilho(input);
			}
			colunaForms.adicionarFilho(form);
		}

		// Montagem do Layout Principal de acordo com a opção escolhida
		if (posicao == 1) {
			containerPrincipal.adicionarFilho(colunaDivs);
			containerPrincipal.adicionarFilho(colunaForms);
		} else {
			containerPrincipal.adicionarFilho(colunaForms);
			containerPrincipal.adicionarFilho(colunaDivs);
		}

		// --- GERAÇÃO E ARMAZENAMENTO NA STRING ---

		StringBuilder sb = new StringBuilder();
		sb.append("<!-- HTML Gerado Dinamicamente -->\n");
		sb.append(containerPrincipal.renderizar(0));

		// Variável contendo o código final como String
		String htmlGerado = sb.toString();

		// Exemplo de uso da String criada:
		System.out.println("\n=== HTML GERADO EM STRING COM SUCESSO ===");
		System.out.println(htmlGerado);
		Sistema.getInstance().getProjetos().get(Projeto.quantProjetos).setProjetoCodigo(htmlGerado);
	}


	public void listar(List <Projeto> listaCopia) {
		System.out.println("\n--- Lista de produtos: ---");
		System.out.println("|COD.\t |NOME\t             |PROPRIETARIO\t      ");
		for (int i = 0; i < listaCopia.size(); i++) {
			if (listaCopia.get(i) != null) {
				System.out.printf("%-8s %-20s %-16s%n", listaCopia.get(i).getCdProjeto(),
						listaCopia.get(i).getNmProjeto(), listaCopia.get(i).getProprietario());
			}
		}
	}
	
	public void projetoPorCodigo() {
		System.out.println();
		System.out.println("================================================================");
		System.out.println("--- Procurar projeto por codigo: ---");
		System.out.println("OBS.: o projeto so sera mostrado se for publico.");
		int codigo = scn.nextInt();

		Projeto projetoCod = Sistema.getInstance().buscarProjetoPorCodigo(codigo);

		System.out.println("\n--- Projeto do codigo " + codigo + " : ---");
		System.out.println("|COD.\t |NOME\t      |PROPRIETARIO(A)\t      ");

		if (projetoCod != null) {
			System.out.printf("%-8s %-20s %-16s%n", codigo, projetoCod.getNmProjeto(),
					projetoCod.getProprietario());

		} else {
			System.out.println("Codigo nao encontrado ou projeto Privado.");
		}
	}
	
	public void excluir() {
		System.out.println("===============================================================");
		System.out.println("\nAVISO: Apenas o proprietario do projeto pode excui-lo.");
		System.out.print("\nCodigo do projeto: ");
		int codigoProjeto = scn.nextInt();
		System.out.print("\nNome do proprietario: ");
		String nomeProprietario = scn.next();
		System.out.print("\nSenha do proprietario: ");
		String senhaProprietario = scn.next();

		Projeto excluiProjeto = Sistema.getInstance().excluirProjeto(codigoProjeto, nomeProprietario, senhaProprietario);
		if (excluiProjeto != null) {
			System.out.println("Projeto excluido com sucesso!");
		}else {
			System.out.println("Falha ao excluir projeto");
		}
	}
//	public void add() {
//		System.out.print("Nome: ");
//		String nome = scn.next();
//		if (Sistema.getInstance().existeNomeProduto(nome)) {
//			System.out.println("Nome já cadastrado!");
//			return;
//		}
//
//		System.out.print("Quantidade: ");
//		int quantidade = scn.nextInt();
//		System.out.print("Preço: ");
//		double preco = scn.nextDouble();
//
//		largura.listarM(Sistema.getInstance().listarMarcas());
//		System.out.print("Marca do produto (código): ");
//		int codMarca = scn.nextInt();
//		Marca marca = Sistema.getInstance().buscarMarcaPorCodigo(codMarca);
//		if (marca == null) {
//			System.out.println("Marca não encontrada.");
//			return;
//		}
//
//		Produto novoProduto = Produto.getInstance(nome, marca, quantidade,
//				preco);
//		if (novoProduto != null
//				&& Sistema.getInstance().addProduto(novoProduto))
//			System.out.println("Produto inserido com sucesso!");
//		else
//			System.out.println("Falha em inserir produto.");
//	}

	

}
